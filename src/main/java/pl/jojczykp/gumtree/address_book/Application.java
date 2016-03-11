package pl.jojczykp.gumtree.address_book;

import java.util.stream.Stream;

public class Application {

	private void run(String[] args) {
		if (args.length != 1) {
			printUsage();
		} else {
			process(args[0]);
		}
	}

	private void printUsage() {
		System.err.println("Error: Wrong number of arguments.");
		System.err.println("Usage: java -jar gumtree-address-book.jar <address book file name>");
	}

	private void process(String pathToFile) {
		Streamer streamer = new Streamer();
		Processor processor = new Processor();

		Stream<String> stream = streamer.stream(pathToFile);
		String result = processor.process(stream);

		System.out.println(result);
		System.out.println();
	}

	public static void main(String args[]) {
		Application application = new Application();
		application.run(args);
	}

}
