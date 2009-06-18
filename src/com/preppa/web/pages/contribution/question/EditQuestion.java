/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.RatingField;


/**
 *
 * @author newtonik
 */
public class EditQuestion {
    @Property
    private Question question;
    @Inject
    private QuestionDAO questionDAO;
    @Component(parameters = {"value=fQuestion"})
    private Editor questioneditor;
    @InjectPage
    private Index indexpage;
    @Property
    private String fQuestion;
    @Property
    private String fExplanation;
    @Property
    private String fTag;
    @Component(parameters = {"value=ans1"})
    private Editor choice1;
    @Component(parameters = {"value=ans2"})
    private Editor choice2;
    @Component(parameters = {"value=ans3"})
    private Editor choice3;
    @Component(parameters = {"value=ans4"})
    private Editor choice4;
    @Component(parameters = {"value=ans5"})
    private Editor choice5;
    @Component
    private RatingField ratingField;
    @Property
    private Integer ratingValue;
    @Property
    private String ans1;
    @Property
    private String ans2;
    @Property
    private String ans3;
    @Property
    private String ans4;
    @Property
    private String ans5;
    @Property
    private Boolean c1;
    @Property
    private Boolean c2;
    @Property
    private Boolean c3;
    @Property
    private Boolean c4;
    @Property
    private Boolean c5;
    @InjectPage
    private ShowQuestion showquestion;

}
