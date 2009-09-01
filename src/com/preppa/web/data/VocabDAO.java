/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Vocab;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface VocabDAO extends GenericDAO <Vocab, Integer> {

    public List<Vocab> findByName(String fWord);

    Vocab findById(Integer id);
    List<Vocab> findByUserId(Integer id);
    List<Vocab> findAllOrderedByName();
    List<Vocab> findAllOrderedByPartOfSpeech(String pos);
    public List<Vocab> findByLetter(Character lower);
    List<Vocab> findByPartialName(String partialName);
    List<Vocab> findByUserIds(List<Integer> ids);
}
