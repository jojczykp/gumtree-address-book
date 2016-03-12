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
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorUnitTest {

	private Processor testee = new Processor();

	@Mock private Config config;

	@Test
	public void shouldEvaluateAllCollectors() {
		String[] input = {"Bill McKnight, Male, 16/03/77", "Paul Robinson, Male, 15/01/85"};
		List<Question> questions = asList(
				new Question("label1", Collectors.counting()),
				new Question("label2", Collectors.mapping(Record::getName, Collectors.joining(", "))));

		List<Answer> answers = testee.process(Stream.of(input), questions);

		assertThat(answers.size(), is(2));
		assertThat(answers.get(0).getValue(), is(2L));
		assertThat(answers.get(1).getValue(), is("Bill McKnight, Paul Robinson"));
	}

}
