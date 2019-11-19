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
 * Rule1Test
 * 11/11/19
 */
package br.ufrn.case_.stacker.rules;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Rule1.
 *
 * The remove spaces, lines number and tabs in stack traces
 */
public class Rule0Test {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "        at com.example.myproject.Book.getTitle(Book.java:10)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n";

    String s2 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            " at com.example.myproject.Book.getTitle(Book.java)\n" +
            " at com.example.myproject.Author.getBookTitles(Author.java)\n";

    String s3 = "caused by: java.lang.nullpointerexception\n" +
            "\tat br.ufrn.sigaa.ava.jsf.permissaoavambean.precisarecarregarpermissao(permissaoavambean.java)\n" +
            "\tat br.ufrn.sigaa.ava.jsf.permissaoavambean.getpermissaousuario(permissaoavambean.java)\n" +
            "\tat sun.reflect.generatedmethodaccessor2247.invoke(unknown source)\n" +
            "\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java)\n" +
            "\tat java.lang.reflect.method.invoke(method.java)\n" +
            "\tat javax.el.beanelresolver.getvalue(beanelresolver.java)\n" +
            "caused by: javax.el.elexception: error reading 'permissaousuario' on type br.ufrn.sigaa.ava.jsf.permissaoavambean\n" +
            "\tat javax.el.beanelresolver.getvalue(beanelresolver.java)\n" +
            "\tat javax.el.compositeelresolver.getvalue(compositeelresolver.java)\n" +
            "\tat org.apache.el.parser.astvalue.getvalue(astvalue.java)\n" +
            "\tat org.apache.el.parser.astor.getvalue(astor.java)\n" +
            "\tat org.apache.el.valueexpressionimpl.getvalue(valueexpressionimpl.java)\n" +
            "\tat org.apache.jasper.runtime.pagecontextimpl.proprietaryevaluate(pagecontextimpl.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_c_005fif_005f17(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_c_005fwhen_005f3(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_c_005fchoose_005f3(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_h_005fform_005f0(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspservice(turma_jsp.java)\n" +
            "\tat org.apache.jasper.runtime.httpjspbase.service(httpjspbase.java)\n" +
            "\tat javax.servlet.http.httpservlet.service(httpservlet.java)\n" +
            "\tat org.apache.jasper.servlet.jspservletwrapper.service(jspservletwrapper.java)\n";

    String s4 = "caused by: java.lang.nullpointerexception\n" +
            "\tat br.ufrn.sigaa.ava.jsf.permissaoavambean.precisarecarregarpermissao(permissaoavambean.java)\n" +
            "\tat br.ufrn.sigaa.ava.jsf.permissaoavambean.getpermissaousuario(permissaoavambean.java)\n" +
            "\tat sun.reflect.generatedmethodaccessor.invoke(unknown source)\n" +
            "\tat sun.reflect.delegatingmethodaccessorimpl.invoke(delegatingmethodaccessorimpl.java)\n" +
            "\tat java.lang.reflect.method.invoke(method.java)\n" +
            "\tat javax.el.beanelresolver.getvalue(beanelresolver.java)\n" +
            "caused by: javax.el.elexception: error reading 'permissaousuario' on type br.ufrn.sigaa.ava.jsf.permissaoavambean\n" +
            "\tat javax.el.beanelresolver.getvalue(beanelresolver.java)\n" +
            "\tat javax.el.compositeelresolver.getvalue(compositeelresolver.java)\n" +
            "\tat org.apache.el.parser.astvalue.getvalue(astvalue.java)\n" +
            "\tat org.apache.el.parser.astor.getvalue(astor.java)\n" +
            "\tat org.apache.el.valueexpressionimpl.getvalue(valueexpressionimpl.java)\n" +
            "\tat org.apache.jasper.runtime.pagecontextimpl.proprietaryevaluate(pagecontextimpl.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_c_fif_f(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_c_fwhen_f(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_c_fchoose_f(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspx_meth_h_fform_f(turma_jsp.java)\n" +
            "\tat org.apache.jsp.public_.docente.turma_jsp._jspservice(turma_jsp.java)\n" +
            "\tat org.apache.jasper.runtime.httpjspbase.service(httpjspbase.java)\n" +
            "\tat javax.servlet.http.httpservlet.service(httpservlet.java)\n" +
            "\tat org.apache.jasper.servlet.jspservletwrapper.service(jspservletwrapper.java)\n";

    @Test
    public void testSimplification() {
        Rule0 r0 = new Rule0();
        Assert.assertEquals(s2, r0.simplify(s1) );
    }

    @Test
    public void testSimplification2() {
        Rule0 r0 = new Rule0();
        Assert.assertEquals(s4, r0.simplify(s3) );
    }

}