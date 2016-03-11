package pl.jojczykp.gumtree.address_book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Queries {

	public List<Collector<Record, ?, ?>> getQueries() {
		return asList(
				countingMales(),
				findingOldest()
		);
	}

	private Collector<Record, ? , Integer> countingMales() {
		return Collectors.summingInt(r -> r.getGender() == Record.Gender.MALE ? 1 : 0);
	}

	private Collector<Record, ? , String> findingOldest() {
		return Collectors.collectingAndThen(
				Collectors.minBy(Comparator.comparing(Record::getBirth)),
				record -> record.isPresent() ? record.get().getName() : "N/A"
		);
	}

}
