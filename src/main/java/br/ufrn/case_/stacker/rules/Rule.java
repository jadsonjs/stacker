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
 * AbstractRuleChain
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

/**
 * This class implements the pattern Chain of Responsibility to apply several correlated stack traces rules
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public abstract class Rule {

    protected Rule next = null;

    /**
     * create the chain
     * @param rule
     */
    public final Rule setNext(Rule rule) {
        if (next == null) {
            next = rule;
        } else {
            next.setNext(rule);
        }
        return this;
    }

    /**
     * Execute the chain of verifications
     *
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    public final boolean correlated(String stackTrace1, String stackTrace2){
        boolean thisCorrelated = this.verifyRule(stackTrace1, stackTrace2); // my verification
        boolean nextCorrelated = true;
        if(next != null)
            nextCorrelated =  next.correlated(stackTrace1, stackTrace2); // next verifications

        // If my verification and next chain verification is correlated, all chain is correlated
        return thisCorrelated && nextCorrelated;
    }


    /**
     * Implements the specific rule verification.
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    protected abstract boolean verifyRule(String stackTrace1, String stackTrace2);

    /**
     * Return the stackTrace simplified use to comparation
     * @return
     */
    public abstract String getStackTrace();

    public Rule getNext(){ return next; }

}
