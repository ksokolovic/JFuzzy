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

import org.sokolovic.jfuzzy.FuzzyTerm;

/**
 * Class to provide a proxy for a fuzzy set. The proxy inherits from the {@code FuzzyTerm}
 * and therefore can be used to create fuzzy rules.
 *
 * @author sokolovic
 */
public class ProxyFuzzySet extends FuzzyTerm {

    /**
     * A reference to the fuzzy set this proxy represents.
     */
    protected FuzzySet set;

    /**
     * Constructor to initialize the proxy of the given fuzzy set.
     * @param set Fuzzy set to create a proxy for.
     */
    public ProxyFuzzySet(FuzzySet set) {
        this.set = set;
    }

    @Override
    public double getDom() {
        return set.getDom();
    }

    @Override
    public void clearDom() {
        set.clearDom();
    }

    @Override
    public void orWithDom(double value) {
        set.orWithDom(value);
    }

}
