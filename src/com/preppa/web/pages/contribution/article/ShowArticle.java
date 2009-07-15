/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.User;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowArticle {
@Persist
@Property
private Article article;
@Inject
private ArticleDAO articleDAO;
@Property
private User author;
@Property
private String authorname;
void onActivate(int id) {
            this.article = articleDAO.findById(id);
            this.author = article.getUser();
            if(author != null) {
                authorname = author.getUsername();
            }
            if(authorname == null)
            {
                authorname = "unknown dude";
            }
            
 }

 public void set(String title) {
        this.article = articleDAO.findByTitle(title);
        this.author = article.getUser();
        if(author != null) {
            authorname = author.getUsername();
        }
        if(authorname == null)
        {
            authorname = "unknown dude";
        }

 }
void onPassivate() {
   
}

void setarticle(Article article) {
        this.article = article;
        this.author = article.getUser();
            if(author != null) {
            authorname = author.getUsername();
        }
    
         if(authorname == null)
         {
            authorname = "unknown dude";
        }

    }

}
