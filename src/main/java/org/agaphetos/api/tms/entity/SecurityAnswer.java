package org.agaphetos.api.tms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_db.security_answer")
public class SecurityAnswer implements Serializable {

	private static final long serialVersionUID = -1652857864618564423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "security_answer_id", nullable = false)
	private int securityAnswerId;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private SecurityQuestion securityQuestion;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column
	private String answer;
	
	@Column(name = "question_order")
	private int questionOrder;

	public int getSecurityAnswerId() {
		return securityAnswerId;
	}

	public void setSecurityAnswerId(int securityAnswerId) {
		this.securityAnswerId = securityAnswerId;
	}

	public SecurityQuestion getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
	}	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}
	
	@Override
	public String toString() {
		return "SecurityAnswer [securityAnswerId=" + securityAnswerId + ", securityQuestion=" + securityQuestion.toString() 
				+ ", employeeId=" + employee.getEmployeeId() + ", answer=" + answer + ", questionOrder=" + questionOrder + "]";
	}
}
