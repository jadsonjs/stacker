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
 * Rule0
 * 11/11/19
 */
package br.ufrn.case_.stacker.rules;

import br.ufrn.case_.stacker.util.TextProcessor;

import java.util.*;

/**
 * This class simplify the stack trace.
 *
 *  - organize cause by
 *  - filter only specific package
 *  - eliminate information out of cause by
 *
 * INPUT:
 * "javax.servlet.servletexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception\n" +
 * "\\ncaused by: javax.faces.facesexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception\n" +
 * "\\n\\tat com.sun.faces.application.actionlistenerimpl.processaction(actionlistenerimpl.java:118)\n" +
 * "\\ncaused by: java.lang.nullpointerexception\n" + // top frame is here
 * "\\n\\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)\n" +
 * "\\n\\tat br.ufrn.sigaa.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1951)\n" +
 *
 * OUTPUT:
 * "\\ncaused by: java.lang.nullpointerexception\n" + // top frame is here
 * "\\n\\tat br.ufrn.sigaa.ensino.tecnico.dao.turmaentradatecnicodao.findturmasentradadisponiveisimdnew(turmaentradatecnicodao.java:237)\n" +
 * "\\n\\tat br.ufrn.sigaa.ensino.graduacao.jsf.matriculagraduacaombean.telainstrucoes(matriculagraduacaombean.java:1951)\n" +
 * "\\ncaused by: javax.faces.facesexception: #{ matriculagraduacao.telainstrucoes}: java.lang.nullpointerexception\n" +
 * "\\n\\tat com.sun.faces.application.actionlistenerimpl.processaction(actionlistenerimpl.java:118)\n" +
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Rule1 extends SimplificationChain {


    /*
     * find string that starts with: "... 53 more" or "...  more"
     * start with any "character zero or more times" ... space "zero or more digits" space "more" "any character"
     */
    final static String REGEX_STACK_TRACE_MORE = "^.*\\.\\.\\.+\\s\\d*\\smore.*";

    /**
     * find the sring that starts with: \n\tat org.apache.catalina.core.
     *
     * starts with \n or \t 0 or more times "at" space
     */
    final static String REGEX_STACK_TRACE_AT = "^[\\n\\t]*\\s+at\\s+.*[\\n\\t]*";

    /**
     * if we will recovery only caused by lines
     */
    boolean onlyCausedBy = false;

    /**
     * If we will filter lines of specific package
     */
    String packageFilter = "";

    /**
     * If we will exclude some package
     */
    String packageExclude = "";

    /**
     * IF the filter by specific package will by applied
     */
    boolean isFilterByPackage = false;

    /**
     * IF the filter by specific package will by applied
     */
    boolean isExcludingPackage = false;

    /**
     *
     */
    public Rule1(){

    }

    /**
     *
     * @param onlyCausedBy
     * @param packageFilter
     */
    public Rule1(boolean onlyCausedBy, String packageFilter){
        this.onlyCausedBy = onlyCausedBy;
        this.packageFilter = packageFilter;

        this.isFilterByPackage = packageFilter != null && ! packageFilter.trim().isEmpty();
    }

    /**
     *
     * @param onlyCausedBy
     * @param packageFilter
     */
    public Rule1(boolean onlyCausedBy, String packageFilter, String packageExclude){
        this.onlyCausedBy = onlyCausedBy;
        this.packageFilter = packageFilter;
        this.packageExclude = packageExclude;

        this.isFilterByPackage = packageFilter != null && ! packageFilter.trim().isEmpty();
        this.isExcludingPackage = packageExclude != null && ! packageExclude.trim().isEmpty();
    }

    @Override
    public String simplify(String stackTrace) {
        return organizerCauseBy(stackTrace, onlyCausedBy, packageFilter);
    }


    /**
     * Re Organizer the cause by lines.  The last one have to be the top frame
     *
     * @param inputStackTrace
     * @return
     */
    private String organizerCauseBy(String inputStackTrace, boolean onlyCausedBy, String packageFilter) {

        // slip the stack traces by line
        List<String> lines = TextProcessor.slipByEndLine(inputStackTrace);

        /* Keep the cause by lines separated */
        Map<Integer, List<String>> causedMap = new HashMap<>();

        List<String> normalLines = new ArrayList<>();
        List<String> tempLines = new ArrayList<>();

        int causedCount=0;

        boolean openCauseBy = false;

        for (String line : lines){

            line = line.replaceAll("\"+",  "");

            if(line.startsWith("caused by:")){
                openCauseBy = true;
                causedCount++;
            }
            if(line.matches(REGEX_STACK_TRACE_MORE)){
                openCauseBy = false;
            }

            if(causedCount > 0){
                if( openCauseBy ){
                    applyPackageFilter(tempLines, line);
                }else{
                    causedMap.put(causedCount, tempLines);
                    tempLines = new ArrayList<>();
                }
            }else{
                applyPackageFilter(normalLines, line);

            }
        }

        if(openCauseBy) {
            causedMap.put(causedCount, tempLines);
            openCauseBy = false;
        }

        // now return the lines in inverse order
        StringBuilder sb = new StringBuilder();

        while(causedCount > 0) {
            if( causedMap.get(causedCount) != null) {
                for (String line : causedMap.get(causedCount)) {
                    sb.append(line + "\n");
                }
            }
            causedCount--;
        }

        if(! onlyCausedBy ) {
            for (String line : normalLines) {
                sb.append(line + "\n");
            }
        }

        return sb.toString();
    }



    /**
     * Select the lines of stack trace that contains a specific java package
     * @param lines
     * @param line
     */
    private void applyPackageFilter(List<String> lines, String line) {
        if (line.matches(REGEX_STACK_TRACE_AT)) {  // just lines starting with "at br.com..." have packages

            if (this.isExcludingPackage){
                if (line.contains(packageExclude)) {
                    return;
                }
            }

            if (this.isFilterByPackage) {
                if (line.contains(packageFilter)) {
                    lines.add(line);
                }
                return;

            }

            lines.add(line);

        } else {
            lines.add(line);
        }
    }

}
