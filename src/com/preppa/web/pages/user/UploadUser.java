  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.user;

import java.io.File;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.upload.services.UploadedFile;

/**
 *
 * @author Jan Jan
 */
    public class UploadUser
    {
        @Property
        private UploadedFile file;
        @Persist(PersistenceConstants.FLASH)
         @Property
         private String message;

        public void onSuccess()
        {
            File copied = new File("./" + file.getFileName());

            file.write(copied);
        }

        Object onUploadException(FileUploadException ex)
        {
            message = "Upload exception: " + ex.getMessage();

            return this;
        }
    }