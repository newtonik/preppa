package com.preppa.web.components.longpassage;

import com.preppa.web.entities.LongDualPassage;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js","context:js/jquery.tools.min.js",  "context:js/passagetab.js"})
public class LongDualPassageTab {
@Parameter(required=true)
private LongDualPassage passage;
@Property
private Integer id;

@SetupRender
void setDefault() {
    id = passage.getId();
}
}
