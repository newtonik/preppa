/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;


/**
 *
 * @author newtonik
 */
@Entity
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Questiontype questiontype;
    private String question;
    private List<Tag> taglist = new ArrayList<Tag>();
    private String source;
    private String explanation;
    private Integer difficulty;
    private List<QuestionAnswer> choices = new ArrayList<QuestionAnswer>();
    //this will be used for validation.
    private Integer numCorrect;
    private String correctAnswer;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private ContentFlag status;
    private String revComment;
    private Boolean image;
    private String imagePath;
    private List<Flag> flags  = new ArrayList<Flag>();
    private Set<Vote> votes;
    private Integer voteScore;
    private User updatedBy;



    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Question(Integer id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @NonVisual
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Lob
    @Column(name = "question")
    @Audited
    public String getQuestion() {
        return question;
    }
    @Transient
    public String getQuestionFormatted() {
         //Removed html tags
        String returnVal = question.substring(3, question.length()-4);
        return returnVal;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    
    @Lob
    @Column(name = "source")
    @Audited
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Lob
    @Column(name = "explanation")
    @Audited
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    @NonVisual
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Audited
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Questions[id=" + id + "]";
    }

    /**
     * @return the questiontype
     */
    @ManyToOne(targetEntity = Questiontype.class)
    @JoinColumn(name = "questiontype_id")
    @Audited
    public Questiontype getQuestiontype() {
        return questiontype;
    }

    /**
     * @param questiontype the questiontype to set
     */
    public void setQuestiontype(Questiontype questiontype) {
        this.questiontype = questiontype;
    }

    /**
     * @return the difficulty
     */
    @Audited
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the numCorrect
     */
    @Audited
    public Integer getNumCorrect() {
        return numCorrect;
    }

    /**
     * @param numCorrect the numCorrect to set
     */
    public void setNumCorrect(Integer numCorrect) {
        this.numCorrect = numCorrect;
    }

    /**
     * @return the choices
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    @Audited
    public List<QuestionAnswer> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<QuestionAnswer> choices) {
        this.choices = choices;
    }

    /**
     * @return the correctAnswer
     */
    @Audited
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param correctAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return the taglist
     */
    @Audited
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Tag.class)
    @JoinTable(name = "Question_Tag", joinColumns = {@JoinColumn(name = "question_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    public List<Tag> getTaglist() {
        return taglist;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(List<Tag> tagltist) {
        this.taglist = tagltist;
    }

    /**
     * @return the user
     */
    @ManyToOne
    @Audited
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the status
     */
    @Enumerated(EnumType.ORDINAL)
    @Audited
    public ContentFlag getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ContentFlag status) {
        this.status = status;
    }

    /**
     * @return the revComment
     */
    @Audited
    @Lob
    public String getRevComment() {
        return revComment;
    }

    /**
     * @param revComment the revComment to set
     */
    public void setRevComment(String revComment) {
        this.revComment = revComment;
    }

    /**
     * @return the image
     */
    @Audited
    public Boolean getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Boolean image) {
        this.image = image;
    }

    /**
     * @return the imagePath
     */
    @Audited
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the flags
     */
    @OneToMany(mappedBy = "question", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Audited
    public List<Flag> getFlags() {
        return flags;
    }

    /**
     * @param flags the flags to set
     */
    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

    /**
     * @return the votes
     */

    @ManyToMany
    public Set<Vote> getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    /**
     * @return the voteScore
     */
    public Integer getVoteScore() {
        return voteScore;
    }

    /**
     * @param voteScore the voteScore to set
     */
    public void setVoteScore(Integer voteScore) {
        this.voteScore = voteScore;
    }

    /**
     * @return the updatedBy
     */

    @ManyToOne
    @Audited
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

  

}
