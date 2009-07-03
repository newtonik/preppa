package com.preppa.web.services;

import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;

/**
 *
 * @author nwt
 */
public interface PassageService {
    void checkLongPassage(LongPassage passage);
    void checkLongDualPassage(LongDualPassage passage);
     void checkShortPassage(ShortPassage passage);
    void checkShortDualPassage(ShortDualPassage passage);
}
