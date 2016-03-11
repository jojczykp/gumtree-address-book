package pl.jojczykp.gumtree.address_book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Queries {

	public List<Collector<Record, ?, ?>> getQueries() {
		return Collections.singletonList(
				countingMales()
		);
	}

	private Collector<Record, ? , Integer> countingMales() {
		return Collectors.summingInt(r -> r.getGender() == Record.Gender.MALE ? 1 : 0);
	}

}
