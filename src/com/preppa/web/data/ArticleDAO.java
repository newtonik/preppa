/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Article;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface ArticleDAO extends GenericDAO< Article, Integer> {
    Article findById(Integer id);
}