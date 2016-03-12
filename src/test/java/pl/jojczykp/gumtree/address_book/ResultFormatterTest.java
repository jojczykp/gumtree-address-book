package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResultFormatterTest {

	private ResultFormatter testee = new ResultFormatter();

	@Test
	public void shouldFormat() {
		List<Answer> answers = asList(new Answer("L1", 1), new Answer("L2", 2));

		String result = testee.format(answers);

		assertThat(result, is(equalTo(String.format("L1: 1%nL2: 2%n"))));
	}

}
