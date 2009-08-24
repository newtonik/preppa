package com.preppa.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author nwt
 */
public class VoteSeal {

    @Parameter
    @Property
    private Integer count;
    @Property
    private Boolean voted;

    @SetupRender
    void setDefault() {
        if(count == null) {
            count = 0;
        }
        else
        {
            if(count < 0)
                count = 0;
        }
        voted = false;
    }
    JSONObject onActionFromVoteUp() {
        System.out.println("Clicked");
        JSONObject json = new JSONObject();
        if(!voted) {
            count++;
            voted = true;
            json.put("voted", "true");
        }
        else
        {
            count--;
            voted = false;
            json.put("voted", "false");
        }
        return json;
    }

    JSONObject onActionFromVoteDown() {
        System.out.println("Clicked");
        JSONObject json = new JSONObject();
        if(!voted) {
            count--;
            voted = true;
            json.put("voted", "true");

        }
        else
        {
            count++;
            voted = false;
            json.put("voted", "false");
        }
        return json;
    }
}
