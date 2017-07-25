package org.agaphetos.api.tms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tms_db.task")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "taskId")
public class Task implements Serializable {

	private static final long serialVersionUID = -3757566133604706246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id", nullable = false)
	private int taskId;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "create_date")
	private Date createdDate;
	
	@Column(name = "completed_date")
	private Date completedDate;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "external_request_id")
	private int externalRequestId;
	
	@Column(name = "remarks")
	private String remarks;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private TaskStatus status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "application_type_id")
	private ApplicationType applicationType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_type_id")
	private TaskType taskType;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="task", orphanRemoval=true)
	private TaskAddtlDetail taskAddtlDetail;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createDate) {
		this.createdDate = createDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getExternalRequestId() {
		return externalRequestId;
	}

	public void setExternalRequestId(int externalRequestId) {
		this.externalRequestId = externalRequestId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	
	public TaskAddtlDetail getTaskAddtlDetail() {
		return taskAddtlDetail;
	}

	public void setTaskAddtlDetail(TaskAddtlDetail taskAddtlDetail) {
		this.taskAddtlDetail = taskAddtlDetail;
		if (taskAddtlDetail == null) {
			System.out.println("taskAddtlDetail is null");
		} else {
			taskAddtlDetail.setTask(this);
		}
	}
}
