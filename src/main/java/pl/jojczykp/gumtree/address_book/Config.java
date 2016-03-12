package pl.jojczykp.gumtree.address_book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Config {

	public List<Question> getQuestions() {
		return asList(
				numberOfMales(),
				oldestPerson());
	}

	private Question numberOfMales() {
		return new Question("Number of Males",
				Collectors.summingInt(r -> r.getGender() == Record.Gender.MALE ? 1 : 0));
	}

	private Question oldestPerson() {
		return new Question("Oldest person",
				Collectors.collectingAndThen(
						Collectors.minBy(Comparator.comparing(Record::getBirth)),
						record -> record.isPresent() ? record.get().getName() : "N/A"));
	}

}
