package utilities;
	import org.apache.poi.util.Units;
	import org.apache.poi.xwpf.usermodel.*;

	import javax.imageio.ImageIO;
	import java.awt.image.BufferedImage;
	import java.io.*;
	import java.text.SimpleDateFormat;
	import java.util.*;

	public class WordReportUtility {

	    private static final ThreadLocal<List<String[]>> steps =
	            ThreadLocal.withInitial(ArrayList::new);

	    // Call this every time a screenshot is saved
	    public static void addStep(String description, String screenshotPath) {
	        steps.get().add(new String[] { description, screenshotPath });
	    }

	    // Call this when the test finishes
	    public static void generate(String testName, String status) {
	        File dir = new File(System.getProperty("user.dir") + "/Reports");
	        dir.mkdirs();
	        String time = new SimpleDateFormat("ddMMMyyyy_HH-mm-ss").format(new Date());
	        File out = new File(dir, testName + "_" + time + ".docx");

	        try (XWPFDocument doc = new XWPFDocument()) {
	            XWPFRun title = doc.createParagraph().createRun();
	            title.setText(testName + " - " + status);
	            title.setBold(true);
	            title.setFontSize(18);
	            title.setColor(status.equals("PASSED") ? "008000" : "C00000");

	            int n = 0;
	            for (String[] s : steps.get()) {
	                XWPFRun cap = doc.createParagraph().createRun();
	                cap.setText("Step " + (++n) + ": " + s[0]);
	                cap.setBold(true);

	                File img = new File(s[1]);
	                if (!img.exists()) continue;

	                BufferedImage bi = ImageIO.read(img);
	                int w = Units.toEMU(430);   // about 6 inches wide
	                int h = (int) ((double) bi.getHeight() / bi.getWidth() * w);

	                XWPFRun pic = doc.createParagraph().createRun();
	                try (FileInputStream in = new FileInputStream(img)) {
	                    pic.addPicture(in, XWPFDocument.PICTURE_TYPE_PNG, img.getName(), w, h);
	                }
	                pic.addBreak();
	            }

	            try (FileOutputStream fos = new FileOutputStream(out)) {
	                doc.write(fos);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            steps.remove();   // clear this thread's list for the next test
	        }
	    }
	}

