package pl.jojczykp.gumtree.address_book;

import java.util.List;
import java.util.stream.Stream;

public class Application {

	private final Streamer streamer;
	private final Processor processor;
	private final Config config;
	private final ResultFormatter resultFormatter;

	public Application(Streamer streamer, Processor processor,
						Config config, ResultFormatter resultFormatter) {
		this.streamer = streamer;
		this.processor = processor;
		this.config = config;
		this.resultFormatter = resultFormatter;
	}

	public static void main(String args[]) {
		if (args.length != 1) {
			printUsage();
		} else {
			Application application = new Application(new Streamer(), new Processor(),
														new Config(), new ResultFormatter());
			application.run(args[0]);
		}
	}

	private static void printUsage() {
		System.err.println("Error: Wrong number of arguments.");
		System.err.println("Usage: java -jar gumtree-address-book.jar <address book file name>");
	}

	void run(String pathToFile) {
		try (Stream<String> stream = streamer.stream(pathToFile)) {
			List<Answer> result = processor.process(stream, config.allQuestions());

			System.out.println(resultFormatter.format(result));
		}
	}

}
