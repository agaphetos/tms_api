package org.agaphetos.api.tms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "tms_db.task_addtl_detail")
public class TaskAddtlDetail implements Serializable {

	private static final long serialVersionUID = 2123625480830625068L;

	@Id
	@Column(name="task_id")
	private int taskId;
	
	@OneToOne
    @JoinColumn(name = "task_id")
    @MapsId
	private Task task;
	
	@Column(name = "planned_start_date", nullable = true)
	private Date plannedStartDate;
	
	@Column(name = "planned_end_date", nullable = true)
	private Date plannedEndDate;
	
	@Column(name = "assessment_method")
	private String assessmentMethod;
	
	@Column(name = "assessment_result")
	private String assessmentResult;
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public Date getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	public String getAssessmentMethod() {
		return assessmentMethod;
	}

	public void setAssessmentMethod(String assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}

	public String getAssessmentResult() {
		return assessmentResult;
	}

	public void setAssessmentResult(String assessmentResult) {
		this.assessmentResult = assessmentResult;
	}	
}
