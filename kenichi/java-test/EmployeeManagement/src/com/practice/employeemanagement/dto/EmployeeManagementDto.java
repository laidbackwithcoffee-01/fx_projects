package com.practice.employeemanagement.dto;

public class EmployeeManagementDto {

	private String employeeNo;
	private String name;

	// 検索用
	private String conditionEmployeeNo;
	private String conditionName;

	public EmployeeManagementDto() {
	}

	public EmployeeManagementDto(String employeeNo, String name, String conditionEmployeeNo, String conditionName) {
		this.employeeNo = employeeNo;
		this.name = name;
		this.conditionEmployeeNo = conditionEmployeeNo;
		this.conditionName = conditionName;
	}

	/**
	 * getter 社員No
	 * @return
	 */
	public String getEmployeeNo() {
		return this.employeeNo;
	}

	/**
	 * setter 社員No
	 * @param employeeNo
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/**
	 * getter 氏名
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter 氏名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter 社員No
	 * @return
	 */
	public String getConditionEmployeeNo() {
		return this.conditionEmployeeNo;
	}

	/**
	 * setter 社員No
	 * @param employeeNo
	 */
	public void setConditionEmployeeNo(String conditionEmployeeNo) {
		this.conditionEmployeeNo = conditionEmployeeNo;
	}

	/**
	 * getter 氏名
	 * @return
	 */
	public String getConditionName() {
		return this.conditionName;
	}

	/**
	 * setter 氏名
	 * @param name
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
}
