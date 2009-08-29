package com.preppa.web.components.longpassage;

import com.preppa.web.entities.LongPassage;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/passagetab.js"})
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
