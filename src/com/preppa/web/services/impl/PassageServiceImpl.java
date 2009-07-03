package com.preppa.web.services.impl;

import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.services.PassageService;
import com.preppa.web.utils.PassageType;

/**
 * This class will be use to verify the properties of a passage, it will act as
 * a state machine.
 * @author nwt
 */
public class PassageServiceImpl implements PassageService {

    @Override
    public void checkLongPassage(LongPassage passage) {
        if(passage.getNumQuestions() == null) {
            passage.setNumQuestions(0);
        }
        else
        {
            passage.setNumQuestions(passage.getQuestions().size());
        }
    
           if(passage.getNumQuestions() >= 4) {
               passage.setComplete(true);
           }
           else 
           {
               passage.setComplete(false);
           }
    
    
    }

    @Override
    public void checkLongDualPassage(LongDualPassage passage) {
        if(passage.getNumQuestions() == null) {
            passage.setNumQuestions(0);
        }
        else
        {
            passage.setNumQuestions(passage.getQuestions().size());
        }

           if(passage.getNumQuestions() >= 8) {
               passage.setComplete(true);
           }
           else
           {
               passage.setComplete(false);
           }
    }




    @Override
    public void checkShortPassage(ShortPassage passage) {

      if(passage.getNumQuestions() == null) {
            passage.setNumQuestions(0);
        }
        else
        {
            passage.setNumQuestions(passage.getQuestions().size());
        }


           if(passage.getNumQuestions() >= 2) {
               passage.setComplete(true);
           }
           else
           {
               passage.setComplete(false);
           }

    }

    @Override
    public void checkShortDualPassage(ShortDualPassage passage) {
          if(passage.getNumQuestions() == null) {
            passage.setNumQuestions(0);
        }
        else
        {
            passage.setNumQuestions(passage.getQuestions().size());
        }

           if(passage.getNumQuestions() >= 4) {
               passage.setComplete(true);
           }
           else
           {
               passage.setComplete(false);
           }
    }

}
