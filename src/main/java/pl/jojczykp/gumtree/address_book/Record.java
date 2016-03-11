package pl.jojczykp.gumtree.address_book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record {

	private static final Pattern PATTERN = Pattern.compile("^[^,]*, (?<gender>[^,]*), .*$");

	private Gender gender;

	public Record(String row) {
		Matcher matcher = PATTERN.matcher(row);
		if (matcher.find()) {
			gender = Gender.valueOf(matcher.group("gender").toUpperCase());
		} else {
			throw new IllegalArgumentException(row);
		}
	}

	public Gender getGender() {
		return gender;
	}

	public enum Gender {
		MALE, FEMALE
	}

}
