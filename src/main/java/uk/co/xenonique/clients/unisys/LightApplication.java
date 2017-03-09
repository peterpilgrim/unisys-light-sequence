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
 * The type LightApplication
 *
 * @author Peter Pilgrim (peter)
 */
public class LightApplication {

    public void execute(String algorithmName) {

        final LightContainer container = new LightContainer(20);
        LightDisplayStrategy strategy = LightDisplayStrategyFactory.createInstance(algorithmName, container);

        System.out.printf("Strategy: [%s]", strategy.getClass().getSimpleName());

        while (strategy.getSequenceCount() < 100) {
            final int sleepTime = strategy.getPulseLength();
            System.out.printf("SEQ COUNT: %4d\n", strategy.getSequenceCount());
            strategy.nextSequence();
            for (int j = 0; j < container.size(); ++j) {
                final FairyLight light = container.getLight(j);
                System.out.printf("[%2d] %6s: %3s |  ", j, light.getColour(), (light.isLit() ? "on" : "off"));
                if (j % 10 == 9) {
                    System.out.println();
                }
            }
            System.out.println();

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public static void main(String args[]) {

        String algorithmName = "sequence";
        if (args.length != 0) {
            algorithmName = args[0];
        }

        if (args.length > 1) {
            System.out.printf("USAGE: %s [ algorithmName ]", LightApplication.class.getSimpleName());
            System.exit(1);
        }
        new LightApplication().execute(algorithmName);
    }
}
