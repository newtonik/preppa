package com.preppa.web.data;

import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface VoteDAO  extends GenericDAO <Vote, Long> {

    public List<Vote> findVoteByContentId(ContentType contentType);
    public Integer findVoteByContentId(ContentType contentType, Integer contentId);
    public Boolean checkVoted(ContentType contentType, Integer contentId, User user);
    public Integer findSumByQuestionId(Integer contentId);
}
