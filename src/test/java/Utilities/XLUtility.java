package Utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fi;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;
    public CellStyle style;

    public XLUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetname) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetname);
        int rowcount = sheet.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }
    public int getCellCount(String sheetname,int rownum) throws IOException{
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetname);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }
    public String getCellData(String sheetname,int rownum,int colnum) throws IOException,IllegalStateException {

            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            sheet = wb.getSheet(sheetname);
            row = sheet.getRow(rownum);
            cell = row.getCell(colnum);
            String data = String.valueOf(sheet.getRow(rownum).getCell(0).getStringCellValue());
            wb.close();
            fi.close();
            return data;
        }
    }
