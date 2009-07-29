package com.preppa.web.data;

import com.preppa.web.entities.Flag;
import com.preppa.web.entities.FlagType;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface FlagDAO extends GenericDAO<Flag, Integer>  {
    List<Flag> FindAllByContentType(ContentType t);
    List<Flag> FindAllByFlagType(ContentType t, ContentFlag type);
}
