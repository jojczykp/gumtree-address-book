package pl.jojczykp.gumtree.address_book;

import java.util.stream.Stream;

public class Application {

	private Streamer streamer;
	private Processor processor;

	public Application(Streamer streamer, Processor processor) {
		this.streamer = streamer;
		this.processor = processor;
	}

	public static void main(String args[]) {
		if (args.length != 1) {
			printUsage();
		} else {
			Application application = new Application(new Streamer(), new Processor());
			application.run(args[0]);
		}
	}

	private static void printUsage() {
		System.err.println("Error: Wrong number of arguments.");
		System.err.println("Usage: java -jar gumtree-address-book.jar <address book file name>");
	}

	void run(String pathToFile) {
		try (Stream<String> stream = streamer.stream(pathToFile)) {
			long result = processor.process(stream);

			System.out.println(result);
			System.out.println();
		}
	}

}
