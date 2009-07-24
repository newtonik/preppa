package com.preppa.web.data;

import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface VoteDAO  extends GenericDAO <Vote, Long> {

    public Integer findVoteByContentId(ContentType contentType, Integer contentId);
}
