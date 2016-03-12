package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProcessorCountingMalesFunctionalTest {

	private Processor testee = new Processor();
	private Queries queries = new Queries();

	@Test
	public void shouldCountMales() {
		Stream<String> input = Stream.of(
				"Bill McKnight, Male, 16/03/77",
				"Sarah Stone, Female, 20/09/80",
				"Paul Robinson, Male, 15/01/85");

		List<?> result = testee.process(input, queries);

		assertThat(result.get(0), is(2));
	}

	@Test
	public void shouldCountZeroMales() {
		Stream<String> input = Stream.of(
				"Sarah Stone, Female, 20/09/80");

		List<?> result = testee.process(input, queries);

		assertThat(result.get(0), is(0));
	}

	@Test
	public void shouldCountZeroMalesIfEmptyData() {
		Stream<String> input = Arrays.stream(new String[0]);

		List<?> result = testee.process(input, queries);

		assertThat(result.get(0), is(0));
	}

}
