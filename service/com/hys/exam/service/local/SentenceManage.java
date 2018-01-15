package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.Sentence;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface SentenceManage extends BaseService {

    Sentence getSentenceById(Long id);

    List<Sentence> getSentenceList(Sentence sentence);

    public void querySentencePageList(PageList<Sentence> pl, Sentence sentence);

    boolean addSentence(Sentence sentence);

    boolean updateSentence(Sentence sentence);

    boolean updateState(Long id, int state);

    boolean deleteSentenceById(Long id);

    boolean updateSentence(Long id);

    Boolean updateMenu(Sentence sentence);

}
