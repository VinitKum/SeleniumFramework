package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public static Object[][] getData() throws IOException {

        FileInputStream fis = new FileInputStream("TestData/LoginData.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {

            Row row = sheet.getRow(i);

            for (int j = 0; j < colCount; j++) {

                Cell cell = row.getCell(j);

                data[i - 1][j] = cell.toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}