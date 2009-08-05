package com.preppa.web.pages.learning;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import java.util.Calendar;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;


/**
 *
 * @author Narry Soliman
 */
public class Index {
    private int index = 1;
    final private static int bodysize = 2000;

    @Inject
    private ArticleDAO articleDAO;
    @Property
    private Article article = articleDAO.findById(index);


    void FeaturedArticle() {
        Calendar calendar = Calendar.getInstance();
        index = calendar.get(Calendar.DAY_OF_MONTH);
    }

    void onActivate() {
        this.index = 1;
        article = articleDAO.findById(index);
        System.out.println(article.getBody());
    }

    public String getBodyParse() {
        String returnVal = article.getBody();
        returnVal = ClearHTMLTags(returnVal,-1);
        if (bodysize > returnVal.length()) {
            return returnVal;
        }
        else {
            return returnVal.substring(0, bodysize);
        }
    }

    /* From http://javacupaday.blogspot.com/2008/01/how-to-remove-html-formatting-in-string.html
     *
     * */
    public String ClearHTMLTags(String strHTML, int intWorkFlow){

    	Pattern pattern = null;
	    Matcher matcher = null;
	   	String regex;
	   	String strTagLess = null;
    	strTagLess = strHTML;

    	if(intWorkFlow == -1)
  		{
  			regex = "<[^>]*>";
  			//removes all html tags
			pattern = pattern.compile(regex);
			strTagLess = pattern.matcher(strTagLess).replaceAll(" ");
		}

		if(intWorkFlow > 0 && intWorkFlow < 3)
		{

		  	regex = "[<]";
			//matches a single <
			pattern = pattern.compile(regex);
			strTagLess = pattern.matcher(strTagLess).replaceAll("<");

			regex = "[>]";
			//matches a single >
		 	pattern = pattern.compile(regex);
			strTagLess = pattern.matcher(strTagLess).replaceAll(">");
		}
		return strTagLess;
    }
}
