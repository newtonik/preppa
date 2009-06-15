/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.components;

import java.util.MissingResourceException;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/site.css", "context:js/lightbox/css/lightbox.css"})
@IncludeJavaScriptLibrary(value = {"context:js/Chenillekit.js", "context:js/lightbox/scripts/lightbox.js"})
public class Layout {

    @Inject
    private ComponentResources resources;
    @Parameter(required = true, defaultPrefix = "literal")
    private String pageTitle;

    //final static ResourceBundle rb = ResourceBundle.getBundle("version.properties");

    public String getPageTitle() {
        return pageTitle;
    }
    

    private String getPageName() {
        Component page = resources.getContainer();
        return page.getClass().getName();
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
}
