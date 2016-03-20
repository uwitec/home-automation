package nl.johnvanweel.iot.light.runmode.filter;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilteredLightService {
    @Autowired
    private ILightService lightService;

    private List<LightFilter> lightFilters;


    @Autowired
    public FilteredLightService(List<LightFilter> lightFilters) {
        this.lightFilters = lightFilters;
    }


    public void setPixel(int pixelNumber, int red, int green, int blue) {
        final int[][] newValues = executeFilters(red, green, blue);

        lightService.setPixel(pixelNumber, newValues[0][0], newValues[0][1], newValues[0][2]);
    }

    public void allPixels(int red, int green, int blue) {
        final int[][] newValues = executeFilters(red, green, blue);

        lightService.allPixels(newValues[0][0], newValues[0][1], newValues[0][2]);
    }

    private int[][] executeFilters(int red, int green, int blue) {
        final int[][] newValues = {new int[]{red, green, blue}};

        lightFilters.stream().filter(filter -> filter.isActive()).forEach(filter -> {
            newValues[0] = filter.filter(newValues[0][0], newValues[0][1], newValues[0][2]);
        });
        return newValues;
    }


    public void send() {
        lightService.send();
    }

    public List<String> getLightFilterNames() {
        return lightFilters.stream().map(LightFilter::getName).collect(Collectors.toList());
    }
}
