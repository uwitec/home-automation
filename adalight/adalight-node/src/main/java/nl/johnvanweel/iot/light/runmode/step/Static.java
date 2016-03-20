package nl.johnvanweel.iot.light.runmode.step;

import nl.johnvanweel.iot.light.runmode.filter.FilteredLightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Loop through the spectrum
 */
public class Static implements Steppable {
    private final FilteredLightService filteredLightService;

    private int[] color = new int[]{255, 255, 255};

    @Autowired
    public Static(final FilteredLightService filteredLightService) {
        this.filteredLightService = filteredLightService;
    }

    @Override
    public void step() {


        filteredLightService.allPixels(color[0], color[1], color[2]);
    }

    public List<String> getFilters() {
        return filteredLightService.getLightFilterNames();
    }

    public void toggleFilter(String name) {
        filteredLightService.toggleFilter(name);
    }
}
