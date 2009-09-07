  
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
public class PromptMenu {
    @Inject
    private HttpServletRequest _request;

    public boolean getGraded() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Graded");
    }


    public boolean getNonGraded() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Non");
    }

    public boolean getPrompt() {
        String url = _request.getRequestURL().toString();
        return url.contains("/prompt/") && !url.contains("/Non") && !url.contains("/Graded");
    }
}
