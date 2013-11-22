package nl.johnvanweel.iot.music.access.scanning;

import java.nio.file.Path;
import java.util.UUID;

/**
 * User: John van Weel
 * Date: 1/16/12
 * Time: 9:29 PM
 */
public class MusicFile {
    private final UUID uuid;
    private final Path location;
    private final String artist;
    private final String title;
    private final String filename;

    public MusicFile(Path location, String artist, String title, String filename) {
        this.location = location;
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        this.uuid = UUID.randomUUID();
    }

    public Path getLocation() {
        return location;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "MusicFile{" +
                "location=" + location +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
