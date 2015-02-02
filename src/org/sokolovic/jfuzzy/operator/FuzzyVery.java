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

package org.sokolovic.jfuzzy.operator;

import org.sokolovic.jfuzzy.FuzzyTerm;

/**
 * Class models the fuzzy hedge VERY unary operator that can be used in the creation
 * of the fuzzy rule base.
 *
 * @author sokolovic
 */
public class FuzzyVery extends FuzzyTerm {

    /**
     * Fuzzy term to apply the operation to.
     */
    protected FuzzyTerm term;

    /**
     * Constructor to initialize the term.
     * @param term Instance to apply the unary operator to.
     */
    public FuzzyVery(FuzzyTerm term) {
        this.term = term;
    }

    /**
     * {@inheritDoc}
     * The VERY operator returns the square of the degree of membership of the set it
     * is applied to.
     */
    @Override
    public double getDom() {
        return term.getDom() * term.getDom();
    }

    @Override
    public void clearDom() {
        term.clearDom();
    }

    @Override
    public void orWithDom(double value) {
        term.orWithDom(value);
    }
}
