package com.practice.geneticstypetest;

import com.practice.mybatistest.dto.MyBatisTestDto;

public class GenericsTypeTestMain {

	public static void main(String[] args) {

		TestClass<Integer> test1 = new TestClass<Integer>(); // 型引数にIntegerを使用
		Integer d1 = new Integer(1);
		test1.setData(d1);
		System.out.println("test 1 : " + test1.getData().getClass().getName()); // Getterで取得したデータの型はIntegerとなる

		TestClass<String> test2 = new TestClass<String>();
		String d2 = "d2";
		test2.setData(d2);
		System.out.println("test 2 : " + test2.getData().getClass().getName());

		TestClass<Boolean> test3 = new TestClass<Boolean>();
		Boolean d3 = new Boolean(true);
		test3.setData(d3);
		System.out.println("test 3 : " + test3.getData().getClass().getName());

		TestClass<MyBatisTestDto> test4 = new TestClass<MyBatisTestDto>();
		MyBatisTestDto d4 = new MyBatisTestDto(1, "test1", "test2", "test3");
		test4.setData(d4);
		System.out.println("test 4 : " + test4.getData().getClass().getName());
	}

}
