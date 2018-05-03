package com.practice.mybatistest;

import java.util.List;

import com.practice.mybatistest.dto.MyBatisTestDto;

public class MyBatisTestUpdateMain {

	public static void main(String[] args) {

		MyBatisTestDao<MyBatisTestDto> dao = new MyBatisTestDao<MyBatisTestDto>();

		List<MyBatisTestDto> list = dao.select(null);
		if (list.get(0) == null) {
			System.out.println("未登録です");
		} else {
			int id = list.get(0).getId();
			MyBatisTestDto dto = new MyBatisTestDto();
			dto.setId(id);
			dto.setColTest1("update1_" + id);
			dto.setColTest2("update2_" + id);
			dto.setColTest3("update3_" + id);
			dao.update(dto);

			System.out.println("id : " + id + " を更新しました");
		}
	}

}
