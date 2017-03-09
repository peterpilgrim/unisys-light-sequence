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


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * The type CommonTestUtility
 *
 * @author Peter Pilgrim (peter)
 */
public class CommonTestUtility {

    public static void assertLightDisplayReset(LightContainer container, LightDisplayStrategy strategy) {
        final int N = (int) (3 + Math.random() * 100);
        for (int j = 0; j < N; ++j) {
            strategy.nextSequence();
        }
        assertThat(strategy.getSequenceCount(), is(N));

        strategy.reset();
        assertThat(strategy.getSequenceCount(), is(0));
        for (int j = 0; j < container.size(); ++j) {
            assertThat(container.getLight(j).isLit(), is(false));
        }
    }
}
