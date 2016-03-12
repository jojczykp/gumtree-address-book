package pl.jojczykp.gumtree.address_book;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Config {

	public List<Question> allQuestions() {
		return asList(
				numberOfMales(),
				oldestPerson(),
				birthDelay());
	}

	public Question numberOfMales() {
		return new Question("Number of Males",
				Collectors.summingInt(r -> r.getGender() == Record.Gender.MALE ? 1 : 0));
	}

	public Question oldestPerson() {
		return new Question("Oldest person",
				Collectors.collectingAndThen(
						Collectors.minBy(Comparator.comparing(Record::getBirth)),
						record -> record.isPresent() ? record.get().getName() : "N/A"));
	}

	public Question birthDelay() {
		return new Question("Days that Bill is Older than Paul",
				Collector.of(
						() -> new YoungerOlderAccumulator("Bill McKnight", "Paul Robinson"),
						YoungerOlderAccumulator::consume,
						YoungerOlderAccumulator::merge,
						YoungerOlderAccumulator::calculateDurationDays));
	}

	private static class YoungerOlderAccumulator {

		private final String olderName;
		private final String youngerName;

		private LocalDate olderBirth;
		private LocalDate youngerBirth;

		public YoungerOlderAccumulator(String olderName, String youngerName) {
			this.olderName = olderName;
			this.youngerName = youngerName;
		}

		public void consume(Record r) {
			if (olderName.equals(r.getName())) {
				olderBirth = r.getBirth();
			} else if (youngerName.equals(r.getName())) {
				youngerBirth = r.getBirth();
			}
		}

		public YoungerOlderAccumulator merge(YoungerOlderAccumulator other) {
			youngerBirth = defaultIfNull(youngerBirth, other.youngerBirth);
			olderBirth = defaultIfNull(olderBirth, other.olderBirth);

			return this;
		}

		public long calculateDurationDays() {
			return ChronoUnit.DAYS.between(olderBirth, youngerBirth);
		}
	}

}
