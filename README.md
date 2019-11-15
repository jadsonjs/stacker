# Stacker

Stacker is library to compare correlated Java Stack Traces

<div style="width:50%; margin-right:auto; margin-left:auto;">
   <img width="250" height="250" src="https://github.com/jadsonjs/stacker/blob/master/src/main/resources/stacker.png">
</div>


This library tries to identify if two Stack Traces were launched by the 
same error, or represents the same error, although Stack traces 
can present small difference among then.

For example, these two stack traces are correlated and probably are related 
to the same error although they are not exactly equals:


```
"Exception in thread \"main\" java.lang.NullPointerException\n" 
"       at com.example.myproject.Book.getTitle(Book.java:10)\n " +
"        at com.example.myproject.Author.getBookTitles(Author.java:25)\n"
"        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)";

"Exception in thread \"main\" java.lang.NullPointerException\n" +
" at com.example.myproject.Book.getTitle(Book.java:11)\n" +
"   at com.example.myproject.Author.getBookTitles(Author.java:28)\n";
```

Some rules was based on the in the author's understanding of algorithm 
extract for the paper "Improving bug management using correlations in crash 
reports of Shaohua Wang · Foutse Khomh · Ying Zou"

Empir Software Eng (2016) 21:337–367

DOI 10.1007/s10664-014-9333-9

### Authors:

     Jadson Santos - jadsonjs@gmail.com
 
### Dependencies

Junit 4.12
   
### Database configuration

Not necessary 

### How do I get set up?

#### From the source code:

   Clone the project -> Import it as a gradle project on your IDE.

#### From the binary:

   Stacker has a binary distribution on **libs/staker.X.Y.jar** directory.
   
   Include it on your classpath.

### How to use


       // Execute for a specific rule 
        
        String stackTrace =
                "Exception in thread \"main\" java.lang.NullPointerException\n" +
                "        at com.example.myproject.Book.getTitle(Book.java:10)\n " +
                "        at com.example.myproject.Author.getBookTitles(Author.java:25)\n";
        
        String result = new Rule0().simplify(stackTrace);
        
        // OR several rules in cascade
        
        SimplificationChain chainSimplification = new Rule1().setNext(new Rule0());

        String result2 = chainSimplification.execute(stackTrace);

  
### How to run tests

 Run the  br.ufrn.case_.stacker.AllUnitTest.java class as a Junit test.   
 
 Or

 Run ```gradlew test``` command. 
    



