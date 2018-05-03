package com.practice.mybatistest;

import java.util.List;

import com.practice.mybatistest.dto.MyBatisTestDto;

public class MyBatisTestDeleteMain {

	public static void main(String[] args) {

		MyBatisTestDao<MyBatisTestDto> dao = new MyBatisTestDao<MyBatisTestDto>();

		List<MyBatisTestDto> list = dao.select(null);
		if (list.get(0) == null) {
			System.out.println("未登録です");
		} else {
			int id = list.get(0).getId();
			MyBatisTestDto dto = new MyBatisTestDto();
			dto.setId(id);
			dao.delete(dto);

			System.out.println("id : " + id + " を削除しました");
		}
	}
}
