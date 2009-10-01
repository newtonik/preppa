package com.preppa.web.components.shortpassage;

import com.preppa.web.entities.ShortDualPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author nikhariale
 */
public class ListDualShortPassage {
    @Parameter
     @Property
    private List<ShortDualPassage> passages;
    @Property
    private ShortDualPassage passage;

}
