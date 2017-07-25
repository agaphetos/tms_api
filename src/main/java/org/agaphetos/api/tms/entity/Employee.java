package org.agaphetos.api.tms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user_db.employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1467568624568042578L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false)
	private int employeeId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "status")
	private int status;	

	@Column(name = "created_dt")
	private Date createdDate;

	@Column(name = "modified_dt")
	private Date modifiedDate;
		
	@Column(name = "supervisor_id", nullable = true)
	private Integer supervisorId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private UserRole userRole;
	
	@Column(name = "first_log")
	private int firstLog;

	@Transient
	private String fullName;
	
	@OneToMany(mappedBy = "employee")
	private List<SecurityAnswer> securityAnswers;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
		if (supervisorId == null) {
			this.supervisorId = null;
		} else {
			this.supervisorId = supervisorId;
		}
	}
	
	public int getFirstLog() {
		return firstLog;
	}

	public void setFirstLog(int firstLog) {
		this.firstLog = firstLog;
	}

	public String getFullName() {
		return fullName = lastName + ", " + firstName + " " + middleName;
	}

	public void setFullName() {
		this.fullName = lastName + ", " + firstName + " " + middleName;
	}
	
	public List<SecurityAnswer> getSecurityAnswers() {
		return securityAnswers;
	}

	public void setSecurityAnswers(List<SecurityAnswer> securityAnswers) {
		this.securityAnswers = securityAnswers;
	}
	
	public void addSecurityAnswer(SecurityAnswer securityAnswer) {
		this.securityAnswers.add(securityAnswer);
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", emailAddress=" + emailAddress + ", password=" + password 
				+ ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", supervisorId=" + supervisorId + ", userRole=" + userRole.toString() + 
				", fullName=" + getFullName() + "]";
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Employee)) {
            return false;
        }

        Employee employee = (Employee) o;

        return employee.employeeId == employeeId;
	}
}
