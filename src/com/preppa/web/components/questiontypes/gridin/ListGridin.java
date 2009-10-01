/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.questiontypes.gridin;

import com.preppa.web.entities.Gridin;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author newtonik
 */
public class ListGridin {
    @Property
    @Parameter
    private List<Gridin> gridins;
    @Property
    private Gridin gridin;

}
