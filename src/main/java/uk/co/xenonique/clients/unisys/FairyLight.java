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
 * The type FairyLight
 *
 * @author Peter Pilgrim (peter)
 */
public class FairyLight {

    public enum Colour {
        RED, GREEN, WHITE
    }

    private Colour colour;
    private boolean lit;


    public FairyLight(Colour colour) {
        this(colour, false);
    }

    public FairyLight(Colour colour, boolean lit) {
        this.colour = colour;
        this.lit = lit;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public boolean isLit() {
        return lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    @Override
    public String toString() {
        return "FairyLight{" +
                "colour=" + colour +
                ", lit=" + lit +
                '}';
    }
}
