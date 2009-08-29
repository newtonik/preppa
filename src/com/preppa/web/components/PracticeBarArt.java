  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.components;

import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class PracticeBarArt {
    @Inject
    private HttpServletRequest _request;

    public boolean getArticle() {
        String url = _request.getRequestURL().toString();
        return url.contains("/article") || url.contains("/learning");
    }

    public boolean getVocab() {
        String url = _request.getRequestURL().toString();
        return url.contains("/vocab");
    }
    public boolean getQuestion() {
        String url = _request.getRequestURL().toString();
        return url.contains("/question") || url.contains("longpassage") || url.contains("shortpassage");
    }
    public boolean getFreeRes() {
        String url = _request.getRequestURL().toString();
        return url.contains("/essay") || url.contains("/prompt");
    }
}
