/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.entities.Article;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.chenillekit.tapestry.core.components.Window;

/**
 *
 * @author newtonik
 */
public class ShowArticle {
@Persist
@Property
private Article article;

@Component(parameters = {"style=bluelighting", "show=true",
                         "modal=true", "title=literal:Window 1"})
private Window window1;

void onActivate(Integer id) {
    
}

}
