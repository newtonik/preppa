/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages;

import com.preppa.web.entities.UserOb;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author newtonik
 */
public class Guess {

    @Persist
    private int target;
    @Persist
    @Property
    private int guess;
    @Persist
    @Property
    private String message;
    @Persist
    @Property
    private int count;
    @ApplicationState
    private UserOb myUser;

    Object initialize(int target) {
        this.target = target;
        this.count = 0;

        return this;
    }

    public int getTarget() {
        return target;
    }

    Object onActionFromLink(int guess) {
        count++;

        if (guess == target) {
            return "GameOver";
        }

        if (guess < target) {
            message = String.format("%d is too low.", guess);
        } else {
            message = String.format("%d is too high.", guess);
        }

        return null;
    }

    /**
     * @return the myUser
     */
    public UserOb getMyUser() {
        return myUser;
    }

    public String getPassedMessage() {
        return this.message;
    }

    void setPassedMessage(String message) {
        this.message = message;
    }
}
