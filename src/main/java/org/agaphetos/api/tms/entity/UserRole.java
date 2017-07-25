package org.agaphetos.api.tms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_db.user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -5477097905940946678L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;

	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "is_supervisor")
	private int isSupervisor;

	@Column
	private int status;
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getIsSupervisor() {
		return isSupervisor;
	}

	public void setIsSupervisor(int isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", description=" + description + ", status=" + status + "]";
	}
}
