package com.practice.util;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class AbstractDao <T> {

	protected static SqlSession sqlSession = null;

	static {
		Reader reader = null;

		if (sqlSession == null) {
			try {
				reader = Resources.getResourceAsReader("mybatis-config.xml");
			} catch (IOException e) {
				e.printStackTrace();
			}

			sqlSession = new SqlSessionFactoryBuilder().build(reader).openSession();
		}
	}

	// 検索用
	public abstract List<T> find(T dto);

	// 選択用
	public abstract List<T> select(T dto);

	// 新規追加用
	public abstract void insert(T dto);

	// 削除用
	public abstract void delete(T dto);

	// 更新用
	public abstract void update(T dto);

}
