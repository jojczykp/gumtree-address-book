package pl.jojczykp.gumtree.address_book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Processor {

	public List<Answer> process(Stream<String> stream, List<Question> questions) {
		return stream
				.map(Record::new)
				.collect(Collector.of(
						() -> new Accumulator(questions),
						Accumulator::consume,
						Accumulator::merge,
						Accumulator::finisher
				));
	}

	private static class Accumulator {

		private List<Question> questions;
		private List<Collector<Record, Object, ?>> collectors;
		private List<Object> data;

		public Accumulator(List<Question> questions) {
			this.questions = questions;
			this.collectors = questions.stream().map(q -> cast(q.getCollector())).collect(toList());
			this.data = collectors.stream()
					.map(collector -> collector.supplier().get())
					.collect(toList());
		}

		public void consume(Record r) {
			for (int i = 0 ; i < data.size() ; i++) {
				collectors.get(i).accumulator().accept(data.get(i), r);
			}
		}

		public Accumulator merge(Accumulator other) {
			for (int i = 0 ; i < data.size() ; i++) {
				data.set(i, collectors.get(i).combiner().apply(data.get(i), other.data.get(i)));
			}

			return this;
		}

		public List<Answer> finisher() {
			List<Answer> results = new ArrayList<>(collectors.size());

			for (int i = 0 ; i < data.size() ; i++) {
				results.add(new Answer(questions.get(i).getLabel(),
						collectors.get(i).finisher().apply(data.get(i))));
			}

			return results;
		}

		@SuppressWarnings("unchecked")
		private Collector<Record, Object, ?> cast(Collector<Record, ?, ?> collector) {
			return (Collector<Record, Object, ?>) collector;
		}

	}

}
