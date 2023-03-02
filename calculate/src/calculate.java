import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class calculate {
    public static void main(String args[]) throws IOException {
        JFrame jFrame = new JFrame();
        File file;
        JFileChooser jFileChooser = new JFileChooser();
        int flag = jFileChooser.showOpenDialog(jFrame);
        file = jFileChooser.getSelectedFile();
        WritableWorkbook newBook = Workbook.createWorkbook(new File("C:/Users/chenhengke/Desktop/表.xls"));
        WritableSheet newSheet = newBook.createSheet("结果", 0);

        try {

            Workbook book = Workbook.getWorkbook(file);//获得表格
            Sheet sheet = book.getSheet(0); // 获得第一个工作表对象
            for (int i = 0; i < sheet.getRows(); i++) {
                double paid = 0;
                String[] temp = new String[20];
                int count = 0;
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i); // 获得单元格
                    temp[j] = cell.getContents();
                    Label label = new Label(j, i, cell.getContents());
                    newSheet.addCell(label);
                 }
                if (i == 0) {
                    Label label = new Label(11, i, "计算费用");
                    newSheet.addCell(label);
                    Label label1 = new Label(12, i, "差值");
                    newSheet.addCell(label1);
                    continue;
                }
                Date date = new Date(temp[3],temp[4]);
                double payment = date.getPayment();
                jxl.write.Number number = new jxl.write.Number(11, i, payment);
                paid = Double.parseDouble(temp[6]);
                System.out.println(i + " " + payment + " -" + paid + "  =" + (payment - paid));
                jxl.write.Number number1 = new jxl.write.Number(12, i, (payment - paid));
                newSheet.addCell(number);
                newSheet.addCell(number1);
                //System.out.print("\n");
             }
        } catch (BiffException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        } catch (RowsExceededException e) {
            throw new RuntimeException(e);
        } catch (WriteException e) {
            throw new RuntimeException(e);
        }
        newBook.write();
        try {
            newBook.close();
        } catch (WriteException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
