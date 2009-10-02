/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.longpassage;

import com.preppa.web.entities.LongPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author newtonik
 */
public class ListLongPassage {
    @Parameter
     @Property
    private List<LongPassage> passages;
    @Property
    private LongPassage passage;

    public String getUsername() {
        if (passage.getUser() != null) {
            return passage.getUser().getUsername();
        }
        else {
            return "";
        }
    }
}
