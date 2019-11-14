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
 * Rule3
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

/**
 * Compare two stack traces. IF top line of stack trace are the same.
 *
 *  Rule3 just works properly if before it was applied simplification using the Rule0 and Rule1
 *
 *  Top Frame Comparison.
 *
 *  Given two crash types CTa and CTb with Top Frames F1A and F2B, CTa and CTb are correlated if Sa
 *  is contained if qFileName1A = qFileName1B and type of exception are the same
 *
 *  caused by: ***java.lang.nullpointerexception***
 *         at ***com.example.myproject.Book.getTitle***(Book.java:16)
 *         at com.example.myproject.Author.getBookTitles(Author.java:25)
 *         at com.example.myproject.Bootstrap.main(Bootstrap.java:14)
 *
 *  caused by: ***java.lang.nullpointerexception***
 *         at ***com.example.myproject.Book.getTitle***(Book.java:160)
 *         at com.example.myproject.Author.getBookTitles(Author.java:25)
 *         at com.example.myproject.Bootstrap.main2(Bootstrap.java:14)
 *
 *
 *
 * This rule was based on the in the author's understanding of algorithm extract for the paper "
 * Improving bug management using correlations in crash reports of Shaohua Wang · Foutse Khomh · Ying Zou"
 * Empir Software Eng (2016) 21:337–367
 * DOI 10.1007/s10664-014-9333-9
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule3 extends CorrelationChain {


    @Override
    public boolean isCorrelated(String stackTrace1, String stackTrace2) {
        return isSameTopFrame(stackTrace1, stackTrace2);
    }

    /**
     * TODO not implemented yet
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    private boolean isSameTopFrame(String stackTrace1, String stackTrace2) {
        return true;
    }

}