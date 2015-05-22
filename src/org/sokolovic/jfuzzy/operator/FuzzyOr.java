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

import java.util.ArrayList;
import java.util.List;

/**
 * Class models the fuzzy OR operator that can be used in the creation of the fuzzy rule base.
 * The class is represented as a composite of the {@code FuzzyTerm} to be more generic.
 *
 * @author sokolovic
 */
public class FuzzyOr extends FuzzyTerm {

    /**
     * A list of terms that make this composite term.
     */
    protected List<FuzzyTerm> terms;

    /**
     * Constructor to initialize an empty fuzzy and composite term.
     */
    public FuzzyOr() {
        terms = new ArrayList<FuzzyTerm>();
    }

    /**
     * Method adds a new term to the composite AND fuzzy term.
     * @param term Term to add.
     */
    public void addTerm(FuzzyTerm term) {
        terms.add(term);
    }

    /**
     * {@inheritDoc}
     * The OR operator returns the maximum degree of membership of the sets it is operating on.
     */
    @Override
    public double getDom() {
        double max = Double.MIN_VALUE;

        for (FuzzyTerm term : terms) {
            if (term.getDom() > max) {
                max = term.getDom();
            }
        }
        return max;
    }

    @Override
    public void clearDom() {
        for (FuzzyTerm term : terms) {
            term.clearDom();
        }
    }

    @Override
    public void orWithDom(double value) {
        for (FuzzyTerm term : terms) {
            term.orWithDom(value);
        }
    }
}
