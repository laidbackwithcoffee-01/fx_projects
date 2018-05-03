package com.practice.mybatistest;

import java.util.List;

import com.practice.mybatistest.dto.MyBatisTestDto;

public class MyBatisTestInsertMain {

	public static void main(String[] args) {

		MyBatisTestDao<MyBatisTestDto> dao = new MyBatisTestDao<MyBatisTestDto>();

		// IDの最大値を取得
		List<MyBatisTestDto> list = dao.select(null);

		int id = 1;
		if (list.get(0) != null) {
			id = list.get(0).getId();
			id++;
		}

		// 新規行を追加
		MyBatisTestDto dto = new MyBatisTestDto();
		dto.setId(id);
		dto.setColTest1("test1_" + id);
		dto.setColTest2("test2_" + id);
		dto.setColTest3("test3_" + id);
		dao.insert(dto);

		System.out.println("id : " + id + " を登録しました");
	}

}
