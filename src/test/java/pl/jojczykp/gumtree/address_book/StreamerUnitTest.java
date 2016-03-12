package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.io.File;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StreamerUnitTest {

	private static final String FILE_NAME = "AddressBook";
	private static final long NUM_OF_LINES = 5L;

	private Streamer testee = new Streamer();

	@Test
	public void shouldReadFile() {
		Stream<String> stream = testee.stream(getResourcePathAsString(FILE_NAME));

		long count = stream.count();

		assertThat(count, is(NUM_OF_LINES));
	}

	@Test(expected = RuntimeException.class)
	public void shouldWrapExceptionWithUnchecked() {
		Stream<String> stream = testee.stream("notExisting");

		stream.count();
	}

	public static String getResourcePathAsString(String name) {
		ClassLoader classLoader = StreamerUnitTest.class.getClassLoader();
		File file = new File(classLoader.getResource(name).getFile());

		return file.getAbsolutePath();
	}

}
