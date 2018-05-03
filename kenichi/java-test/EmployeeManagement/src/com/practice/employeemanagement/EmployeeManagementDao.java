package com.practice.employeemanagement;

import java.util.List;

import com.practice.util.AbstractDao;
public class EmployeeManagementDao <T> extends AbstractDao <T> {

	private static final String STATEMENT_FIND = "com.practice.employeemanagement.dto.EmployeeManagementDto.find";
	private static final String STATEMENT_SELECT = "com.practice.employeemanagement.dto.EmployeeManagementDto.select";
	private static final String STATEMENT_INSERT = "com.practice.employeemanagement.dto.EmployeeManagementDto.insert";
	private static final String STATEMENT_UPDATE = "com.practice.employeemanagement.dto.EmployeeManagementDto.update";
	private static final String STATEMENT_DELETE = "com.practice.employeemanagement.dto.EmployeeManagementDto.delete";

	public EmployeeManagementDao() {
		super();
	}

	@Override
	public List<T> find(T param) {
		return sqlSession.selectList(STATEMENT_FIND, param);
	}

	@Override
	public List<T> select(T dto) {
		return sqlSession.selectList(STATEMENT_SELECT, dto);
	}

	@Override
	public void insert(T dto) {
		try {
			sqlSession.insert(STATEMENT_INSERT, dto);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}

	@Override
	public void delete(T dto) {
		try {
			sqlSession.delete(STATEMENT_DELETE, dto);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}

	@Override
	public void update(T dto) {
		try {
			sqlSession.update(STATEMENT_UPDATE, dto);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}
	}
}
