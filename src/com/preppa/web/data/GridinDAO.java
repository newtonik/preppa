package com.preppa.web.data;

import com.preppa.web.entities.Gridin;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface GridinDAO  extends GenericDAO<Gridin, Long> {
    Gridin findById(Long id);
    List<Gridin> findByUserIds(List<Long> ids);
}
