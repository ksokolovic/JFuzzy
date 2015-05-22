/*
 * Copyright (c) 2015 Kemal Sokolović <kemal DOT sokolovic AT gmail DOT com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sokolovic.jfuzzy.set;

/**
 * Class models abstract fuzzy set. Each concrete fuzzy set must inherit from this abstract class
 * and provide appropriate methods.
 *
 * @author sokolovic
 */
public abstract class FuzzySet {

    /**
     * Degree of membership to this set of the given value.
     */
    protected double dom;

    /**
     * This is the max of the set's membership function. E.g. if the set is triangular,
     * then this will be the peak of the triangle. If the set has a plateau then this value
     * will be the midpoint of the plateau. This value is set in the constructor to avoid
     * runtime calculation of the midpoint values.
     */
    protected double representativeValue;

    /**
     * Constructor to initialize the {@code FuzzySet} with the given representative value and
     * default (zero) degree of membership.
     * @param representativeValue {@code FuzzySet}'s representative value.
     */
    public FuzzySet(double representativeValue) {
        this.dom = 0.0;
        this.representativeValue = representativeValue;
    }

    /**
     * Getter to obtain the degree of membership.
     * @return Degree of membership value.
     */
    public double getDom() {
        return this.dom;
    }

    /**
     * Setter to set the degree of membership.
     * @param dom Degree of membership to set.
     */
    public void setDom(double dom) {
        this.dom = dom;
    }

    /**
     * Getter to obtain the fuzzy set's representative value.
     * @return Fuzzy set's representative value.
     */
    public double getRepresentativeValue() {
        return this.representativeValue;
    }

    /**
     * Method to calculate and return the degree of membership in this set for the given value.
     * @param value Value to calculate the degree of membership for.
     * @return Degree of membership to this set for the given value.
     */
    public abstract double calculateDom(double value);

    /**
     * If this fuzzy set is part of a consequent fuzzy linguistic variable and it is fired by a rule
     * then this method sets the degree of membership (in this context, the degree of membership
     * represents a confidence level) to the maximum of the parameter value or the set's existing
     * degree of membership value.
     * @param value Degree of membership value.
     */
    public void orWithDom(double value) {
        if (value > dom) {
            dom = value;
        }
    }

    /**
     * Method resets the degree of membership value to the zero value.
     */
    public void clearDom() {
        this.dom = 0.0;
    }

}
