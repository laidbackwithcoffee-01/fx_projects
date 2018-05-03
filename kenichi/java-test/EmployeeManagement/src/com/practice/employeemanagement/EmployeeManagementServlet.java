package com.practice.employeemanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.Const;
import com.practice.employeemanagement.dto.EmployeeManagementDto;

/**
 * Servlet implementation class EmployeeManagementServlet
 */
@WebServlet(name="EmployeeManagementServlet", urlPatterns= {"/jsp/EmployeeManagement"})
public class EmployeeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字化け防止
		request.setCharacterEncoding("UTF-8");

		// モード取得 取得不可の場合は強制的に選択モードにする
		String mode = request.getParameter(Const.ID_EMPLOYEEMANAGEMENT_MODE);
		if (mode == null || mode.isEmpty()) {
			mode = Const.MODE_SELECT;
		}

		String employeeNo = Const.EMPTY_STRING;
		String name = Const.EMPTY_STRING;
		String msg = Const.EMPTY_STRING;
		List<EmployeeManagementDto> data = new ArrayList<EmployeeManagementDto>();

		// リクエストで取得したパラメータをDTOに変換
		EmployeeManagementDto dto = convToDto(request);
		RequestDispatcher dispatcher = null;

		switch(mode) {
			case Const.MODE_UPDATE:
				if (Const.MODE_UPDATE.equals(mode)) {
					try {
						EmployeeManagementDao<EmployeeManagementDto> dao = new EmployeeManagementDao<EmployeeManagementDto>();
						dao.update(dto);

						msg = Const.MSG_DB_UPDATE_SCCESS;
					} catch (Exception e) {
						msg = e.getMessage();
					}

					clearParameter(dto);
				}

				// breakなし、SELECTも実行する

			case Const.MODE_DELETE:
				if (Const.MODE_DELETE.equals(mode)) {
					try {
						EmployeeManagementDao<EmployeeManagementDto> dao = new EmployeeManagementDao<EmployeeManagementDto>();
						dao.delete(dto);

						msg = Const.MSG_DB_DELETE_SUCCESS;
					} catch (Exception e) {
						msg = e.getMessage();
					}

					clearParameter(dto);
				}

				// breakなし、SELECTも実行する

			case Const.MODE_SELECT:

				// 条件用のパラメータを取得
				employeeNo = dto.getConditionEmployeeNo();
				name = dto.getConditionName();
				// 条件用テキストボックスに設定するためJSPへ渡す
				request.setAttribute(Const.ID_CONDITION_EMPLOYEEMANAGEMENT_EMPLOYEE_NO, employeeNo == null ? Const.EMPTY_STRING : employeeNo);
				request.setAttribute(Const.ID_CONDITION_EMPLOYEEMANAGEMENT_NAME, name == null ? Const.EMPTY_STRING : name);

				try {
					EmployeeManagementDao<EmployeeManagementDto> dao = new EmployeeManagementDao<EmployeeManagementDto>();
					data = dao.find(dto);

					if (Const.MODE_SELECT.equals(mode)) {
						msg = Const.MSG_DB_SELECT_SUCCESS;
					}
				} catch (Exception e) {
					msg = e.getMessage();
				}

                request.setAttribute("msg", msg);
                request.setAttribute("data", data);

                dispatcher = request.getRequestDispatcher("/jsp/EmployeeManagementSelect.jsp");
                dispatcher.forward(request, response);

				break;

			case Const.MODE_INSERT:
				if (dto.getEmployeeNo() == null) {
					// 新規追加画面へ遷移
	                dispatcher = request.getRequestDispatcher("/jsp/EmployeeManagementInsert.jsp");
	                dispatcher.forward(request, response);
				} else {
					// 新規追加実施
					try {
						EmployeeManagementDao<EmployeeManagementDto> dao = new EmployeeManagementDao<EmployeeManagementDto>();

						if(dao.select(dto).size() > 0) {
							// 既に登録されている社員Noの場合は新規登録しない
							msg = Const.MSG_DB_INSERT_DUPLICATION;
						} else {
							// 新規登録
							dao.insert(dto);
							msg = Const.MSG_DB_INSERT_SUCCESS;
						}

						// 条件ない状態で選択処理
						data = dao.find(new EmployeeManagementDto());

					} catch (Exception e) {
						msg = e.getMessage();
					}

					// 条件テキストボックスを空にする
					request.setAttribute(Const.ID_CONDITION_EMPLOYEEMANAGEMENT_EMPLOYEE_NO, Const.EMPTY_STRING);
					request.setAttribute(Const.ID_CONDITION_EMPLOYEEMANAGEMENT_NAME, Const.EMPTY_STRING);

	                request.setAttribute("msg", msg);
	                request.setAttribute("data", data);

					// 検索画面へ遷移
	                dispatcher = request.getRequestDispatcher("/jsp/EmployeeManagementSelect.jsp");
	                dispatcher.forward(request, response);
				}

				break;
		}
	}

	private EmployeeManagementDto convToDto(HttpServletRequest request) {
		EmployeeManagementDto ret = new EmployeeManagementDto();
		ret.setEmployeeNo(request.getParameter(Const.ID_EMPLOYEEMANAGEMENT_EMPLOYEE_NO));
		ret.setName(request.getParameter(Const.ID_EMPLOYEEMANAGEMENT_NAME));
		ret.setConditionEmployeeNo(request.getParameter(Const.ID_CONDITION_EMPLOYEEMANAGEMENT_EMPLOYEE_NO));
		ret.setConditionName(request.getParameter(Const.ID_CONDITION_EMPLOYEEMANAGEMENT_NAME));
		return ret;
	}

	private void clearParameter(EmployeeManagementDto dto) {
		dto.setEmployeeNo(Const.EMPTY_STRING);
		dto.setName(Const.EMPTY_STRING);
	}
}
