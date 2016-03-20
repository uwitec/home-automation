package nl.johnvanweel.iot.light;

import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.light.runmode.RunMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 3/20/16.
 */
@Component
public class LightCapabilityRunModeFactory {
    private final RunMode[] allModes;

    @Autowired
    public LightCapabilityRunModeFactory(RunMode[] allModes) {
        this.allModes = allModes;
    }

    public LightRunMode[] createLightRunModes() {
        List<LightRunMode> result = new ArrayList<>();

        for (RunMode runMode : allModes){
            result.add(new LightRunMode(runMode.identify()));
        }

        return result.toArray(new LightRunMode[result.size()]);
    }
}
