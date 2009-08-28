package com.preppa.web.components.longpassage;

import com.preppa.web.entities.LongPassage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author nwt
 */
public class LongPassageTab {
@Parameter(required=true)
private LongPassage passage;
@Property
private Integer id;

@SetupRender
void setDefault() {
    id = passage.getId();
}
}
