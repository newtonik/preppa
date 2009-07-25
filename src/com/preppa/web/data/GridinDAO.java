package com.preppa.web.data;

import com.preppa.web.entities.Gridin;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface GridinDAO  extends GenericDAO<Gridin, Integer> {
    Gridin findById(Long id);
}
