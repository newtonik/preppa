package com.preppa.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author nwt
 */
public class VoteSeal {

    @Parameter
    @Property
    private Integer count;

    @SetupRender
    void setDefault() {
        if(count == null) {
            count = 0;
        }
        else
        {
            if(count < 0)
                count = 0;
        }
    }
}
