package com.preppa.web.components.longpassage;

import com.preppa.web.entities.LongDualPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
/**
 *
 * @author newtonik
 */
public class ListDualLongPassage {

        @Parameter
     @Property
    private List<LongDualPassage> passages;
    @Property
    private LongDualPassage passage;

    public String getUsername() {
        if (passage.getUser() != null) {
            return passage.getUser().getUsername();
        }
        else {
            return "";
        }
    }
}
