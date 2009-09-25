package com.preppa.web.pages.search;

import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author nwt
 */
public class Index {

    @Property
    private String searchterm;


    void onActivate() {
        searchterm = "";
    }

}
