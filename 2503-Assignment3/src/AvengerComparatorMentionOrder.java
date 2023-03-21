import java.util.Comparator;

public class AvengerComparatorMentionOrder implements Comparator<Avenger> {

	@Override
	public int compare(Avenger o1, Avenger o2) {
		return o1.getMentionIndex() - o2.getMentionIndex();
	}
}
