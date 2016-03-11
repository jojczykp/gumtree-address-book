package pl.jojczykp.gumtree.address_book;

import java.util.stream.Stream;

public class Processor {

	public int process(Stream<String> stream, Queries queries) {
		return stream
				.map(Record::new)
				.collect(queries.countingMales());
	}

}
