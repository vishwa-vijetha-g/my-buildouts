package demo;

import org.testng.annotations.*;
import java.io.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProviderClass {
    
    @DataProvider (name = "Data Provider Method")
    public Object[][] dataProviderMethod() throws IOException{
        
        
        File file = new File("src\\test\\resources\\datasheet.xlsx");
        
        FileInputStream fileinputstream = new FileInputStream(file);
        
        XSSFWorkbook workbook = new XSSFWorkbook(fileinputstream);
        
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        
        int rows = sheet.getLastRowNum();
        
        Object[][] data = new String[rows][1];

        for(int i=1;i<=rows;i++){
            XSSFCell cell = sheet.getRow(i).getCell(0);
            data[i-1][0] = cell.getStringCellValue();
        }

        workbook.close();

        return data;
        

        /*
        Object[][] stringArray = {{"Movies"},{"Music"},{"Games"},{"India"},{"UK"}};
        return stringArray;
        */


    }
}
