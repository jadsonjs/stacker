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
 * br.ufrn.case_.stacker
 * Rule1
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

import br.ufrn.case_.stacker.chains.SimplificationChain;
import br.ufrn.case_.stacker.regex.Regex;

/**
 *  Simplify the stack trace eliminating number of lines and spaces
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public final class Rule0 extends SimplificationChain {

    @Override
    public String simplify(String stackTrace) {
        return stackTrace
                .replaceAll(Regex.ONE_MORE_SPACES, " ") // normalize spaces
                .replaceAll(Regex.QUOTATION_MARK,  "")  // remove all " inside string
                .replaceAll(Regex.NUMBER_OF_LINES, "") // remove liner number
                .replaceAll(Regex.ONE_MORE_DIGIT,  ""); // remove any digit
    }


}
