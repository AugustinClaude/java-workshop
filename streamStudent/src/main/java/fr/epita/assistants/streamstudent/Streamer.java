package fr.epita.assistants.streamstudent;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Streamer {
    public Stream<Pair<Integer, String>> validator(Stream<Pair<Integer, String>> stream) {
        return stream.filter(s -> s.getKey() >= 0 && s.getKey() <= 100 && (s.getValue().matches("^[^._]*_[^._]*$") || s.getValue().matches("^[^._]*\\.[^._]*$")));
    }

    public Stream<Pair<Integer, String>> orderGrade(Stream<Pair<Integer, String>> stream) {
        return stream.sorted((p1, p2) -> {
            if (p1.getKey().equals(p2.getKey()))
                return p1.getValue().compareTo(p2.getValue());
            return p1.getKey().compareTo(p2.getKey());
        });
    }

    public Stream<Pair<Integer, String>> lowercase(Stream<Pair<Integer, String>> stream) {
        return stream.map(p -> {
            if (p.getValue().matches(".*[A-Z]+.*"))
                return new Pair<>(p.getKey() / 2, p.getValue().toLowerCase());
            return p;
        });
    }

    public Optional<Pair<Integer, String>> headOfTheClass(Stream<Pair<Integer, String>> stream) {
        return stream.sorted((p1, p2) -> {
            if (p1.getKey().equals(p2.getKey()))
                return p1.getValue().compareTo(p2.getValue());
            return p2.getKey().compareTo(p1.getKey());
        }).findFirst();
    }

    public Stream<Pair<Integer, String>> quickFix(Stream<Pair<Integer, String>> stream) {
        return stream.map(p -> {
            if (p.getValue().toLowerCase().startsWith("ma") || (p.getValue().toLowerCase().startsWith("l") && p.getValue().toLowerCase().endsWith("x")))
                return new Pair<>(Math.min(p.getKey() * 2, 100), p.getValue());
            return p;
        });
    }

    public Stream<Pair<Integer, String>> encryption(Stream<Pair<Integer, String>> stream) {
        return stream.map(p -> {
            int len = p.getValue().length();
            String first = p.getValue().substring(0, len / 2);
            String second = p.getValue().substring(len / 2);
            return new Pair<>(p.getKey(), second + first);
        });
    }
}
