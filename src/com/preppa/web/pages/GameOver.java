/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author newtonik
 */
public class GameOver {

  @Persist
  @Property
  private int count;

  Object initialize(int count)
  {
    this.count = count;

    return this;
  }

}
