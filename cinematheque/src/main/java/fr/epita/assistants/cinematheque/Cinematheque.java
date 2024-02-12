package fr.epita.assistants.cinematheque;

import java.io.PrintStream;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cinematheque {
    Stock<Movie> movieStock;
    Logger logger;

    public Cinematheque() {
        movieStock = new ListStock<>();
        logger = new Logger(System.out);
        movieStock.property.addPropertyChangeListener(logger);
    }

    public Cinematheque(PrintStream output) {
        movieStock = new ListStock<>();
        logger = new Logger(output);
        movieStock.property.addPropertyChangeListener(logger);
    }

    public boolean add(Movie movie) {
        if (movie == null)
            return false;
        return movieStock.add(movie);
    }

    public boolean remove(Movie movie) {
        return movieStock.remove(movie);
    }

    public boolean contains(Movie movie) {
        return movieStock.contains(movie);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Movie movie : movieStock.list())
            sb.append(movie).append("\n");
        return sb.toString();
    }

    public boolean sortByTitle() {
        Comparator<Movie> cmp = (o1, o2) -> {
            if (o1.equals(o2))
                return 0;

            if (o1.getTitle().compareTo(o2.getTitle()) != 0)
                return o1.getTitle().compareTo(o2.getTitle());
            if (o1.getDirector().compareTo(o2.getDirector()) != 0)
                return o1.getDirector().compareTo(o2.getDirector());
            if (!o1.getRelease().isEqual(o2.getRelease()))
                return o1.getRelease().compareTo(o2.getRelease());
            return o1.getDuration().compareTo(o2.getDuration());
        };

        return movieStock.sort(cmp);
    }

    public void banDirector(String director) {
        List<? extends Movie> listCopy = new ArrayList<>(movieStock.list().stream().toList());
        for (Movie movie : listCopy) {
            if (movie.getDirector().equals(director))
                movieStock.remove(movie);
        }
    }

    public Period datesAmplitude() {
        if (movieStock.list().size() == 0)
            return null;

        Movie min = movieStock.list().stream().min(Comparator.comparing(Movie::getRelease)).get();
        Movie max = movieStock.list().stream().max(Comparator.comparing(Movie::getRelease)).get();
        return Period.between(min.getRelease(), max.getRelease());
    }

    public Duration averageDuration() {
        if (movieStock.list().size() == 0)
            return Duration.ZERO;

        final Duration[] avg = {Duration.ZERO};
        movieStock.list().forEach(m -> avg[0] = avg[0].plus(m.getDuration()));
        long milliAvg = avg[0].toMillis() / movieStock.list().size();
        return Duration.ofMillis(milliAvg);
    }
}
