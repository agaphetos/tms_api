package org.agaphetos.api.tms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tms_db.application_type")
public class ApplicationType implements Serializable {

	private static final long serialVersionUID = 4710310701167428932L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_type_id", nullable = false)
	private int applicationTypeId;

	@Column(name = "application_name", nullable = false)
	private String applicationName;

	public int getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(int applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
