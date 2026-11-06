package chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class Exercise07 {

    private static final Concert2 CONCERT = new Concert2("Concert 1", "Artist X", 100);
    private static final Movie2 MOVIE1 = new Movie2("Movie 1", 2017, 120);
    private static final Movie2 MOVIE2 = new Movie2("Movie 2", 2018, 130);

    public static void main(String[] args) {

        Show2 show = new CompositeShow3(
                CONCERT,
                new CompositeShow3(MOVIE1, MOVIE2)
        );
        System.out.println("composite show:");
        System.out.println("Description: " + show.description());
        System.out.println("Running time: " + show.runningTime() + " minutes");

        System.out.println("\nsub show: ");

        for (Show2 subshow : show) {
            System.out.println(" - " + subshow.description()
                    + " (" + subshow.runningTime() + " minutes)");
        }

        Show2 copiedShow = show.copy();

        System.out.println("\ncopy: ");
        System.out.println("Description: " + copiedShow.description());
        System.out.println("Running time: " + copiedShow.runningTime() + " minutes");

        for (Show2 subshow : copiedShow) {
            System.out.println(" - " + subshow.description());
        }
    }
}

interface Show2 extends Iterable<Show2> {

    String description();

    int runningTime();

    Show2 copy();

    default Iterator<Show2> iterator() {
        return Collections.emptyIterator();
    }
}

class Movie2 implements Show2 {

    private final String aTitle;
    private final int aYear;
    private final int aRunningTime;

    Movie2(String pTitle, int pYear, int pRunningTime) {
        aTitle = pTitle;
        aYear = pYear;
        aRunningTime = pRunningTime;
    }

    @Override
    public String description() {
        return aTitle + " (" + aYear + ")";
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }

    @Override
    public Show2 copy() {
        return new Movie2(aTitle, aYear, aRunningTime);
    }
}

class Concert2 implements Show2 {

    private final String aTitle;
    private final String aArtist;
    private final int aRunningTime;

    Concert2(String pTitle, String pArtist, int pRunningTime) {
        aTitle = pTitle;
        aArtist = pArtist;
        aRunningTime = pRunningTime;
    }

    @Override
    public String description() {
        return aTitle + " by " + aArtist;
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }

    @Override
    public Show2 copy() {
        return new Concert2(aTitle, aArtist, aRunningTime);
    }
}

class CompositeShow3 implements Show2 {

    private List<Show2> aShows = new ArrayList<>();

    public CompositeShow3(Show2... pShows) {
        if (pShows.length < 2 || pShows.length > 5) {
            throw new IllegalArgumentException(
                    "Arguments should be between two and five shows inclusively");
        }

        aShows.addAll(Arrays.asList(pShows));
    }

    @Override
    public Iterator<Show2> iterator() {
        return Collections.unmodifiableList(aShows).iterator();
    }

    @Override
    public String description() {
        StringJoiner description = new StringJoiner("; ", "[", "]");

        for (Show2 show : aShows) {
            description.add(show.description());
        }

        return description.toString();
    }

    @Override
    public int runningTime() {
        int time = 0;

        for (Show2 show : aShows) {
            time += show.runningTime();
        }

        return time;
    }

    @Override
    public Show2 copy() {
        List<Show2> copies = new ArrayList<>();

        for (Show2 show : aShows) {
            copies.add(show.copy());
        }

        return new CompositeShow3(copies.toArray(new Show2[0]));
    }
}