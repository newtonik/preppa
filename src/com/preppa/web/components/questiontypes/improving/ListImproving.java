/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.questiontypes.improving;

import com.preppa.web.entities.ImprovingParagraph;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author newtonik
 */
public class ListImproving {
    @Parameter
    @Property
    private List<ImprovingParagraph> paragraphs;
    @Property
    private ImprovingParagraph paragraph;

}
