package org.agaphetos.api.tms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_db.security_question")
public class SecurityQuestion implements Serializable {

	private static final long serialVersionUID = -5645904411787138733L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id", nullable = false)
	private int questionId;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "SecurityQuestion [questionId=" + questionId + ", description=" + description + "]";
	}
}
