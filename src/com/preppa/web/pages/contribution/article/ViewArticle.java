  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.*;
import java.util.regex.*;

/**
 *
 * @author Jan Jan
 */
public class ViewArticle {

@Property
private Article cursor;
@Inject
private ArticleDAO articleDAO;

private String input;

private final static int bodysize = 100;

    private static final int TESTINDEX = 4;
    private static final int MATHINDEX = 2;
    private static final int CRINDEX = 1;
    private static final int WRITINGINDEX = 3;

    Object onActivate(String input) {
        this.input = input;
        return null;
    }

    public String getsamplebody() {
        String returnVal = cursor.getBody();
        returnVal = ClearHTMLTags(returnVal, -1);
        if (bodysize < returnVal.length()) {
            returnVal = returnVal.substring(0, bodysize);
        }

        return returnVal;
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

    public List<Article> getArticleList() {
        List<Article> returnVal = null;

        if (input == null) {
            returnVal = articleDAO.findAll();
        }
        else if (input.compareTo("Test") == 0) {
            returnVal = articleDAO.findBytestsubject_id(TESTINDEX);
        }
        else if (input.compareTo("Math") == 0) {
            returnVal = articleDAO.findBytestsubject_id(MATHINDEX);
        }
        else if (input.compareTo("CriticalReading") == 0) {
            returnVal = articleDAO.findBytestsubject_id(CRINDEX);
        }
        else if (input.compareTo("Writing") == 0) {
            articleDAO.findBytestsubject_id(WRITINGINDEX);
        }

        return returnVal;
    }

}
