package br.ufrn.case_.stacker.rules.regex;

import br.ufrn.case_.stacker.rules.Rule0;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTracesRegexTest {

    String s1 = "Exception in thread \"main\" java.lang.NullPointerException\n" +
            "       at com.example.myproject.Book.getTitle(Book.java:1)\n " +
            "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n ";

    String sFinal = "Exceptioninthread\"main\"java.lang.NullPointerExceptionatcom.example.myproject.Book.getTitle(Book.java)atcom.example.myproject.Author.getBookTitles(Author.java)";

    @Test
    public void testUnify() {
        Assert.assertEquals(sFinal, StackTracesRegex.unify(s1));
    }

}