public class Avenger implements Comparable<Avenger> {

	// Initialize variables
	private String heroName;
	private String heroAlias;
	private int frequency;
	private int mentionIndex;

	/**
	 * Constructor for Avenger class
	 * @param alias - String input
	 * @param name  - String input
	 */
	public Avenger(String alias, String name) {
		heroName = name;
		heroAlias = alias;
		frequency = 0;
		mentionIndex = -1;
	}

	/**
	 * Comparable override to sort object alphabetically
	 */
	@Override
	public int compareTo(Avenger a) {
		return this.heroAlias.compareTo(a.getHeroAlias());
	}

	/*
	 * Override equals method that must return true if two Avenger objects have the
	 * same alias
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		if (this.getHeroAlias() == ((Avenger) o).getHeroAlias()) {
			return true;
		}
		Avenger p = (Avenger) o;
		return this.getHeroAlias().equals(p.getHeroAlias());
	}

	// Getter for frequency
	public int getFrequency() {
		return frequency;
	}

	// Getter for hero name
	public String getHeroName() {
		return heroName;
	}

	// Getter for hero alias
	public String getHeroAlias() {
		return heroAlias;
	}

	// Increase frequency
	public void addFrequency() {
		frequency++;
	}
	
	public int getMentionIndex() {
		return mentionIndex;
	}
	
	public void setMentionIndex(int mentionIndex) {
		this.mentionIndex = mentionIndex;
	}

	/*
	 * String override to a specific format
	 */
	@Override
	public String toString() {
		return heroAlias + " aka " + heroName + " mentioned " + frequency + " time(s)";
	}
}
