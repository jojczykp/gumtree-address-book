package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.io.File;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StreamerTest {

	private Streamer testee = new Streamer();

	@Test
	public void shouldReadFile() {
		Stream<String> stream = testee.stream(getResourcePathAsString("AddressBook"));

		long count = stream.count();

		assertThat(count, is(5L));
	}

	@Test(expected = RuntimeException.class)
	public void shouldWrapExceptionWithUnchecked() {
		Stream<String> stream = testee.stream("notExisting");

		stream.count();
	}

	public static String getResourcePathAsString(String name) {
		ClassLoader classLoader = StreamerTest.class.getClassLoader();
		File file = new File(classLoader.getResource(name).getFile());

		return file.getAbsolutePath();
	}

}