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
 * Rule2
 * 08/11/19
 */
package br.ufrn.case_.stacker.rules;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Compare two stack traces. IF one is contained in the other, return the stack with more steps.
 *
 *  Rule2 just works in before it was applied the Rule0 and Rule1
 *
 *  Crash type signature correlation comparison.
 *
 *  Given two crash types CTa and CTb with Signature Sa and Sb respectively, CTa and CTb are correlated if Sa
 *  is contained in Sb or Sb is contained in Sa
 *
 *  Exception in thread "main" java.lang.NullPointerException
 *         at com.example.myproject.Book.getTitle(Book.java:16)
 *         at com.example.myproject.Author.getBookTitles(Author.java:25)
 *         at com.example.myproject.Bootstrap.main(Bootstrap.java:14)
 *
 *  Exception in thread "main" java.lang.NullPointerException
 *         at com.example.myproject.Author.getBookTitles(Author.java:25)
 *         at com.example.myproject.Bootstrap.main(Bootstrap.java:14)
 *
 * This rule was based on the in the author's understanding of algorithm extract for the paper "
 * Improving bug management using correlations in crash reports of Shaohua Wang · Foutse Khomh · Ying Zou"
 * Empir Software Eng (2016) 21:337–367
 * DOI 10.1007/s10664-014-9333-9
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule2 extends Rule{

    private String stackTraceOther;

    public Rule2( String stackTraceOther){
        this.stackTraceOther = stackTraceOther;
    }

    @Override
    protected String simplify(String stackTrace) {
        if( isContained(stackTrace, stackTraceOther) ){
            return stackTraceOther;
        }else{
            return stackTrace;
        }
    }


    /**
     * verify if one stack trace is contained in another stack trace
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    private boolean isContained(String stackTrace1, String stackTrace2) {

        List<String> stackTraces1Lines = Arrays.asList(stackTrace1.split("\\n"));
        List<String> stackTraces2Lines = Arrays.asList(stackTrace2.split("\\n"));

        List<String> comumLines = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;

        while (index1 < stackTraces1Lines.size()){
            while (index2 < stackTraces2Lines.size()){
                if( stackTraces1Lines.get(index1).equals(stackTraces2Lines.get(index2) )  ) {
                    index1++;
                    index2++;
                    comumLines.add(stackTraces1Lines.get(index1));
                }else{
                    index2++;
                }
            }

            if(index2 >= stackTraces2Lines.size())
                break;
        }

        if(comumLines.size() == stackTraces1Lines.size())
            return true;
        return false;
    }


}