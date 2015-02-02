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
 * Class models a right shoulder fuzzy set, which is defined by three values: a peak point,
 * a left offset and a right offset.
 *
 * @author sokolovic
 */
public class RightShoulderFuzzySet extends FuzzySet {

    /**
     * A left shoulder function peak point.
     */
    protected double peak;

    /**
     * A left shoulder function left offset.
     */
    protected double leftOffset;

    /**
     * A left shoulder function right offset.
     */
    protected double rightOffset;

    /**
     * Constructor to initialize the right shoulder fuzzy set instance with the given
     * values. Representative value is set to the midpoint of the plateau of the shoulder.
     * @param peak Left shoulder function peak point.
     * @param leftOffset Left shoulder function left offset.
     * @param rightOffset Left shoulder function right offset.
     */
    public RightShoulderFuzzySet(double peak, double leftOffset, double rightOffset) {
        super(((peak + rightOffset) + peak) / 2);
        this.peak = peak;
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
    }

    @Override
    public double calculateDom(double value) {
        // Test for the case where the left or right offsets are zero
        // to prevent divide by zero errors
        if ((rightOffset == 0.0 && peak == value) ||
            (leftOffset == 0.0 && peak == value)) {
            return 1.0;
        }
        // Find the degree of membership if left of the center
        else if ((value <= peak) && (value > (peak - leftOffset))) {
            double grad = 1.0 / leftOffset;

            return grad * (value - (peak - leftOffset));
        }
        // Find the degree of membership if right of center and less than center + offset
        else if ((value > peak) && (value <= (peak + rightOffset))) {
            return 1.0;
        }
        // Out of the range
        else {
            return 0.0;
        }
    }

}
