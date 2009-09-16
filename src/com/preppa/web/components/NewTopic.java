/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */
package com.preppa.web.components;

import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import com.preppa.web.utils.InjectSelectionModel;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;

/**
 *
 * @author newtonik
 */
@IncludeJavaScriptLibrary(value = {"context:js/newtopic.js"})
public class NewTopic {

    @Inject
    @Property
    private Block newtopicblock;
    @Property
    private Testsubject topicSubject;
    @Component(parameters = {"value=topicSubject"})
    private Select select2;
    @Component
    private Form topicform;
    @Property
    private String fTopic;
    @Property
    private String fTopicName;
    @Inject
    private TopicDAO topicDAO;
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Testsubject> testsubjects = new ArrayList<Testsubject>();
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Inject
    private Request request;


    @SetupRender
    void setUpItems() {
                     Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
                testsubjects.clear();
              testsubjects.addAll(setItems);
    }


        @CommitAfter
        JSONObject onSuccessFromTopicForm() {
            JSONObject json = new JSONObject();
            System.out.println("trying to save " + fTopicName);
          Topic topic = new Topic();
           topic.setName(fTopicName);
          topic.setTestsubject(topicSubject);

          if(request.isXHR()) {
              System.out.println("It was an ajax request");
          }
         if(topicDAO.findSizeByName(fTopicName, topicSubject) > 0) {
             String markup = "<p> There is already a <b>" + fTopicName +
                    "</b> topic in " + topicSubject.getName() + " Section.<p>";
                json.put("content", markup);


         }
         else
         {
             Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

             topic.setCreatedAt(now);
             topic.setUpdatedAt(now);
             System.out.println("Topic is being saved");
            topicDAO.doSave(topic);
             String markup = "<p> You just submitted <b>" + topic.getName() +
                    "</b>. Please add it using the topics autocomplete. <p>";
               json.put("content", markup);

         }
          return json;
        }


        Block onActionFromCloseTopic() {
            return newtopicblock;

        }
}
