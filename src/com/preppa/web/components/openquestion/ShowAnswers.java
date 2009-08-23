package com.preppa.web.components.openquestion;

import com.preppa.web.entities.OpenAnswer;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

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
    @Property
    private String count;


    @SetupRender
    void SetDefaults() {
        Integer counter = answers.size();
        if(counter < 2)
            count = counter.toString() + " Answer";
        else
            count = counter.toString() + " Answers";
    }

}
