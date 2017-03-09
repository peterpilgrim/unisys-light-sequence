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

/**
 * A unit test {@link SequenceLightDisplayStrategyTest} to verify the operation of {@link SequenceLightDisplayStrategy}
 *
 * @author Peter Pilgrim
 */
public class SequenceLightDisplayStrategyTest {

    private final static int NUMBER_OF_LIGHTS = 10;

    private LightContainer container;

    @Before
    public void setup() {
        container = new LightContainer(NUMBER_OF_LIGHTS);
    }

    @Test
    public void first_light_switches_turn_on_and_then_off() {

        final LightDisplayStrategy sequence = new SequenceLightDisplayStrategy(container);

        sequence.nextSequence();

        assertThat(container.getLight(0).isLit(), is(true));
        assertThat(container.getLight(1).isLit(), is(false));
        assertThat(container.getLight(2).isLit(), is(false));

        sequence.nextSequence();

        assertThat(container.getLight(0).isLit(), is(false));
        assertThat(container.getLight(1).isLit(), is(true));
        assertThat(container.getLight(2).isLit(), is(false));

        sequence.nextSequence();

        assertThat(container.getLight(0).isLit(), is(false));
        assertThat(container.getLight(1).isLit(), is(false));
        assertThat(container.getLight(2).isLit(), is(true));
    }

    @Test
    public void generic_light_switches_turn_on_and_then_off() {

        final LightDisplayStrategy sequence = new SequenceLightDisplayStrategy(container);


        for (int j = 0; j < container.size(); ++j) {
            sequence.nextSequence();
            assertLightIndexSwitchedOn(container, j);
        }
    }

    private void assertLightIndexSwitchedOn(LightContainer container, int lightIndex) {

        assertThat(String.format("light index [%d] should be ON!", lightIndex),
                container.getLight(lightIndex).isLit(), is(true));

        for (int k = 0; k < container.size(); ++k) {
            if (k != lightIndex) {
                assertThat(String.format("light index [%d] should be OFF!", k),
                        container.getLight(k).isLit(), is(false));
            }
        }

    }

    @Test
    public void all_lights_should_be_reset() {
        CommonTestUtility.assertLightDisplayReset(container, new SequenceLightDisplayStrategy(container));
    }

}
