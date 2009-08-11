  
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
public class ArticleMenu {
    @Inject
    private HttpServletRequest _request;

    public boolean getTesting() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Test");
    }
    public boolean getMath() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Math");
    }

    public boolean getCriticalReading() {
        String url = _request.getRequestURL().toString();
        return url.contains("/CriticalReading");

    }

    public boolean getWriting() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Writing");
    }

    public boolean getFlagged() {
        String url = _request.getRequestURL().toString();
        return url.contains("/flagged/");

    }
}
