package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AnswerTest {

	private static final String LABEL = "Some Label";
	private static final Integer VALUE = 5;

	@Test
	public void shouldConvertToString() {
		Answer answer = new Answer(LABEL, VALUE);

		String string = answer.toString();

		assertThat(string, is(equalTo(LABEL + ": " + VALUE)));
	}

}
