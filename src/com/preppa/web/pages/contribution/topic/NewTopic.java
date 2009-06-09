/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.topic;

import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Topic;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class NewTopic {
    @Property
    private Topic topic;
    @Inject
    private TopicDAO topicDAO;
    @Property
    private String fName;
    @InjectPage
    private Index index;

    Object onSubmit() {
        topic = new Topic();

         topic.setName(fName);
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

         topic.setCreatedAt(now);
         topic.setUpdatedAt(now);

         topicDAO.doSave(topic);

         return index;
    }

}
