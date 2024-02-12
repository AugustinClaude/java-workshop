package fr.epita.assistants.cinematheque;

import java.time.Duration;
import java.time.LocalDate;

public class Movie {
    final private String title;
    final private String director;
    final private LocalDate release;
    final private Duration duration;

    public Movie(String title, String director, LocalDate release, Duration duration) {
        if (title == null || director == null || release == null || duration == null)
            throw new NullPointerException();
        this.title = title;
        this.director = director;
        this.release = release;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getRelease() {
        return release;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Movie movie))
            return false;
        return this.title.equals(movie.title) && this.director.equals(movie.director) && this.release.equals(movie.release) && this.duration.equals(movie.duration);
    }

    @Override
    public String toString() {
        return "Movie(title=" + title + ", director=" + director + ", release=" + release + ", duration=" + duration + ")";
    }
}
