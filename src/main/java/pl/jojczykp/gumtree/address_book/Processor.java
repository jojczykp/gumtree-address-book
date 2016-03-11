package pl.jojczykp.gumtree.address_book;

import java.util.stream.Stream;

public class Processor {

	public long process(Stream<String> stream) {
		return stream
				.map(Record::new)
				.filter(r -> r.getGender() == Record.Gender.MALE)
				.count();
	}

}
