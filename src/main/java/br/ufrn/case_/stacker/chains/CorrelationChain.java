/*
 * Federal University of Rio Grande do Norte
 * Department of Informatics and Applied Mathematics
 * Collaborative & Automated Software Engineering (CASE) Research Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *
 * stacker
 * br.ufrn.case_.stacker.rules
 * RuleComparation
 * 14/11/19
 */
package br.ufrn.case_.stacker.chains;

/**
 * Abstract classe to correlate to stack traces
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public abstract class CorrelationChain {

    protected CorrelationChain next = null;

    /**
     * create the chain
     * @param rule
     */
    public final CorrelationChain setNext(CorrelationChain rule) {
        if (next == null) {
            next = rule;
        } else {
            next.setNext(rule);
        }
        return this;
    }

    /**
     * Execute the chain of simplification
     *
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    public final boolean execute(String stackTrace1, String stackTrace2){
        boolean thisCorrelated = this.isCorrelated(stackTrace1, stackTrace2); // my verification
        boolean nextCorrelated = true;
        if(next != null)
            nextCorrelated =  next.isCorrelated(stackTrace1, stackTrace2); // next verifications

        // If my verification and next chain verification is correlated, all chain is correlated
        return thisCorrelated && nextCorrelated;
    }

    /**
     * Implements the rule to identify is tow stack trance are correlated.
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    public abstract boolean isCorrelated(String stackTrace1, String stackTrace2);


}
