/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.questiontypes.prompt;

import com.preppa.web.entities.Prompt;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author newtonik
 */
public class ListPrompt {
    @Property
    @Parameter
    private List<Prompt> allprompts;
    @Property
    private Prompt prompt;


}
