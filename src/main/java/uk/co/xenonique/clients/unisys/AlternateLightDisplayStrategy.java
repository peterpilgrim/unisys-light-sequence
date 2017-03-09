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
 * The type AlternateLightDisplayStrategy
 *
 * @author Peter Pilgrim (peter)
 */
public class AlternateLightDisplayStrategy extends AbstractLightDisplayStrategy {

    public static final String RUNNING_STRATEGY_SEQUENCE = "Sequence";
    public static final String RUNNING_STRATEGY_COLOUR = "Colour";

    private final int sequenceStrategyCount;
    private final int colourStrategyCount;
    private final SequenceLightDisplayStrategy sequenceStrategy;
    private final ColourLightDisplayStrategy colourStrategy;

    private int counter = 0;
    private boolean alternate;


    public AlternateLightDisplayStrategy(LightContainer container, int sequenceStrategyCount, int colourStrategyCount) {
        super(container);
        this.sequenceStrategyCount = sequenceStrategyCount;
        this.colourStrategyCount = colourStrategyCount;
        sequenceStrategy = new SequenceLightDisplayStrategy(container);
        colourStrategy = new ColourLightDisplayStrategy(container);
    }

    @Override
    public void nextSequence() {
        ++sequenceCount;        /* !!! */
        if (!alternate) {
            sequenceStrategy.nextSequence();
            counter++;
            if (counter >= sequenceStrategyCount) {
                counter = 0;
                sequenceStrategy.reset();           /*!!! */
                alternate = true;
            }
        } else {
            colourStrategy.nextSequence();
            counter++;
            if (counter >= colourStrategyCount) {
                counter = 0;
                colourStrategy.reset();           /*!!! */
                alternate = false;
            }
        }
    }

    @Override
    public int getPulseLength() {
        return !alternate ? sequenceStrategy.getPulseLength() : colourStrategy.getPulseLength();
    }

    public String getRunningName() {
        return !alternate ? RUNNING_STRATEGY_SEQUENCE : RUNNING_STRATEGY_COLOUR;
    }

}

