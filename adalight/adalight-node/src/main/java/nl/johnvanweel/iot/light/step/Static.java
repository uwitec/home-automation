package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Loop through the spectrum
 */
public class Static implements Steppable {
	private final ILightService ILightService;

	private int[] color = new int[]{255, 255, 255};
	private int percentage;

	@Autowired
	public Static(final ILightService ILightService) {
		this.ILightService = ILightService;
	}

	@Override
	public void step() {
		final int red = (color[0] * percentage) / 100;
		final int green =(color[1] * percentage) / 100;
		final int blue = (color[2] * percentage) / 100;

		ILightService.allPixels(red, green, blue);
	}

	public void setColor(final int[] color) {
		this.color = color;
	}

	public void setPercentage(final int percentage) {
		this.percentage = percentage;
	}
}
