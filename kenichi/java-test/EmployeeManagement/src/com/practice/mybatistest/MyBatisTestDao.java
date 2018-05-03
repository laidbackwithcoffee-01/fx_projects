package com.practice.mybatistest;

import java.util.List;

import com.practice.util.AbstractDao;

public class MyBatisTestDao<MyBatisDto> extends AbstractDao<MyBatisDto> {

	private static final String STATEMENT_FIND = "com.practice.mybatistest.dto.MyBatisTestDto.find";
	private static final String STATEMENT_SELECT_MAX = "com.practice.mybatistest.dto.MyBatisTestDto.select_max";
	private static final String STATEMENT_INSERT = "com.practice.mybatistest.dto.MyBatisTestDto.insert";
	private static final String STATEMENT_UPDATE = "com.practice.mybatistest.dto.MyBatisTestDto.update";
	private static final String STATEMENT_DELETE = "com.practice.mybatistest.dto.MyBatisTestDto.delete";

	@Override
	public List<MyBatisDto> find(MyBatisDto dto) {
		return sqlSession.selectList(STATEMENT_FIND, dto);
	}

	@Override
	public List<MyBatisDto> select(MyBatisDto dto) {
		return sqlSession.selectList(STATEMENT_SELECT_MAX, null);
	}

	@Override
	public void insert(MyBatisDto dto) {
		try {
			sqlSession.insert(STATEMENT_INSERT, dto);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}

	@Override
	public void delete(MyBatisDto dto) {
		try {
			sqlSession.insert(STATEMENT_DELETE, dto);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}

	@Override
	public void update(MyBatisDto dto) {
		try {
			sqlSession.insert(STATEMENT_UPDATE, dto);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}
}
