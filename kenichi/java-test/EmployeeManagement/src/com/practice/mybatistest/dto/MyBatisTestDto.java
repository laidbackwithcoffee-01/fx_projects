package com.practice.mybatistest.dto;

public class MyBatisTestDto {

	private int id;
	private String colTest1;
	private String colTest2;
	private String colTest3;

	// コンストラクタ
	public MyBatisTestDto() {
	}

	public MyBatisTestDto(int id, String colTest1, String colTest2, String colTest3) {
		this.id = id;
		this.colTest1 = colTest1;
		this.colTest2 = colTest2;
		this.colTest3 = colTest3;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColTest1() {
		return this.colTest1;
	}

	public void setColTest1(String colTest1) {
		this.colTest1 = colTest1;
	}

	public String getColTest2() {
		return this.colTest2;
	}

	public void setColTest2(String colTest2) {
		this.colTest2 = colTest2;
	}

	public String getColTest3() {
		return this.colTest3;
	}

	public void setColTest3(String colTest3) {
		this.colTest3 = colTest3;
	}
}
