  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.components;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;

/**
 *
 * @author Jan Jan
 */
public class ArticleSearch {
    @Property
    private String dWord;
	@Component(id = "articlesearch")
	private Form myform;
}
