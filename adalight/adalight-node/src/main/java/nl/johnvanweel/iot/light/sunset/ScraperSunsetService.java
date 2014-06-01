package nl.johnvanweel.iot.light.sunset;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class ScraperSunsetService implements SunsetService {
    private final Logger logger = Logger.getLogger(ScraperSunsetService.class);

    private static final Pattern format = Pattern.compile("(\\d{2}):(\\d{2})");
    private DateTime sunsetTime;

    public static void main(String... args) {
        System.out.println(new ScraperSunsetService().getSunState());
    }

    @Override
    public SunState getSunState() {
        DateTime now = new DateTime();

        DateTime sunsetDoneTime = sunsetTime.plusMinutes(30);
        if (now.isAfter(sunsetTime) && now.isBefore(sunsetDoneTime)) {
            return SunState.SETTING;
        } else if (now.isAfter(sunsetDoneTime)) {
            return SunState.DOWN;
        }

        return SunState.UP;
    }

    @PostConstruct
    public void findTimes() {
        DateTime now = new DateTime();
        LocalDate today = now.toLocalDate();

        DateTime startOfToday = today.toDateTimeAtStartOfDay(now.getZone());

        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.zonsondergang-tijden.nl/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Matcher timeMatcher = format.matcher(doc.select("#sunset").first().html());
        int hours = 0;
        int minutes = 0;
        if (timeMatcher.find()) {
            hours = Integer.parseInt(timeMatcher.group(1));
            minutes = Integer.parseInt(timeMatcher.group(2));
        }

        sunsetTime = startOfToday.plusHours(hours).plusMinutes(minutes);

        logger.info("Next sunset on " + sunsetTime.toDate());
    }

    @Override
    public double getTransitionPercentage() {
        return 100;
    }
}
