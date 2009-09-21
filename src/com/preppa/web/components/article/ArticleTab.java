/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.article;

import com.preppa.web.entities.Article;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ArticleTab {
@Parameter(required=true)
private Article article;
@Property
private Integer id;
    @Inject
    private HttpServletRequest _request;


@SetupRender
void setDefault() {
    id = article.getId();
}

    public boolean getHistory() {
        String url = _request.getRequestURL().toString();
        return url.contains("/revisions/");
    }
    public boolean getDiscuss() {
        String url = _request.getRequestURL().toString();
        return url.contains("/talk/");
    }

    public boolean getArticle() {
        String url = _request.getRequestURL().toString();
        return url.contains("/article/");

    }
}
