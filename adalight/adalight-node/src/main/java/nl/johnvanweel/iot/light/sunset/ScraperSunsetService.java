package nl.johnvanweel.iot.light.sunset;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 *
 */
public class ScraperSunsetService implements SunsetService {

    public static void main(String... args) {
        System.out.println(new ScraperSunsetService().getSunState());
    }

    @Override
    public SunState getSunState() {
        //        Document doc = null;
//        try {
//            doc = Jsoup.connect("http://www.zonsondergang-tijden.nl/").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(doc.select("#sunset").first().html());

        DateTime now = new DateTime();

        LocalDate today = now.toLocalDate();
        DateTime startOfToday = today.toDateTimeAtStartOfDay(now.getZone());
        DateTime sunsetTime = startOfToday.plusHours(21).plusMinutes(07);
        DateTime sunsetDoneTime = sunsetTime.plusMinutes(30);
        if (now.isAfter(sunsetTime) && now.isBefore(sunsetDoneTime)) {
            return SunState.SETTING;
        } else if (now.isAfter(sunsetDoneTime)) {
            return SunState.DOWN;
        }

        return SunState.UP;
    }

    @Override
    public double getTransitionPercentage() {
        return 0;
    }
}
