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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type LightContainer
 *
 * @author Peter Pilgrim (peter)
 */
public class LightContainer {

    private List<FairyLight> fairyLights;

    public LightContainer(final int capacity) {
        fairyLights = new ArrayList<>();

        final List<FairyLight.Colour> bulbColour = new ArrayList<FairyLight.Colour>(Arrays.asList(FairyLight.Colour.values()));
        int counter = 0;

        for (int j = 0; j < capacity; ++j) {
            fairyLights.add(new FairyLight(bulbColour.get(counter)));
            counter = (counter + 1) % bulbColour.size();
        }
    }

    public int size() {
        return fairyLights.size();
    }

    public FairyLight getLight(int j) {
        return fairyLights.get(j);
    }
}
