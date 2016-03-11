package pl.jojczykp.gumtree.address_book;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Queries {

	public Collector<Record, ?, Long> countingMales() {
		return Collectors.summingLong(r -> r.getGender() == Record.Gender.MALE ? 1 : 0);
	}

}
