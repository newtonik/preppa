package com.preppa.web.pages.contribution.vocab.flagged;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.FlagDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.FlagType;
import com.preppa.web.entities.User;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.springframework.security.annotation.Secured;


/**
 *
 * @author nwt
 */
@Secured("ROLE_USER")
@IncludeJavaScriptLibrary(value = {"context:js/flagged.js"})
public class Index {
    @ApplicationState
    private User user;
    @Property
    private List<Article> articles;
    @Property
    private Article article;
    @Inject
    private ArticleDAO articleDAO;
    @Inject
    private FlagDAO flagDAO;
    @Property
    private FlagType flagtype;
    @Inject
    @Property
    private Block resultblock;
    @InjectComponent
    private Zone articleviewzone;
    @Persist
    @Property
    private String flagvalue;
    @Property
    private String username;
    @Property
    private List<Flag> flags;
    @Property
    private Flag flag;


    @Component(parameters = {"value=flagvalue",  "event=change",
                         "onCompleteCallback=literal:onChangeFlag"})
    @Mixins({"ck/OnEvent"})
    private Select flagSelect;


   @Validate("required")
    public  FlagType getFlagType() { return flagtype; }

    public void setType( FlagType type) { flagtype = type; }

    void onActivate() {
        flags = flagDAO.FindAllByContentType(ContentType.Vocab);
       System.out.println("There are " + flags.size() + " flags");
       
//        if(flags.size() > 0) {
//            for(Flag f : flags) {
//                 System.out.println(f.getArticle().getTitle());
//                 System.out.println(f.getFlagger().getUsername());
//                 System.out.println(f.getFlagtype());
//                 System.out.println(f.getCreatedAt());
//
//            }
//        }

    }

    Block onChangeFromFlagSelect(String selected) {
        JSONObject json = new JSONObject();
       if(selected != null && !selected.equals(""))
       {
           json.put("flag", selected);
          // Active, Incorrect, Spam, Inappropriate, Copyright, Attention
           if(selected.equals("Incorrect"))
           {
               flags = flagDAO.FindAllByFlagType(ContentType.Vocab, ContentFlag.Incorrect);
           }
           else  if(selected.equals("Spam"))
           {
               flags = flagDAO.FindAllByFlagType(ContentType.Vocab, ContentFlag.Spam);
           }
           else if(selected.equals("Inappropriate"))
           {
               flags = flagDAO.FindAllByFlagType(ContentType.Vocab, ContentFlag.Inappropriate);
           }
           else if(selected.equals("Copyright"))
           {
               flags = flagDAO.FindAllByFlagType(ContentType.Vocab, ContentFlag.Copyright);
           }
           else  if(selected.equals("Attention"))
           {
               flags = flagDAO.FindAllByFlagType(ContentType.Vocab, ContentFlag.Attention);
           }
           


          //json.put("content", r.getBody());
       }

       else
       {
            json.put("flag", "-1");
       }

        return resultblock;
    }



}
