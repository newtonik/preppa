package com.preppa.web.services;

import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;

/**
 *
 * @author nwt
 */
public interface PassageService {
    void checkRegularPassage(LongPassage passage);
    void checkDualPassage(LongDualPassage passage);
}
