package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Sentence;
import com.hys.exam.utils.PageList;

public interface SentenceManageDAO {

    Sentence getSentenceById(Long id);

    List<Sentence> getSentenceList(Sentence sentence);

    public void querySentencePageList(PageList<Sentence> pl, Sentence sentence);

    boolean addSentence(Sentence sentence);

    boolean updateSentence(Sentence sentence);

    boolean updateState(Long id, int state);

    boolean deleteSentenceById(Long id);

    Boolean updateMenu(Sentence sentence);
}