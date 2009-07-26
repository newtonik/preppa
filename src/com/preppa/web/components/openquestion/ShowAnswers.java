package com.preppa.web.components.openquestion;

import com.preppa.web.entities.OpenAnswer;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author nwt
 */
public class ShowAnswers {
    @Parameter
    @Property
    private List<OpenAnswer> answers;
    @Property
    private OpenAnswer answer;


    
}
