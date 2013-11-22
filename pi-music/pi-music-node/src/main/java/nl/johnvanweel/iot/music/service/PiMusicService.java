package nl.johnvanweel.iot.music.service;

import nl.johnvanweel.iot.music.business.MusicSearchBusiness;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class PiMusicService {
    private final MusicSearchBusiness searchBusiness;

    @Autowired
    public PiMusicService(MusicSearchBusiness searchBusiness) {
        this.searchBusiness = searchBusiness;
    }

    public void search(String query) {
        searchBusiness.searchCluster(query);
    }
}
