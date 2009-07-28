/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Announcement;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface AnnouncementDAO extends GenericDAO< Announcement, Integer>  {
    Announcement findById(Integer id);

    public List<Announcement> findAllOrderedByDate();
}
