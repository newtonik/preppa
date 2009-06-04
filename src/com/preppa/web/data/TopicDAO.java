/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Topic;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface TopicDAO  extends GenericDAO <Topic, Integer>{

    public List<Topic> findByPartialName(String partial);

}
