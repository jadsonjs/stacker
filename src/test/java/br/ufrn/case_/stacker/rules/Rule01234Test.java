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
 * Rule01234Test
 * 15/11/19
 */
package br.ufrn.case_.stacker.rules;

import br.ufrn.case_.stacker.chains.CorrelationChain;
import br.ufrn.case_.stacker.chains.SimplificationChain;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test all rules
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule01234Test {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n" +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

    String s2 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:170)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n" +
            "        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";


    /**
     * just for test that when the stack traces are exactly identical, all rule are match
     */
    @Test
    public void testAllRules() {

        SimplificationChain chainSimplification = new Rule1().setNext(new Rule0());

        CorrelationChain chainCorrelation = new Rule2().setNext(new Rule3()).setNext(new Rule4());

        Assert.assertTrue( chainCorrelation.execute( chainSimplification.execute(s1) , chainSimplification.execute(s2)  ) );

    }

}
