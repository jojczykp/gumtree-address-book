package pl.jojczykp.gumtree.address_book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Streamer {

	public Stream<String> stream(String pathToFile) {
		try {
			return Files.lines(Paths.get(pathToFile));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
