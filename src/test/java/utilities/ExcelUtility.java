package utilities;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

public class ExcelUtility {
/**
	 * Collects the result of every test and writes them to a new Excel file:
	 *   <project>/Reports/TestStatus_yyyyMMdd_HHmmss.xlsx
	 *
	 * Usage from the listener:
	 *   ExcelStatusUtility.record(result, "PASS" | "FAIL" | "SKIP");   // in each onTest* method
	 *   ExcelStatusUtility.generate();                                  // in onFinish
	 */
	

	    private static class Entry {
	        final String className, testName, status, reason;
	        final long durationMs;
	        final String time;

	        Entry(String className, String testName, String status, long durationMs, String reason, String time) {
	            this.className = className;
	            this.testName = testName;
	            this.status = status;
	            this.durationMs = durationMs;
	            this.reason = reason;
	            this.time = time;
	        }
	    }

	    private static final List<Entry> ENTRIES = new CopyOnWriteArrayList<Entry>();

	    // fixed per run, so onFinish being called once per <test> rewrites the SAME file
	    private static final String RUN_STAMP = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

	    public static void record(ITestResult r, String status) {
	        String reason = "";
	        if (r.getThrowable() != null) {
	            reason = String.valueOf(r.getThrowable().getMessage()).replace("\n", " ");
	            if (reason.length() > 500) {
	                reason = reason.substring(0, 500) + "...";
	            }
	        }
	        ENTRIES.add(new Entry(
	                r.getTestClass().getName(),
	                r.getName(),
	                status,
	                r.getEndMillis() - r.getStartMillis(),
	                reason,
	                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(r.getEndMillis()))));
	    }

	    public static synchronized String generate() {
	        File dir = new File(System.getProperty("user.dir"), "Result");
	        dir.mkdirs();
	        File file = new File(dir, "TestStatus_" + RUN_STAMP + ".xlsx");

	        try (Workbook wb = new XSSFWorkbook()) {
	            CellStyle header = style(wb, IndexedColors.GREY_25_PERCENT, true);
	            CellStyle plain = style(wb, null, false);
	            CellStyle pass = style(wb, IndexedColors.LIGHT_GREEN, true);
	            CellStyle fail = style(wb, IndexedColors.ROSE, true);
	            CellStyle skip = style(wb, IndexedColors.LIGHT_YELLOW, true);

	            // ---- Sheet 1: per-test status ----
	            Sheet sheet = wb.createSheet("Test Status");
	            String[] cols = { "S.No", "Class", "Test Name", "Status", "Duration (ms)", "Executed At", "Reason" };
	            Row h = sheet.createRow(0);
	            for (int i = 0; i < cols.length; i++) {
	                set(h, i, cols[i], header);
	            }

	            int rowNum = 1;
	            int p = 0, f = 0, s = 0;
	            for (Entry e : ENTRIES) {
	                Row row = sheet.createRow(rowNum);
	                set(row, 0, String.valueOf(rowNum), plain);
	                set(row, 1, e.className, plain);
	                set(row, 2, e.testName, plain);
	                CellStyle st = plain;
	                if ("PASS".equals(e.status)) { st = pass; p++; }
	                else if ("FAIL".equals(e.status)) { st = fail; f++; }
	                else if ("SKIP".equals(e.status)) { st = skip; s++; }
	                set(row, 3, e.status, st);
	                set(row, 4, String.valueOf(e.durationMs), plain);
	                set(row, 5, e.time, plain);
	                set(row, 6, e.reason, plain);
	                rowNum++;
	            }
	            for (int i = 0; i < cols.length - 1; i++) {
	                sheet.autoSizeColumn(i);
	            }
	            sheet.setColumnWidth(6, 80 * 256);
	            sheet.createFreezePane(0, 1);
	            sheet.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(0, Math.max(rowNum - 1, 1), 0, cols.length - 1));

	            // ---- Sheet 2: summary ----
	            Sheet sum = wb.createSheet("Summary");
	            set(sum.createRow(0), 0, "Metric", header);
	            set(sum.getRow(0), 1, "Count", header);
	            String[][] data = {
	                    { "Total", String.valueOf(ENTRIES.size()) },
	                    { "Passed", String.valueOf(p) },
	                    { "Failed", String.valueOf(f) },
	                    { "Skipped", String.valueOf(s) },
	                    { "Pass %", ENTRIES.isEmpty() ? "0" : String.format("%.1f", p * 100.0 / ENTRIES.size()) } };
	            for (int i = 0; i < data.length; i++) {
	                Row r = sum.createRow(i + 1);
	                set(r, 0, data[i][0], plain);
	                set(r, 1, data[i][1], plain);
	            }
	            sum.autoSizeColumn(0);
	            sum.autoSizeColumn(1);

	            try (OutputStream out = new FileOutputStream(file)) {
	                wb.write(out);
	            }
	            System.out.println("Excel status report: " + file.getAbsolutePath());
	            return file.getAbsolutePath();
	        } catch (Exception ex) {
	            // most common cause: the xlsx is open in Excel and locked
	            System.out.println("Could not write Excel status report: " + ex);
	            return null;
	        }
	    }

	    private static void set(Row row, int col, String value, CellStyle style) {
	        Cell c = row.createCell(col);
	        c.setCellValue(value == null ? "" : value);
	        c.setCellStyle(style);
	    }

	    private static CellStyle style(Workbook wb, IndexedColors fill, boolean bold) {
	        CellStyle cs = wb.createCellStyle();
	        cs.setBorderTop(BorderStyle.THIN);
	        cs.setBorderBottom(BorderStyle.THIN);
	        cs.setBorderLeft(BorderStyle.THIN);
	        cs.setBorderRight(BorderStyle.THIN);
	        if (fill != null) {
	            cs.setFillForegroundColor(fill.getIndex());
	            cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        }
	        if (bold) {
	            Font font = wb.createFont();
	            font.setBold(true);
	            cs.setFont(font);
	        }
	        return cs;
	    }
	}


