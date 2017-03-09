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

/**
 * The type LightDisplayStrategyFactory
 *
 * @author Peter Pilgrim (peter)
 */
public class LightDisplayStrategyFactory {
    public static LightDisplayStrategy createInstance(String sequence, final LightContainer container) {

        if ("Sequence".equalsIgnoreCase(sequence)) {
            return new SequenceLightDisplayStrategy(container);
        } else if ("Colour".equalsIgnoreCase(sequence)) {
            return new ColourLightDisplayStrategy(container);
        } else if ("Alternate".equalsIgnoreCase(sequence)) {
            return new AlternateLightDisplayStrategy(container, 60, 30);
        } else {
            throw new IllegalArgumentException("cannot create with unrecognised strategy algorithm name [" + sequence + "]");
        }
    }
}
