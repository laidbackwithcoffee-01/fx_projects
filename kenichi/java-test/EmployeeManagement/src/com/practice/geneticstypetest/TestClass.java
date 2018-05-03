package com.practice.geneticstypetest;

// ジェネリクス（総称型）
public class TestClass<T> {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
