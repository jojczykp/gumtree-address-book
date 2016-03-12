package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BirthDelayFunctionalTest {

	private Processor testee = new Processor();
	private List<Question> questions = singletonList(new Config().birthDelay());

	@Test
	public void shouldCalculateBirthDelayIfOlderFirst() {
		Stream<String> input = Stream.of(
				"Bill McKnight, Male, 16/03/77",
				"Paul Robinson, Male, 15/01/85",
				"Gemma Lane, Female, 20/11/91");

		List<Answer> answers = testee.process(input, questions);

		assertThat(answers.get(0).getValue(), is(2862L));
	}

	@Test
	public void shouldCalculateBirthDelayIfYoungerFirst() {
		Stream<String> input = Stream.of(
				"Paul Robinson, Male, 15/01/85",
				"Gemma Lane, Female, 20/11/91",
				"Bill McKnight, Male, 16/03/77");

		List<Answer> answers = testee.process(input, questions);

		assertThat(answers.get(0).getValue(), is(2862L));
	}

}
