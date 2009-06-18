/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.components;

import java.util.MissingResourceException;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/site.css", "context:js/lightbox/css/lightbox.css"})
@IncludeJavaScriptLibrary(value = {"context:js/Chenillekit.js", "context:js/lightbox/scripts/lightbox.js", "context:js/jquery-1.2.6.js"})
public class Layout {

    @Inject
    private ComponentResources resources;
    @Parameter(required = true, defaultPrefix = "literal")
    private String pageTitle;
    @Parameter
    private Block left;
     @Component
     private Left leftRegularContent;

     @Parameter
     private Block rightblockone;
     @Component
     private Right rightblockoneregularContent;

     @Parameter
     private Block rightblocktwo;
     @Component
    private Right rightblocktworegularContent;

     @Parameter
     private Block rightblockthree;
     @Component
     private Right rightblockthreeregularContent;

    //final static ResourceBundle rb = ResourceBundle.getBundle("version.properties");

    public String getPageTitle() {
        return pageTitle;
    }
   /**
        * This method check if the left parameter has been set by the user
        * if not the regular content is shown otherwise the content of this parameter
        *
        * @return the component we want to display
    */
    public Object getLeftContent() {
        return left==null ?  getLeftRegularContent() : left;

    }   

    private String getBuildNumber() {
          String msg = "";
        try {
         //   msg = rb.getString("BUILD");
        } catch (MissingResourceException e) {
            System.err.println("Token ".concat("BUILD").concat(" not in Propertyfile!"));
        }
        return msg;
    }

    /**
     * @return the leftRegularContent
     */
    public Left getLeftRegularContent() {
        return leftRegularContent;
    }

    /**
     * @return the rightblockone
     */
    public Object getRightblockone() {
        return rightblockone == null ? rightblockoneregularContent : rightblockone;
    }

    /**
     * @return the rightblocktwo
     */
    public Object getRightblocktwo() {
        return rightblocktwo == null ? rightblocktworegularContent : rightblocktwo;
    }

    /**
     * @return the rightblockthree
     */
    public Object getRightblockthree() {
        return rightblockthree == null ? rightblockthreeregularContent : rightblockthree;
    }


}
