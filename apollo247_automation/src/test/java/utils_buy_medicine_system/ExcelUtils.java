package utils_buy_medicine_system;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    // Read data from Excel sheet based on row and column index
    public static String getData(int row, int col) {

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/testdata/data.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("Sheet1");

            // Fetch data before closing workbook
            String data = sheet.getRow(row).getCell(col).getStringCellValue();

            // Close resources to avoid memory leak
            wb.close();
            fis.close();

            return data;

        } catch (Exception e) {
            return null;
        }
    }
}