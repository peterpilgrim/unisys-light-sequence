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
 * The type {@link AbstractLightDisplayStrategy}
 *
 * @author Peter Pilgrim (peter)
 */
public abstract class AbstractLightDisplayStrategy implements LightDisplayStrategy {
    protected final LightContainer container;
    protected int sequenceCount = 0;

    public AbstractLightDisplayStrategy(final LightContainer container) {
        this.container = container;
    }

    @Override
    public int getSequenceCount() {
        return sequenceCount;
    }

    @Override
    public void reset() {
        sequenceCount = 0;
        for (int k = 0; k < container.size(); ++k) {
            container.getLight(k).setLit(false);
        }
    }


}
