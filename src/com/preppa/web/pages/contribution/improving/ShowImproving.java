package com.preppa.web.pages.contribution.improving;

import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.entities.Tag;
import com.preppa.web.pages.Index;
import com.preppa.web.utils.Constants;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nikhariale
 */
public class ShowImproving {
    @Property
    private ImprovingParagraph improving;
    @Inject
    private ImprovingParagraphDAO improvingDAO;
    @Property
    private ContentType contentType;
    @Property
    private Long contId;
    @InjectPage
    private Index index;
    @Property
    private List<Tag> tags;
    @Property
    private Integer votes;
    @Property
    private boolean isApproved;
    @Inject
    private VoteDAO voteDAO;

    Object onActivate(Long id) {

        if(id > 0) {
            improving = improvingDAO.doRetrieve(id, false);
            contentType = ContentType.ImprovingParagraph;
            contId = id;
            tags = improving.getTaglist();
            votes = voteDAO.findVoteByContentId(contentType, improving.getId().intValue());

            if (votes >= Constants.getApprovalThreshhold()) {
                isApproved = true;
            }
            else {
                isApproved = false;
            }

            return null;

        }
        else
        {
            return index;
        }
    }
    Long onPassivate() {
        return improving.getId();
    }

    public void setImprovingParagraph(ImprovingParagraph paragraph) {
        improving = paragraph;
    }
}
