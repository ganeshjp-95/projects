package com.project.FormAuthentication.Util;

import com.project.FormAuthentication.Repository.FormRepository;
import com.project.FormAuthentication.Table.FormTable;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelUtil {


    public static String Header[] ={"S.No","EmployeeId","Name","Age","Role","Organization","Salary"};
    public static String SHEET_NAME ="SheetForProductData";
    public ByteArrayInputStream  dataToExcel(List<FormTable> formTables){

        XSSFWorkbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XSSFSheet sheet =workbook.createSheet(SHEET_NAME);

        Row headerrow = sheet.createRow(0);
        for (int i=0;i< Header.length;i++){
            Cell cell= headerrow.createCell(i);
            cell.setCellValue(Header[i]);
            System.out.println("Header name"+ Header[i]);
            System.out.println("Count =>"+i);
        }

        int RowIndex1 =1;

        for (int i=0; i<formTables.size();i++){
            Row row1 = sheet.createRow(RowIndex1);
            RowIndex1++;
            row1.createCell(0).setCellValue(formTables.get(i).getSno());
            row1.createCell(1).setCellValue(formTables.get(i).getEmployeeid());
            row1.createCell(2).setCellValue(formTables.get(i).getName());
            row1.createCell(3).setCellValue(formTables.get(i).getAge());
            row1.createCell(4).setCellValue(formTables.get(i).getRole());
            row1.createCell(5).setCellValue(formTables.get(i).getOrganisation());
            row1.createCell(6).setCellValue(formTables.get(i).getSalary());
        }

        try {
            workbook.write(byteArrayOutputStream);
            workbook.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());


    }


}
