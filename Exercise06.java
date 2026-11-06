package chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class Exercise06 {

	// Samples
	private static final Concert CONCERT = new Concert("Concert 1", "Artist X", 100);
	private static final Movie MOVIE1 = new Movie("Movie 1", 2017, 120);
	private static final Movie MOVIE2 = new Movie("Movie 2", 2018, 130);

	public static void main(String[] args) {
		// Sample extraction of inner shows from a Composite Show
		CompositeShow2 show = new CompositeShow2(CONCERT, new CompositeShow2(MOVIE1, MOVIE2));
		if (show instanceof CompositeShow2 compositeShow) {
			for (Show subshow : compositeShow) {
				IO.println(subshow.description());
			}
		}
	}
}

class CompositeShow2 implements Show, Iterable<Show> {

	private List<Show> aShows = new ArrayList<>();

	public CompositeShow2(Show... pShows) {
		if (pShows.length < 2 || pShows.length > 5) {
			throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
		}
		aShows.addAll(Arrays.asList(pShows));
	}

	public Iterator<Show> iterator() {
		return Collections.unmodifiableList(aShows).iterator();
	}

	public String description() {
		StringJoiner description = new StringJoiner("; ", "[", "]");
		for (Show show : aShows) {
			description.add(show.description());
		}
		return description.toString();
	}

	public int runningTime() {
		int time = 0;
		for (Show show : aShows) {
			time += show.runningTime();
		}
		return time;
	}

	public Show copy() {
		List<Show> copies = new ArrayList<>();
		for (Show show : aShows) {
			copies.add(show.copy());
		}
		return new CompositeShow(copies.toArray(new Show[copies.size()]));
	}

	// Equals and hashCode are skipped for brevity
}