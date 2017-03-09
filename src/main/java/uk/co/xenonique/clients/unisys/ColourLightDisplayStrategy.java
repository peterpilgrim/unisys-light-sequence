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
 * The type ColourLightDisplayStrategy
 *
 * @author Peter Pilgrim (peter)
 */
public class ColourLightDisplayStrategy extends AbstractLightDisplayStrategy {

    public static final int DEFAULT_PULSE_LENGTH = 1000;
    private final List<FairyLight.Colour> colourList = new ArrayList<FairyLight.Colour>(Arrays.asList(FairyLight.Colour.values()));

    public ColourLightDisplayStrategy(LightContainer container) {
        super(container);
    }

    @Override
    public void nextSequence() {
        final FairyLight.Colour colour = colourList.get(sequenceCount % colourList.size());

        for (int j = 0; j < container.size(); ++j) {
            final FairyLight light = container.getLight(j);
            light.setLit(light.getColour() == colour);
        }

        ++sequenceCount;
    }

    @Override
    public int getPulseLength() {
        return DEFAULT_PULSE_LENGTH;
    }
}
