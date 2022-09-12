package assessment.utilsForUser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getExcelData {
	public static ArrayList<Object> getDetails(String fileName,String SheetName,String TestCaseLiteral,String UserData) throws IOException {
		FileInputStream file = new FileInputStream(fileName);

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<Object> testData = new ArrayList<Object>();
		for (int i = 0; i < sheetCount; i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			if (sheet.getSheetName().equalsIgnoreCase(SheetName)) {
				//System.out.println("Sheet Found");
				Iterator<Row> rows = sheet.rowIterator();

				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				int cellNum = 0;
				int cellIndex = 0;

				while (cell.hasNext()) {
					Cell cellValue = cell.next();

					if (cellValue.getStringCellValue().equalsIgnoreCase(TestCaseLiteral)) {

						cellIndex = cellNum;

					}
					cellNum++;
				}

				while (rows.hasNext()) {
					Row rowValue = rows.next();
					if (rowValue.getCell(cellIndex).getStringCellValue().equalsIgnoreCase(UserData)) {
						// System.out.println("Row Found");
						Iterator<Cell> cellvalue = rowValue.cellIterator();
						cellvalue.next();
						while (cellvalue.hasNext()) {
							Cell cellInfo = cellvalue.next();
							if (cellInfo.getCellType() == CellType.STRING) {
								
								testData.add(cellInfo.getStringCellValue());
							} else if (cellInfo.getCellType() == CellType.NUMERIC) {
								testData.add(cellInfo.getNumericCellValue());
							} else if (cellInfo.getCellType() == CellType.BOOLEAN) {
								testData.add(cellInfo.getBooleanCellValue());

							}
						}

					}

				}

			}

		}
		return testData;

	}

}
