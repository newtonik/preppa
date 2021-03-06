
package com.preppa.web.data;

import com.preppa.web.entities.Tag;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface TagDAO extends GenericDAO< Tag, Integer> {
    Tag findById(Integer id);
    List<Tag> findByName(String name);
    List<Tag> findByPartialName(String partialName);
   List<Tag> findByPartialName(String partialName, List<Integer> notlist);
}
