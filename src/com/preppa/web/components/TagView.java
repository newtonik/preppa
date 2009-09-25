package com.preppa.web.components;

import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Tag;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class TagView {
    @Inject
    private TagDAO tagDAO;
    @Parameter
    private List<Tag> allTags;
    @Property
    private Tag tag;
    @Parameter
    private Object content;
    @Property
    private String taglink;
    @Parameter
    private String searchlink;
    @SetupRender
    void initializeTags()
    {
        taglink = "contribution/article/search";
        if(searchlink != null)
        {
            taglink = searchlink;
        }
    }
    /**
     * @return the allTags
     */
    public List<Tag> getAllTags() {
        return allTags;
    }

    String getTagLinsk() {
        return "contribution/article/search";
    }


}
