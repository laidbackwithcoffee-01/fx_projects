package jp.ne.cocoa.util.poiwrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class PoiAdapter implements AutoCloseable {

	public static enum Type {
		BOOLEAN, CALENDAR, DATE, DOUBLE, RICHTEXTSTRING, STRING
	}

	private static final String ERR_NOT_EXISTS_TYPE = "設定属性が誤っています。";
	private static final String EXTENSION_OLD = ".xls";
	private static final String EXTENSION_NEW = ".xlsx";

	private Workbook currentWorkbook = null;
	private Sheet currentSheet = null;
	private Integer currentSheetIndex = null;

	public int getCurrentSheetIndex() {
		return this.currentSheetIndex;
	}

	private String errString = null;

	public String getErrString() {
		return this.errString;
	}

	private String filePath = null;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean setWorkbook(String filePath) {

		this.filePath = filePath;
		return setWorkbook();
	}

	public void selectSheet(int Index) {

		currentSheet = currentWorkbook.getSheetAt(Index);
		currentSheetIndex = Index;
	}

	public boolean setWorkbook() {

		boolean ret = true;

		File file = new File(filePath);
		try {
			if (file.exists()) {
				// 更新
				currentWorkbook = WorkbookFactory.create(file);
			} else {
				// 新規
				String extension = filePath.substring(filePath.length() - EXTENSION_OLD.length() - 1,
						filePath.length());
				if (EXTENSION_OLD.equals(extension)) {
					currentWorkbook = new HSSFWorkbook();
				} else {
					currentWorkbook = new SXSSFWorkbook();
				}

				currentWorkbook.createSheet();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
			errString = e.getMessage();
		} finally {
			file = null;
		}

		selectSheet(0);

		return ret;
	}

	public boolean setCells(int rowIndex, int colIndex, Object value, Type type) {

		boolean ret = true;

		try {
			Row row = currentSheet.createRow(rowIndex);
			Cell cell = row.createCell(colIndex);

			switch (type) {
			case BOOLEAN:
				cell.setCellValue((Boolean) value);
				break;
			case CALENDAR:
				cell.setCellValue((Calendar) value);
				break;
			case DATE:
				cell.setCellValue((Date) value);
				break;
			case DOUBLE:
				cell.setCellValue((Double) value);
				break;
			case RICHTEXTSTRING:
				cell.setCellValue((RichTextString) value);
				break;
			case STRING:
				cell.setCellValue((String) value);
				break;
			default:
				ret = false;
				errString = ERR_NOT_EXISTS_TYPE;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
			errString = e.getMessage();
		}

		return ret;
	}

	public boolean save() {

		boolean ret = true;

		FileOutputStream fout = null;

		try {
			fout = new FileOutputStream(filePath);
			currentWorkbook.write(fout);
		} catch (Exception e) {
			ret = false;
			errString = e.getMessage();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
				}
				fout = null;
			}
		}

		return ret;
	}

	@Override
	public void close() throws Exception {
		if (currentSheet != null) {
			currentSheet = null;
		}
		if (currentWorkbook != null) {
			currentWorkbook.close();
			currentWorkbook = null;
		}
	}
}
