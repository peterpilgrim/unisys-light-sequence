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

import java.util.Arrays;
import java.util.List;

/**
 * A unit test {@link LightContainerTest} to verify the operation of {@link LightContainer}
 *
 * @author Peter Pilgrim
 */
public class LightContainerTest {

    private final static int NUMBER_OF_LIGHTS = 20;

    @Test
    public void create_container_with_light_elements() {
        final LightContainer container = new LightContainer(NUMBER_OF_LIGHTS);
        assertThat(container.size(), is(NUMBER_OF_LIGHTS));
    }

    @Test
    public void create_container_with_alternating_colours() {
        final LightContainer container = new LightContainer(NUMBER_OF_LIGHTS);
        assertThat(container.size(), is(NUMBER_OF_LIGHTS));

        final List<FairyLight.Colour> alternateColours = Arrays.asList(FairyLight.Colour.RED, FairyLight.Colour.GREEN, FairyLight.Colour.WHITE);
        int colourCounter = 0;

        for (int j = 0; j < NUMBER_OF_LIGHTS; ++j) {
            assertThat(container.getLight(j).getColour(), is(alternateColours.get(colourCounter)));
            colourCounter = (colourCounter + 1) % alternateColours.size();
        }
    }


    @Test
    public void create_container_with_off_light_elements() {
        final LightContainer container = new LightContainer(NUMBER_OF_LIGHTS);
        assertThat(container.size(), is(NUMBER_OF_LIGHTS));

        for (int j = 0; j < NUMBER_OF_LIGHTS; ++j) {
            assertThat(container.getLight(j).isLit(), is(false));
        }
    }

    @Test
    public void create_container_and_then_switch_on_all_elements() {
        final LightContainer container = new LightContainer(NUMBER_OF_LIGHTS);
        assertThat(container.size(), is(NUMBER_OF_LIGHTS));

        for (int j = 0; j < NUMBER_OF_LIGHTS; ++j) {
            container.getLight(j).setLit(true);
            assertThat(container.getLight(j).isLit(), is(true));
        }
    }

}

