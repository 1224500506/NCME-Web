package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.SentenceManageDAO;
import com.hys.exam.model.Sentence;
import com.hys.exam.service.local.SentenceManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class SentenceManageImpl extends BaseMangerImpl implements
        SentenceManage {

    private SentenceManageDAO localSentenceMangeDAO;

    @Override
    public Sentence getSentenceById(Long id) {
        return localSentenceMangeDAO.getSentenceById(id);
    }

    @Override
    public List<Sentence> getSentenceList(Sentence sentence) {
        return localSentenceMangeDAO.getSentenceList(sentence);
    }

    @Override
    public boolean updateSentence(Sentence sentence) {
        return localSentenceMangeDAO.updateSentence(sentence);
    }

    @Override
    public boolean deleteSentenceById(Long id) {
        return localSentenceMangeDAO.deleteSentenceById(id);
    }

    @Override
    public boolean updateState(Long id, int state) {
        return localSentenceMangeDAO.updateState(id, state);
    }

    public SentenceManageDAO getLocalSentenceMangeDAO() {
        return localSentenceMangeDAO;
    }

    public void setLocalSentenceMangeDAO(SentenceManageDAO localSentenceMangeDAO) {
        this.localSentenceMangeDAO = localSentenceMangeDAO;
    }

    @Override
    public boolean addSentence(Sentence sentence) {
        if (localSentenceMangeDAO.addSentence(sentence))
            return true;
        else
            return false;
    }

    @Override
    public boolean updateSentence(Long id) {
        return false;
    }

    @Override
    public Boolean updateMenu(Sentence sentence) {
        return localSentenceMangeDAO.updateMenu(sentence);

    }

    @Override
    public void querySentencePageList(PageList<Sentence> pl, Sentence sentence) {
        localSentenceMangeDAO.querySentencePageList(pl, sentence);
    }
}
