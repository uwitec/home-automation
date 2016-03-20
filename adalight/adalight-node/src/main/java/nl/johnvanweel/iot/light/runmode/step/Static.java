package nl.johnvanweel.iot.light.runmode.step;

import nl.johnvanweel.iot.light.runmode.filter.FilteredLightService;
import nl.johnvanweel.iot.light.runmode.filter.LightFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Loop through the spectrum
 */
public class Static implements Steppable {
	private final FilteredLightService filteredLightService;

	private int[] color = new int[]{255, 255, 255};
	private int percentage = 100;

	@Autowired
	public Static(final FilteredLightService filteredLightService) {
		this.filteredLightService = filteredLightService;
	}

	@Override
	public void step() {
		final int red = (color[0] * percentage) / 100;
		final int green =(color[1] * percentage) / 100;
		final int blue = (color[2] * percentage) / 100;

		filteredLightService.allPixels(red, green, blue);
	}

	public void setPercentage(final int percentage) {
		this.percentage = percentage;
	}

	public List<String> getFilters() {
		return filteredLightService.getLightFilterNames();
	}

	public void toggleFilter(String name) {
		filteredLightService.toggleFilter(name);
	}
}
