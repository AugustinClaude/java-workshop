package fr.epita.assistants.ministreamsmusic.dataaccess;

import fr.epita.assistants.ministreamsmusic.data.Artist;
import fr.epita.assistants.ministreamsmusic.data.Song;

import java.util.List;

public class StreamsSongs {
    public static List<String> getOlderArtists(List<Song> songs) {
        return songs.stream().map(Song::getArtist).filter(a -> a.getAge() >= 30).distinct().limit(10).map(Artist::getSurname).toList();
    }

    public static Integer getSumAges(List<Song> songs) {
        final int[] sum = {0};
        songs.stream().map(Song::getArtist).filter(a -> a.getAge() >= 20).forEach(a -> sum[0] += a.getAge());
        return sum[0];
    }

    public static List<String> getMusics(List<Song> songs) {
        return songs.stream().filter(s -> s.getArtist().getName().toLowerCase().contains("an")).limit(10).map(Song::getName).toList();
    }
}
