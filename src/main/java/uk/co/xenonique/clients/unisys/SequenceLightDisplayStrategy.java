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
 * The type {@link SequenceLightDisplayStrategy}
 *
 * @author Peter Pilgrim (peter)
 */
public class SequenceLightDisplayStrategy extends AbstractLightDisplayStrategy {

    public static final int DEFAULT_PULSE_LENGTH = 500;
    private int bulbCount;

    public SequenceLightDisplayStrategy(final LightContainer container) {
        super(container);
    }

    @Override
    public void nextSequence() {
        ++sequenceCount;
        final FairyLight light0 = container.getLight(bulbCount);
        if (!light0.isLit()) {
            light0.setLit(true);
        } else {
            light0.setLit(false);
            bulbCount = (bulbCount + 1) % container.size();
            final FairyLight light = container.getLight(bulbCount);
            light.setLit(true);
        }
    }

    @Override
    public int getPulseLength() {
        return DEFAULT_PULSE_LENGTH;
    }
}
