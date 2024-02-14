package com.example.MicroServicesExample.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJECTS_INFO")
public class ProjectEntity {
	
	@Id
	@Column(name = "EMPLOYEE_ID")
	private int empId;
	
	@Column(name = "PROJECT_CODE")
	private int projectCode;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;

	public ProjectEntity(int empId, int projectCode, String projectName) {
		super();
		this.empId = empId;
		this.projectCode = projectCode;
		this.projectName = projectName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "ProjectEntity [empId=" + empId + ", projectCode=" + projectCode + ", projectName=" + projectName + "]";
	}

	public ProjectEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
