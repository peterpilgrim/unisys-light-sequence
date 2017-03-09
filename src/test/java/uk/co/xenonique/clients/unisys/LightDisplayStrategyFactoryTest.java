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
 * A unit test LightDisplayStrategyFactoryTest to verify the operation of LightDisplayStrategyFactoryTest
 *
 * @author Peter Pilgrim
 */
public class LightDisplayStrategyFactoryTest {

    private final static int NUMBER_OF_LIGHTS = 10;

    private LightContainer container;

    @Before
    public void setup() {
        container = new LightContainer(NUMBER_OF_LIGHTS);
    }

    @Test
    public void factory_create_sequence_strategy() {
        LightDisplayStrategy strategy = LightDisplayStrategyFactory.createInstance("sequence", container);
        assertThat(strategy instanceof SequenceLightDisplayStrategy, is(true));
    }

    @Test
    public void factory_create_colour_strategy() {
        LightDisplayStrategy strategy = LightDisplayStrategyFactory.createInstance("colour", container);
        assertThat(strategy instanceof ColourLightDisplayStrategy, is(true));
    }

    @Test
    public void factory_create_alternate_strategy() {
        LightDisplayStrategy strategy = LightDisplayStrategyFactory.createInstance("alternate", container);
        assertThat(strategy instanceof AlternateLightDisplayStrategy, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void factory_create_unknown_strategy() {
        LightDisplayStrategy strategy = LightDisplayStrategyFactory.createInstance("UNKNOWN", container);
    }
}
