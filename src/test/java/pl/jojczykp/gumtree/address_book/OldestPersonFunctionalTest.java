package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class OldestPersonFunctionalTest {

	private Processor testee = new Processor();
	private List<Question> questions = singletonList(new Config().oldestPerson());

	@Test
	public void shouldFindOldest() {
		Stream<String> input = Stream.of(
				"Bill McKnight, Male, 16/03/77",
				"Paul Robinson, Male, 15/01/85",
				"Gemma Lane, Female, 20/11/91",
				"Sarah Stone, Female, 20/09/80",
				"Wes Jackson, Male, 14/08/74");

		List<Answer> answers = testee.process(input, questions);

		assertThat(answers.get(0).getValue(), is(equalTo("Wes Jackson")));
	}

	@Test
	public void shouldNotFindOldestIfEmptyData() {
		Stream<String> input = Arrays.stream(new String[0]);

		List<Answer> answers = testee.process(input, questions);

		assertThat(answers.get(0).getValue(), is(equalTo("N/A")));
	}

}
