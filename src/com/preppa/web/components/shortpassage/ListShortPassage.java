package com.preppa.web.components.shortpassage;

import com.preppa.web.entities.ShortPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author nikhariale
 */
public class ListShortPassage {

    @Parameter
    @Property
    private List<ShortPassage> passages;
    @Property
    private ShortPassage passage;
}
