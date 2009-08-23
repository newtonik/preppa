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

    public Integer findSumByDualLongPassage(Integer id);

    public Integer findSumByDualShortPassage(Integer id);

    public Integer findSumByShortPassage(Integer id);
    public List<Vote> findVoteByContentId(ContentType contentType);
    public Integer findVoteByContentId(ContentType contentType, Integer contentId);
    public Boolean checkVoted(ContentType contentType, Integer contentId, User user);
    public Integer findSumByQuestionId(Integer contentId);
    public Integer findSumByGridInId(Integer contentId);
    public Integer findSumByShortPassage(Integer contentId);
    Integer findSumByDualLongPassage(Integer contentId);
    Integer findSumByLongPassage(Integer contentId);
    Integer findSumByDualShortPassage(Integer contentId);
}
