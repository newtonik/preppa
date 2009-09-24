package com.preppa.web.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;




/**
 *
 * @author newtonik
 */
@Entity
@Audited
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private List<OpenQuestion> openquestions;
    private List<Article> articles;
    private List<Gridin> gridins;
    private List<Question> questions;
    private List<Prompt> prompt;

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @return the name
     */
    @Column(unique=true)
    @Field(index=Index.TOKENIZED)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the openquestions
     */
    @ContainedIn
    @ManyToMany(mappedBy="taglist")
    public List<OpenQuestion> getOpenquestions() {
        return openquestions;
    }

    /**
     * @return the articles with a tag
     */
    @ContainedIn
    @ManyToMany(mappedBy="taglist")
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * @return the questions with a tag
     */
    @ContainedIn
    @ManyToMany(mappedBy="taglist")
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @param openquestions the openquestions to set
     */
    public void setOpenquestions(List<OpenQuestion> openquestions) {
        this.openquestions = openquestions;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    
    @ContainedIn
    @ManyToMany(mappedBy = "taglist")
    public List<Gridin> getGridins() {
        return gridins;
    }

    public void setGridins(List<Gridin> gridins) {
        this.gridins = gridins;
    }


    @ContainedIn
    @ManyToMany(mappedBy = "taglist")
    public List<Prompt> getPrompts() {
        return prompt;
    }

    public void setPrompts(List<Prompt> prompts) {
        this.prompt = prompts;
    }

}
