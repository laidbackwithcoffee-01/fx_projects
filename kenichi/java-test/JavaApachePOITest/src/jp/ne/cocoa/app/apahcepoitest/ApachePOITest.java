package jp.ne.cocoa.app.apahcepoitest;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ApachePOITest {

	public static void main(String[] args) throws IOException {

		String fileName = "test.xlsx";
		Workbook book = null;
		FileOutputStream fout = null;

		try {
			book = new SXSSFWorkbook();

			Font font = book.createFont();
			font.setFontName("Meiryo UI");
			font.setFontHeightInPoints((short) 10);

			DataFormat format = book.createDataFormat();

			Sheet sheet = null;
			try {
				sheet = book.createSheet();

				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0);
				cell.setCellValue("asdf");

			} finally {
				if (sheet != null) {
					sheet = null;
				}
			}

			// ファイル出力
			fout = new FileOutputStream(fileName);
			book.write(fout);
		} finally {
			if (fout != null) {
				fout.close();
				fout = null;
			}
			if (book != null) {
				book.close();
				book = null;
			}
		}
	}
}
