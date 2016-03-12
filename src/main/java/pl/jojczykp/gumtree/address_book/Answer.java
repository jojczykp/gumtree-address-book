package pl.jojczykp.gumtree.address_book;

public final class Answer {

	private final String label;
	private final Object value;

	public Answer(String label, Object value) {
		this.label = label;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return label + ": " + value;
	}
}
