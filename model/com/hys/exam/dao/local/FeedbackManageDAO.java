package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.Feedback;

public interface FeedbackManageDAO {
	boolean add(Feedback feedback);



	int addFeedback(Feedback feedback);

	boolean addImage(String result);



//	boolean addImage(String result);

}
