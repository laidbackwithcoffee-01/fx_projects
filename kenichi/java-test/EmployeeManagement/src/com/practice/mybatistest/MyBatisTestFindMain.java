package com.practice.mybatistest;

import java.util.List;

import com.practice.mybatistest.dto.MyBatisTestDto;

public class MyBatisTestFindMain {

	public static void main(String[] args) {

		MyBatisTestDao<MyBatisTestDto> dao = new MyBatisTestDao<MyBatisTestDto>();
		MyBatisTestDto dto = new MyBatisTestDto();
		dto.setColTest1(null);
		dto.setColTest2(null);
		dto.setColTest3(null);

		List<MyBatisTestDto> recs = dao.find(dto);
		for (MyBatisTestDto rec : recs) {
			System.out.print("id : " + rec.getId() + ", ");
			System.out.print("col_test_1 : " + rec.getColTest1() + ", ");
			System.out.print("col_test_2 : " + rec.getColTest2() + ", ");
			System.out.print("col_test_3 : " + rec.getColTest3());
			System.out.println();
		}
	}

}
