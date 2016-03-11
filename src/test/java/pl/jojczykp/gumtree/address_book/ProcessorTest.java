package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProcessorTest {

	private Processor testee = new Processor();

	@Test
	public void shouldCountMales() {
		Stream<String> input = Arrays.stream(new String[] {
				"Bill McKnight, Male, 16/03/77",
				"Sarah Stone, Female, 20/09/80",
				"Paul Robinson, Male, 15/01/85"});

		long result = testee.process(input);

		assertThat(result, is(2L));
	}

}