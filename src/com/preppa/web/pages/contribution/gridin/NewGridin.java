package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author nwt
 */
public class NewGridin {
    @Property
    private Gridin gridin;
    @Property
    private String fTitle;
    @Component
    private Form qridinForm;
    @Property
    private String fQuestion;
    @Component(parameters = {"value=fQuestion"})
    private Editor questionBody;
    private List<GridinAnswer> answers;

    void onActivate() {
        gridin = new Gridin();
    }
   


}
