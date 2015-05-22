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

import org.sokolovic.jfuzzy.set.*;

/**
 * Class implementing the Factory Design Pattern. The class is used to instantiate
 * and return the requested {@code FuzzySet} instance with the parameters that are
 * more natural to use than the constructor parameters used in class implementations.
 *
 * @author sokolovic
 */
public class FuzzyVariableFactory {

    /**
     * Method instantiates and returns the instance of the requested fuzzy set with the given
     * parameters.
     * @param type Type of the fuzzy set to instantiate and return. The type must be one
     *             of the values defined in the {@code eFuzzySet} enumeration.
     * @param minBound Minimum bound of the requested set.
     * @param peak Peak of the requested set.
     * @param maxBound Maximum bound of the requested set.
     * @return Instance of the requested fuzzy set instantiated with the given parameters.
     */
    public static FuzzySet getFuzzyVariable(eFuzzySet type, double minBound, double peak, double maxBound) {
        switch (type) {
            case TRIANGULAR:
                return new TriangularFuzzySet(peak, peak - minBound, maxBound - peak);
            case LEFT_SHOULDER:
                return new LeftShoulderFuzzySet(peak, peak - minBound, maxBound - peak);
            case RIGHT_SHOULDER:
                return new RightShoulderFuzzySet(peak, peak - minBound, maxBound - peak);
            // This will never occur, but has to be here so no error is reported
            default:
                return null;
        }
    }
}
