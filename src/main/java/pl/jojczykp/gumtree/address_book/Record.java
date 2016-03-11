package pl.jojczykp.gumtree.address_book;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record {

	private static final Pattern PATTERN = Pattern.compile("^(?<name>[^,]*), (?<gender>[^,]*), (?<birth>.*)$");
	private static final DateTimeFormatter DATE_TIME_FORMATTER =
			new DateTimeFormatterBuilder().appendPattern("dd/MM/")
					.appendValueReduced(ChronoField.YEAR, 2, 2, Year.now().getValue() - 80).toFormatter();

	private Gender gender;
	private LocalDate birth;
	private String name;

	public Record(String row) {
		Matcher matcher = PATTERN.matcher(row);
		if (matcher.find()) {
			name = matcher.group("name");
			gender = Gender.valueOf(matcher.group("gender").toUpperCase());
			birth = LocalDate.parse(matcher.group("birth"), DATE_TIME_FORMATTER);
		} else {
			throw new IllegalArgumentException(row);
		}
	}

	public Gender getGender() {
		return gender;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public String getName() {
		return name;
	}

	public enum Gender {
		MALE, FEMALE
	}

}
