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

import br.ufrn.case_.stacker.util.TextProcessor;

import java.util.List;

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
public final class Rule3 extends CorrelationChain {


    @Override
    public boolean isCorrelated(String stackTrace1, String stackTrace2) {
        return isSameTopFrame(stackTrace1, stackTrace2);
    }

    /**
     * verify the first line and exception are the same. if yes, stack traces are correlated
     * @param stackTrace1
     * @param stackTrace2
     * @return
     */
    private boolean isSameTopFrame(String stackTrace1, String stackTrace2) {

        List<String> stackTraces1Lines = TextProcessor.slipByEndLine(stackTrace1);
        List<String> stackTraces2Lines = TextProcessor.slipByEndLine(stackTrace2);

        if(stackTraces1Lines.size() >=2  && stackTraces2Lines.size() >=2 ){

            /**
             * find the first Line that contains "caused by" or "Exception in thread"
             */
            String firstLine1 = "";
            int i = 0;
            for(; i < stackTraces1Lines.size() ; i++) {
                firstLine1 = stackTraces1Lines.get(i);
                if(firstLine1.contains("caused by") || firstLine1.contains("Exception in thread")){
                    break;
                }
            }
            String firstLine2 = "";
            int j = 0;
            for(; j < stackTraces2Lines.size() ; j++) {
                firstLine2 = stackTraces2Lines.get(j);
                if(firstLine2.contains("caused by") ||  firstLine2.contains("Exception in thread")){
                    break;
                }
            }

            /**
             * find the second Line that contains "at" AND "java.xxx.xxx(....)"
             */
            String secondLine1 = "";
            for(; i < stackTraces1Lines.size() ; i++) {
                secondLine1 = stackTraces1Lines.get(i);
                if(secondLine1.contains("at") && secondLine1.contains("(")){
                    break;
                }
            }
            String secondLine2 = "";
            for(; j < stackTraces1Lines.size() ; j++) {
                secondLine2 = stackTraces2Lines.get(j);
                if(secondLine2.contains("at") && secondLine2.contains("(")){
                    break;
                }
            }

            if(  firstLine1.equals(firstLine2)  )  {

                /*
                 * if line = at com.example.myproject.Book.getTitle(Book.java:170)
                 * file name = at com.example.myproject.Book.getTitle
                 */
                String fileName1 = secondLine1.substring(0, secondLine1.indexOf("("));
                String fileName2 = secondLine2.substring(0, secondLine2.indexOf("("));

                if(fileName1.equals(fileName2)) {
                    return true;
                }
            }

        }

        return false;
    }

}