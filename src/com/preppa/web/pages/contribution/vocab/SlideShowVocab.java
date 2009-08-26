/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import java.util.List;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/vocabslide.css"})
@IncludeJavaScriptLibrary(value = { "context:js/jquery-1.3.2.js", "context:js/jquery/jquery.tools.min.js", "context:js/vocabslide.js"})
public class SlideShowVocab {
    @Inject
    private VocabDAO vocabDAO;
    @Property
    private List<Vocab> allVocab;
    @Property
    private Vocab listVocab;
    void onActivate() {
        allVocab = vocabDAO.findAll();
    }

}
