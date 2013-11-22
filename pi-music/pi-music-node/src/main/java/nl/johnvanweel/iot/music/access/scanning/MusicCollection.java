package nl.johnvanweel.iot.music.access.scanning;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
public class MusicCollection {
    private String location;

    private List<MusicFile> musicFiles = createMusicList(new ArrayList<>());

    @PostConstruct
    public void readFiles() throws IOException {
        new MusicScannerThread(location, musicFiles).start();
    }

    public void setMusicList(List<MusicFile> files) {
        musicFiles = createMusicList(files);
    }

    private List<MusicFile> createMusicList(List<? extends MusicFile> content) {
        return Collections.synchronizedList(new ArrayList<>(content));
    }

    public MusicFile findOne(String audioQuery) {
        return findMusic(audioQuery).findAny().orElse(null);
    }

    public MusicFile findMusicByUUID(String uuid) {
        return musicFiles.parallelStream().filter(item -> item.getUuid().toString().equals(uuid)).findFirst().orElse(null);
    }

    public Stream<MusicFile> findMusic(String query) {
        return finder(query);
    }

    private Stream<MusicFile> finder(String query) {
        return musicFiles.parallelStream().filter(item -> matches(item, query));
    }

    private boolean matches(MusicFile item, String query) {
        return matchTitle(item, query) || matchArtist(item, query) || matchFileName(item, query);
    }

    private boolean matchTitle(MusicFile item, String query) {
        return item.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    private boolean matchArtist(MusicFile item, String query) {
        return item.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    private boolean matchFileName(MusicFile item, String query) {
        return item.getFilename().toLowerCase().contains(query.toLowerCase());
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
