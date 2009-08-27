/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.article;

import com.preppa.web.entities.Article;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author newtonik
 */
public class ArticleTab {
@Parameter(required=true)
private Article article;
@Property
private Integer id;



@SetupRender
void setDefault() {
    id = article.getId();
}
}
