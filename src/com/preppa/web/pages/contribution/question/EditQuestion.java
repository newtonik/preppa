/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.question;

import com.preppa.web.components.questiontypes.multichoice.EditMultiChoice;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Tag;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@Secured("ROLE_USER")
public class EditQuestion {

    @Component
    private EditMultiChoice editquestion;
    @Property
    private Question ques;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private String fQuestion;
    @Property
    private String fExplanation;
    @Property
    private String fTag;
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
    private String correct;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();

    void onActivate(int id) {
        this.ques = questionDAO.findById(id);
//        if(ques != null) {
//            fQuestion = ques.getQuestion();
//            fExplanation = ques.getExplanation();
//            ans1 = ques.getChoices().get(0).getAnswer();
//            ans2 = ques.getChoices().get(1).getAnswer();
//            ans3 = ques.getChoices().get(2).getAnswer();
//            ans4 = ques.getChoices().get(3).getAnswer();
//            ans5 = ques.getChoices().get(4).getAnswer();
//            addedTags = ques.getTaglist();
//            ratingValue = ques.getDifficulty();
//            correct = ques.getCorrectAnswer();
//        }
    }

    Integer onPassivate() {
        return ques.getId();
    }
    /*
    void onValidateFormFromCreateQuestionForm(){
    System.out.println("Validating");
    if(mywork == false) {
    editquestionform.recordError("You must verify that this is your own work.");
    }
    if ((correct == null)) {
    editquestionform.recordError(answergroup, "You did not specify an answer.");
    }

    if(ratingValue == null) {
    editquestionform.recordError(ratingField, "You need to select a difficulty");
    }
    }

    @CommitAfter
    Object onSuccess(){
    
    ques.setExplanation(fExplanation);
    ques.setQuestion(fQuestion);
    if(ans1.length() > 0) {
    ques.getChoices().get(0).setAnswer(ans1);

    }
    if(ans2.length() > 0) {
    ques.getChoices().get(1).setAnswer(ans2);
    }
    if(ans3.length() > 0) {
    ques.getChoices().get(2).setAnswer(ans3);
    }
    if(ans4.length() > 0) {
    ques.getChoices().get(3).setAnswer(ans4);
    }
    if(ans5.length() > 0) {
    ques.getChoices().get(4).setAnswer(ans5);
    }
    for(Tag t: addedTags)
    {
    if(!(ques.getTaglist().contains(t)))
    {
    ques.getTaglist().add(t);
    }
    }
    ques.setDifficulty(ratingValue);
    Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
    ques.setUpdatedAt(now);
    questionDAO.doSave(ques);
    showquestion.setquestion(ques);
    return showquestion;
    }
    List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
    List<Tag> matches = tagDAO.findByPartialName(partial);
    return matches;

    }
    public FieldTranslator getTagTranslator()
    {
    return new FieldTranslator<Tag>()
    {
    @Override
    public String toClient(Tag value)
    {
    String clientValue = "0";
    if (value != null)
    clientValue = String.valueOf(value.getName());

    return clientValue;
    }

    @Override
    public void render(MarkupWriter writer) { }

    @Override
    public Class<Tag> getType() { return Tag.class; }

    @Override
    public Tag parse(String clientValue) throws ValidationException
    {
    Tag serverValue = null;
    //            if(clientValue == null) {
    //                Tag t = new Tag();
    //                t.setName(clientValue);
    //            }
    System.out.println(clientValue);

    if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
    System.out.println(clientValue);
    serverValue = tagDAO.findByName(clientValue).get(0);
    }
    return serverValue;
    }

    };
    }

     */
}
