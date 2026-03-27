package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for reading test data from Excel (.xlsx) using Apache POI.
 *
 * Excel file : src/test/resources/data/testdata.xlsx
 *
 * PaymentData sheet columns (note: Expiry is split into two separate columns):
 *   TestCaseID | CardHolderName | CardNumber | ExpiryMonth | ExpiryYear | CVV | ExpectedError
 *
 * Step definitions retrieve month and year independently:
 *   String month = ExcelUtil.getPaymentData("TC_AP_09_02").get("ExpiryMonth");  // "12"
 *   String year  = ExcelUtil.getPaymentData("TC_AP_09_02").get("ExpiryYear");   // "28"
 */
public class ExcelUtil {

    // ── Path to workbook ──────────────────────────────────────────────────────
    private static final String EXCEL_PATH =
            System.getProperty("user.dir") + "/src/test/resources/data/testdata.xlsx";

    // ── Sheet name constants ──────────────────────────────────────────────────
    public static final String SHEET_LOGIN   = "LoginData";
    public static final String SHEET_SEARCH  = "SearchData";
    public static final String SHEET_CART    = "CartData";
    public static final String SHEET_ADDRESS = "AddressData";
    public static final String SHEET_COUPON  = "CouponData";
    public static final String SHEET_PAYMENT = "PaymentData";

    // ─────────────────────────────────────────────────────────────────────────
    // Private helpers
    // ─────────────────────────────────────────────────────────────────────────

    private static Sheet getSheet(Workbook wb, String sheetName) {
        Sheet sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException(
                    "Sheet '" + sheetName + "' not found in " + EXCEL_PATH);
        }
        return sheet;
    }

    private static Map<String, Integer> buildHeaderMap(Row headerRow) {
        Map<String, Integer> map = new HashMap<>();
        for (Cell cell : headerRow) {
            map.put(cell.getStringCellValue().trim().toLowerCase(),
                    cell.getColumnIndex());
        }
        return map;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Core public API
    // ─────────────────────────────────────────────────────────────────────────

    public static int getRowCount(String sheetName) {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {
            return getSheet(wb, sheetName).getLastRowNum();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel: " + EXCEL_PATH, e);
        }
    }

    public static String getData(String sheetName, int rowIndex, String columnName) {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet  = getSheet(wb, sheetName);
            Row   header = sheet.getRow(0);
            if (header == null)
                throw new RuntimeException("Header row missing in sheet: " + sheetName);

            Map<String, Integer> colMap = buildHeaderMap(header);
            Integer colIdx = colMap.get(columnName.trim().toLowerCase());
            if (colIdx == null)
                throw new IllegalArgumentException(
                        "Column '" + columnName + "' not found in sheet '" + sheetName + "'");

            Row dataRow = sheet.getRow(rowIndex);
            if (dataRow == null) return "";
            return getCellValue(dataRow.getCell(colIdx));

        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel: " + EXCEL_PATH, e);
        }
    }

    public static Map<String, String> getRowData(String sheetName, int rowIndex) {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet   = getSheet(wb, sheetName);
            Row   header  = sheet.getRow(0);
            Row   dataRow = sheet.getRow(rowIndex);

            Map<String, String> result = new HashMap<>();
            if (header == null || dataRow == null) return result;

            for (Cell hCell : header) {
                String key  = hCell.getStringCellValue().trim();
                Cell   dCell = dataRow.getCell(hCell.getColumnIndex());
                result.put(key, getCellValue(dCell));
            }
            return result;

        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel: " + EXCEL_PATH, e);
        }
    }

    public static Map<String, String> getDataByTestCaseId(String sheetName, String testCaseId) {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet  = getSheet(wb, sheetName);
            Row   header = sheet.getRow(0);
            if (header == null) return new HashMap<>();

            Map<String, Integer> colMap   = buildHeaderMap(header);
            Integer              idColIdx = colMap.get("testcaseid");
            if (idColIdx == null)
                throw new RuntimeException("'TestCaseID' column not found in sheet: " + sheetName);

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                if (testCaseId.equalsIgnoreCase(getCellValue(row.getCell(idColIdx)))) {
                    Map<String, String> result = new HashMap<>();
                    for (Cell hCell : header) {
                        String key  = hCell.getStringCellValue().trim();
                        Cell   dCell = row.getCell(hCell.getColumnIndex());
                        result.put(key, getCellValue(dCell));
                    }
                    return result;
                }
            }
            return new HashMap<>();

        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel: " + EXCEL_PATH, e);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Convenience wrappers
    // ─────────────────────────────────────────────────────────────────────────

    /** LoginData sheet – row 1 (TC_LOGIN_01). */
    public static Map<String, String> getLoginData() {
        return getRowData(SHEET_LOGIN, 1);
    }

    public static Map<String, String> getSearchData(String testCaseId) {
        return getDataByTestCaseId(SHEET_SEARCH, testCaseId);
    }

    public static Map<String, String> getCartData(String testCaseId) {
        return getDataByTestCaseId(SHEET_CART, testCaseId);
    }

    public static Map<String, String> getAddressData(String testCaseId) {
        return getDataByTestCaseId(SHEET_ADDRESS, testCaseId);
    }

    public static Map<String, String> getCouponData(String testCaseId) {
        return getDataByTestCaseId(SHEET_COUPON, testCaseId);
    }

    /**
     * Returns PaymentData row as a Map. Keys:
     *
     *   "TestCaseID"     → "TC_AP_09_02"
     *   "CardHolderName" → "John Doe"
     *   "CardNumber"     → "4111111111111111"
     *   "ExpiryMonth"    → "12"   ← month stored separately
     *   "ExpiryYear"     → "28"   ← year stored separately
     *   "CVV"            → "123"
     *   "ExpectedError"  → "Payment failed…"
     *
     * Usage in step definitions:
     *   Map<String,String> data = ExcelUtil.getPaymentData("TC_AP_09_02");
     *   circleMemPaymentPage.enterExpiryMonth(data.get("ExpiryMonth"));
     *   circleMemPaymentPage.enterExpiryYear(data.get("ExpiryYear"));
     */
    public static Map<String, String> getPaymentData(String testCaseId) {
        return getDataByTestCaseId(SHEET_PAYMENT, testCaseId);
    }
}