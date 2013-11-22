package nl.johnvanweel.iot.light.sunset;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *
 */
public class ScraperSunsetService implements SunsetService {

    public static void main(String... args) {
        new ScraperSunsetService().getSunsetTime();
    }

    @Override
    public long getSunsetTime() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.zonsondergang-tijden.nl/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements sunsetElement = doc.select("#sunset");

        System.out.println(new DateTime().millisOfDay().getDateTime().getMillis());
        return 0;
    }
}
