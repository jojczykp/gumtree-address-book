package pl.jojczykp.gumtree.address_book;

public final class Answer {

	private final String label;
	private final Object value;

	public Answer(String label, Object value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public Object getValue() {
		return value;
	}

}
