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

package org.sokolovic.jfuzzy.variable;

import org.sokolovic.jfuzzy.set.FuzzySet;
import org.sokolovic.jfuzzy.set.ProxyFuzzySet;
import org.sokolovic.jfuzzy.set.eFuzzySet;

import java.util.HashMap;

/**
 * Class models a fuzzy linguistic variable. A fuzzy linguistic variable is comprised of a
 * number of fuzzy sets.
 *
 * @author sokolovic
 */
public class FuzzyVariable {

    /**
     * A map of the fuzzy sets that comprise this variable.
     */
    protected HashMap<String, FuzzySet> members;

    /**
     * The minimum value of the range of this variable.
     */
    protected double minRange;

    /**
     * The maximum value of the range of this variable.
     */
    protected double maxRange;

    /**
     * No-arg constructor. Initializes an empty fuzzy variable instance and sets
     * min and max range to zero value.
     */
    public FuzzyVariable() {
        this.members = new HashMap<String, FuzzySet>();
        this.minRange = 0.0;
        this.maxRange = 0.0;
    }

    /**
     * Method is invoked with the upper and lower bound of a set each time a new set
     * is added to the variable in order to adjust the upper and lower range values
     * accordingly.
     * @param min Upper bound.
     * @param max Lower bound.
     */
    public void adjustRangeToFit(double min, double max) {
        if (min < minRange) { minRange = min; }
        if (max > maxRange) { maxRange = max; }
    }

    /**
     * This method adds an instance of the {@code FuzzySet} to the set map. Each time a new set is added
     * the {@code minRange} and {@code maxRange} are adjusted accordingly. Method performs by invoking the
     * factory method for set instantiation on the more generic parameters.
     * @param name The name of the variable member.
     * @param type The type of the set that represents it.
     * @param minBound Minimum bound of the set to create.
     * @param peak Peak of the set to create.
     * @param maxBound Maximum bound of the set to create.
     * @return An instance of the proxy set of the set added.
     */
    public ProxyFuzzySet addFuzzySet(String name, eFuzzySet type, double minBound, double peak, double maxBound) {
        FuzzySet set = FuzzyVariableFactory.getFuzzyVariable(type, minBound, peak, maxBound);
        members.put(name, set);
        adjustRangeToFit(minBound, maxBound);

        return new ProxyFuzzySet(members.get(name));
    }

    /**
     * Fuzzify a value by calculating its degree of membership in each of this variable subsets.
     * @param value
     */
    public void fuzzify(double value) {
        // For each set calculate the degree of membership for the given value
        for (FuzzySet current : members.values()) {
            current.setDom(current.calculateDom(value));
        }
    }

    /**
     * Defuzzify the variable by averaging the maxima of the sets that have fired.
     * @return Defuzzified value.
     */
    public double defuzzify() {
        double bottom = 0.0;
        double top = 0.0;

        for (FuzzySet current : members.values()) {
            bottom += current.getDom();
            top += current.getRepresentativeValue() * current.getDom();
        }
        // Make sure bottom is not equal to zero
        if (bottom == 0.0) {
            return 0.0;
        }

        return top / bottom;
    }

}
