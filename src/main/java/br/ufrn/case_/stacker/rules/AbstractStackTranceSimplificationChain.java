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
 * This is abstract class to stack trance simplification that implements the pattern Chain of Responsibility
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public abstract class AbstractStackTranceSimplificationChain {

    protected AbstractStackTranceSimplificationChain next = null;

    /**
     * create the chain
     * @param rule
     */
    public final AbstractStackTranceSimplificationChain setNext(AbstractStackTranceSimplificationChain rule) {
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
     * @param stackTrace
     * @return
     */
    public final String execute(String stackTrace){
        String simpleStack = this.simplify(stackTrace); // my verification
        if(next == null)
            return simpleStack;
        else
            return next.execute(simpleStack); // next verifications

    }

    /**
     * Implements the specific stack trance simplification.
     * @param stackTrace
     * @return
     */
    protected abstract String simplify(String stackTrace);


}
