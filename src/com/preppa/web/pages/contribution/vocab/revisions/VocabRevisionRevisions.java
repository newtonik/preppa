package com.preppa.web.pages.contribution.vocab.revisions;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;

import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author nwt
 */
public class VocabRevisionRevisions {
  @Property
    private Vocab vocab;
    @Inject
    private VocabDAO vocabDAO;
    private String example = "before";
    @InjectPage
    private Index index;
    private Integer vid;
    private List<Flag> vocabflags;
    @Property
    private List<Tag> tags;
    @Inject
    private HibernateSessionManager sessionManager;
    private Long revisionNumber;

    void onActivate(String params) {

        String[] tokens = params.split("_");
        Integer vId = Integer.parseInt(tokens[0]);
        Long revId = Long.parseLong(tokens[1]);
        if (vId > 0) {

            AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        this.revisionNumber = revId;

        this.vocab = reader.find(Vocab.class, vId, revisionNumber);
            this.vid = vId;
            if (this.vocab != null) {
                if (vocab.getSentence() == null) {
                    example = "";
                } else {
                    example = vocab.getSentence().getSentence();
                    System.out.println(example);
                }
                vid = vocab.getId();
                vocabflags = vocab.getFlags();
                tags = vocab.getTaglist();
            }

        }

    }

    Integer onPassivate() {
        return this.vid;

    }

    public String getExample() {
        if (vocab.getSentence() == null) {
            example = "";
        } else {
            example = vocab.getSentence().getSentence();
            System.out.println(example);
        }
        return example;
    }


}
