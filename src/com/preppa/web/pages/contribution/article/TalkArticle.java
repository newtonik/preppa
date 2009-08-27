/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */
package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.ReviewComment;
import com.preppa.web.entities.User;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/jquery.tools.min.js", "context:js/article.js"})
public class TalkArticle {

    @ApplicationState
    private User user;
    @Property
    private Article article;
    @Inject
    private ArticleDAO articleDAO;
    @Property
    private List<ReviewComment> comments;
    @Property
    private ReviewComment comment;
    @Property
    private String fComment;
    @Component
    private Form commentform;
    @Component(parameters = {"value=fComment", "toolbarSet=Basic"})
    private Editor pass1;



    void onActivate(Integer id) {
        if (id > 0) {
            article = articleDAO.findById(id);
            comments = article.getReviewcomments();

        }
    }

    Integer onPassivate() {
        return article.getId();
    }

    Boolean onValidateFormFromCommentForm() {
         System.out.println("I validation here with comment " + fComment);
        if (fComment == null) {
            commentform.recordError(pass1, "You need to Enter a review");
        }
        else
        {

        }
        if (user == null) {
            commentform.recordError("You must be logged in to post a review");
        }

        return true;
    }

    @CommitAfter
    @Secured("ROLE_USER")
    Boolean onSuccessFromCommentForm() {
        System.out.println("I reached here with comment " + fComment);
        ReviewComment rc = new ReviewComment();
        rc.setComment(fComment);
        rc.setCommenter(user);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        rc.setCreatedAt(now);
        rc.setUpdatedAt(now);

        article.getReviewcomments().add(rc);

        articleDAO.doSave(article);
        return true;
    }

}
