package pl.jojczykp.gumtree.address_book;

import java.util.List;
import java.util.stream.Stream;

public class Application {

	private Streamer streamer;
	private Processor processor;
	private Config config;

	public Application(Streamer streamer, Processor processor, Config config) {
		this.streamer = streamer;
		this.processor = processor;
		this.config = config;
	}

	public static void main(String args[]) {
		if (args.length != 1) {
			printUsage();
		} else {
			Application application = new Application(new Streamer(), new Processor(), new Config());
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

			System.out.println(result);
			System.out.println();
		}
	}

}
