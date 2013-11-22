package nl.johnvanweel.iot.music;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 *
 */
public abstract class SimpleBaseMatcher<T> extends BaseMatcher<T> {
    @Override
    public void describeTo(Description description) {

    }
}
