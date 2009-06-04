/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.questiontype;

import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.pages.Index;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.BeanSelect;

/**
 *
 * @author newtonik
 */
public class NewQuestiontype {

    
    @Persist
    private Questiontype questiontype;
    @Property
    @Persist
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private QuestiontypeDAO questiontypeDAO;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Property
    private String fullName;
    

    void NewQuestiontype() {
       // this.questiontype = new Questiontype();
        this.setTestsubjects(testsubjectDAO.findAll());
    }

    @InjectPage
    private Index index;

    void onActivate(Questiontype questiontype) {


        this.questiontype = new Questiontype();
    }

    Object onPassivate() {
        return questiontype;
    }

    @CommitAfter
    Object onSubmitFromQuestionForm() {
        this.questiontype = new Questiontype();
       System.out.println(fullName);
        questiontype.setName(fullName);
        questiontype.setTestsubject(testsubject);
        questiontype.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        questiontype.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        //article.setUser(userDAO.findById(1));
        System.out.println("I have succeded");
        questiontypeDAO.doSave(questiontype);
        return index;
    }

    /**
     * @return the testsubjects
     */
    public List<Testsubject> getTestsubjects() {
        this.setTestsubjects(testsubjectDAO.findAll());
        return testsubjects;
    }

    /**
     * @param testsubjects the testsubjects to set
     */
    public void setTestsubjects(List<Testsubject> testsubjects) {
        this.testsubjects = testsubjects;
    }



}
