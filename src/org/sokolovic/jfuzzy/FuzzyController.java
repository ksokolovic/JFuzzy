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

package org.sokolovic.jfuzzy;

import org.sokolovic.jfuzzy.variable.FuzzyVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class models a fuzzy controller: a collection of fuzzy variables and rules
 * that operate on them.
 *
 * @author sokolovic
 */
public class FuzzyController {

    protected HashMap<String, FuzzyVariable> variables;
    protected List<FuzzyRule> rules;

    /**
     * No-arg constructor. Initializes the new instance of the fuzzy
     * controller.
     */
    public FuzzyController() {
        variables = new HashMap<String, FuzzyVariable>();
        rules = new ArrayList<FuzzyRule>();
    }

    /**
     * Creates a new 'empty' fuzzy variable and returns a reference to it.
     * @param name Name of the variable to create.
     * @return Reference to the newly created variable.
     */
    public FuzzyVariable createFLV(String name) {
        variables.put(name, new FuzzyVariable());

        return variables.get(name);
    }

    /**
     * Adds a rule to the controller.
     * @param antecedent Antecedent of the rule to add.
     * @param consequence Consequence of the rule to add.
     */
    public void addRule(FuzzyTerm antecedent, FuzzyTerm consequence) {
        rules.add(new FuzzyRule(antecedent, consequence));
    }

    /**
     * Method calls the {@code fuzzify()} method of the named FLV.
     * @param name Name of the FLV.
     * @param value Value to fuzzify.
     */
    public void fuzzify(String name, double value) {
        variables.get(name).fuzzify(value);
    }

    /**
     * Given a fuzzy variable with the key specified method returns the crisp value.
     * @param name Name of the FLV to defuzzify.
     * @return Crisp value of the variable specified.
     */
    public double defuzzify(String name) {
        // Clear the DOMs of all the consequences of all the rules
        setConfidencesOfConsequentsToZero();

        // Process the rules
        for (FuzzyRule rule : rules) {
            rule.calculate();
        }

        // Defuzzify the resultant conclusion
        return variables.get(name).defuzzify();
    }

    /**
     * Method zeros the DOMs of the consequents of each rule.
     */
    private void setConfidencesOfConsequentsToZero() {
        for (FuzzyRule rule : rules) {
            rule.setConfidenceOfConsequentToZero();
        }
    }

}
