package com.hys.exam.model;

public class LogStudy implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -466093895566172923L;
	
	
	Long Id;
	
	Long log_study_cv_unit_id;
	
	Long system_user_id;
	
	Long cv_id;
	
	Long cv_unit_id;
	
	Long testPaper_id;
	
	Long question_id;
	
	String question_Result;
	
	int isNot_Right;
	
	Integer questionLabel_id;
	
	Integer seq;
	
	Long parent_id;
	
	String select_result;
	
	String right_count;
	
	double score;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getLog_study_cv_unit_id() {
		return log_study_cv_unit_id;
	}

	public void setLog_study_cv_unit_id(Long log_study_cv_unit_id) {
		this.log_study_cv_unit_id = log_study_cv_unit_id;
	}

	public Long getSystem_user_id() {
		return system_user_id;
	}

	public void setSystem_user_id(Long system_user_id) {
		this.system_user_id = system_user_id;
	}

	public Long getCv_id() {
		return cv_id;
	}

	public void setCv_id(Long cv_id) {
		this.cv_id = cv_id;
	}

	public Long getCv_unit_id() {
		return cv_unit_id;
	}

	public void setCv_unit_id(Long cv_unit_id) {
		this.cv_unit_id = cv_unit_id;
	}

	public Long getTestPaper_id() {
		return testPaper_id;
	}

	public void setTestPaper_id(Long testPaper_id) {
		this.testPaper_id = testPaper_id;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_Result() {
		return question_Result;
	}

	public void setQuestion_Result(String question_Result) {
		this.question_Result = question_Result;
	}

	public int getIsNot_Right() {
		return isNot_Right;
	}

	public void setIsNot_Right(int isNot_Right) {
		this.isNot_Right = isNot_Right;
	}

	public Integer getQuestionLabel_id() {
		return questionLabel_id;
	}

	public void setQuestionLabel_id(Integer integer) {
		this.questionLabel_id = integer;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer integer) {
		this.seq = integer;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getSelect_result() {
		return select_result;
	}

	public void setSelect_result(String select_result) {
		this.select_result = select_result;
	}

	public String getRight_count() {
		return right_count;
	}

	public void setRight_count(String right_count) {
		this.right_count = right_count;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
