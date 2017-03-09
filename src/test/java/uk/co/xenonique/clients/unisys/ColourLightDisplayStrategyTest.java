/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.clients.unisys;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

import java.util.Random;


/**
 * The type ColourLightDisplayStrategyTest
 *
 * @author Peter Pilgrim (peter)
 */
public class ColourLightDisplayStrategyTest {

    private final static int NUMBER_OF_LIGHTS = 10;

    private LightContainer container;

    @Before
    public void setup() {
        container = new LightContainer(NUMBER_OF_LIGHTS);
    }

    @Test
    public void all_light_bulbs_initially_switch_to_red() {

        final LightDisplayStrategy strategy = new ColourLightDisplayStrategy(container);

        strategy.nextSequence();

        assertSwitchOn(container, FairyLight.Colour.RED);
        assertSwitchOff(container, FairyLight.Colour.GREEN);
        assertSwitchOff(container, FairyLight.Colour.WHITE);
    }


    @Test
    public void life_cycle_complete() {

        final LightDisplayStrategy strategy = new ColourLightDisplayStrategy(container);

        strategy.nextSequence();

        assertSwitchOn(container, FairyLight.Colour.RED);
        assertSwitchOff(container, FairyLight.Colour.GREEN);
        assertSwitchOff(container, FairyLight.Colour.WHITE);

        strategy.nextSequence();

        assertSwitchOff(container, FairyLight.Colour.RED);
        assertSwitchOn(container, FairyLight.Colour.GREEN);
        assertSwitchOff(container, FairyLight.Colour.WHITE);

        strategy.nextSequence();

        assertSwitchOff(container, FairyLight.Colour.RED);
        assertSwitchOff(container, FairyLight.Colour.GREEN);
        assertSwitchOn(container, FairyLight.Colour.WHITE);

        strategy.nextSequence();

        assertSwitchOn(container, FairyLight.Colour.RED);
        assertSwitchOff(container, FairyLight.Colour.GREEN);
        assertSwitchOff(container, FairyLight.Colour.WHITE);

    }

    @Test
    public void all_lights_should_be_reset() {
        CommonTestUtility.assertLightDisplayReset(container, new ColourLightDisplayStrategy(container));
    }

    private void assertSwitchOn(final LightContainer container, final FairyLight.Colour colour) {
        for (int j = 0; j < container.size(); ++j) {
            if (container.getLight(j).getColour() == colour) {
                assertThat(String.format("Expected light bulb [%s] to be switched ON! (lightIndex:%d)",
                        colour, j),
                        container.getLight(j).isLit(), is(true));
            }
        }
    }

    private void assertSwitchOff(final LightContainer container, final FairyLight.Colour colour) {
        for (int j = 0; j < container.size(); ++j) {
            if (container.getLight(j).getColour() == colour) {
                assertThat(String.format("Expected light bulb [%s] to be switched OFF! (lightIndex:%d)",
                        colour, j),
                        container.getLight(j).isLit(), is(false));
            }
        }
    }

}
