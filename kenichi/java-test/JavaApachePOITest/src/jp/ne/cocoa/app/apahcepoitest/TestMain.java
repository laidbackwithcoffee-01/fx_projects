package jp.ne.cocoa.app.apahcepoitest;

import jp.ne.cocoa.util.poiwrapper.PoiAdapter;

public class TestMain {

	public static void main(String[] args) throws Exception {

		try (PoiAdapter poi = new PoiAdapter()) {
			String filePath = "aiueo.xlsx";
			if (poi.setWorkbook(filePath)) {

				poi.setCells(0, 0, "asdfasdfasdf", PoiAdapter.Type.STRING);

				poi.save();
			} else {
				System.out.println("asdf");
			}
		}
	}
}
