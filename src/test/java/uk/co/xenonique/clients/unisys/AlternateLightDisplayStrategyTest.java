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
 * A unit test AlternateLightDisplayStrategyTest to verify the operation of AlternateLightDisplayStrategyTest
 *
 * @author Peter Pilgrim
 */
public class AlternateLightDisplayStrategyTest {

    private final static int NUMBER_OF_LIGHTS = 10;

    private LightContainer container;

    @Before
    public void setup() {
        container = new LightContainer(NUMBER_OF_LIGHTS);
    }

    @Test
    public void alternate_strategy() {
        final int sequence_strategy_count = 2;
        final int colour_strategy_count = 3;

        final AlternateLightDisplayStrategy strategy = new AlternateLightDisplayStrategy(container, sequence_strategy_count, colour_strategy_count);

        for (int k = 0; k < sequence_strategy_count; ++k) {
            assertThat(strategy.getRunningName(), is(AlternateLightDisplayStrategy.RUNNING_STRATEGY_SEQUENCE));
            assertThat(strategy.getPulseLength(), is(SequenceLightDisplayStrategy.DEFAULT_PULSE_LENGTH));
            strategy.nextSequence();
        }
        for (int k = 0; k < colour_strategy_count; ++k) {
            assertThat(strategy.getRunningName(), is(AlternateLightDisplayStrategy.RUNNING_STRATEGY_COLOUR));
            assertThat(strategy.getPulseLength(), is(ColourLightDisplayStrategy.DEFAULT_PULSE_LENGTH));
            strategy.nextSequence();
        }

        // Did we alternate back to the beginning again?
        assertThat(strategy.getPulseLength(), is(SequenceLightDisplayStrategy.DEFAULT_PULSE_LENGTH));
        assertThat(strategy.getRunningName(), is(AlternateLightDisplayStrategy.RUNNING_STRATEGY_SEQUENCE));
    }

    @Test
    public void all_lights_should_be_reset() {
        CommonTestUtility.assertLightDisplayReset(container,
                new AlternateLightDisplayStrategy(container, 10, 5));
    }

}


