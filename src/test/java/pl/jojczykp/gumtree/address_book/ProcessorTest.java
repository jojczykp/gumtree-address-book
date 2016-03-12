package pl.jojczykp.gumtree.address_book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorTest {

	private Processor testee = new Processor();

	@Mock private Queries queries;

	@Test
	public void shouldEvaluateAllCollectors() {
		String[] input = {"Bill McKnight, Male, 16/03/77", "Paul Robinson, Male, 15/01/85"};
		when(queries.getQueries()).thenReturn(asList(
				Collectors.counting(),
				Collectors.mapping(Record::getName, Collectors.joining(", "))));

		List<?> result = testee.process(Stream.of(input), queries);

		assertThat(result, contains((long) input.length, "Bill McKnight, Paul Robinson"));
	}

}
