import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelSheetWorker {

    public static void writeToExcel(String fileName, String usn, String name, int marks1, int marks2, int marks3) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheet(usn.charAt(7) + "" + usn.charAt(8));//opening the excel sheet based on branch given in usn

            //iterating through the rows of the sheet
            for (Row row : sheet) {
                //getting the first cell of each row
                Cell cell = row.getCell(0);

                //checking if the cell is not null
                if (cell != null) {
                    if(cell.getStringCellValue().equals(usn))//checking if the input usn is stored in the excel sheet
                    {
                        System.out.println("USN Already Exists");
                        System.exit(1);
                    }
                }
            }

            int avg = (int)Math.ceil((marks1+marks2+marks3)/3);//calculating average of given marks
            Object[][] data = {
                    {usn, name, marks1, marks2, marks3, avg}
            };

            Row dataRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
            for (int j = 0; j < data[0].length; j++) {
                Cell cell = dataRow.createCell(j);
                if (data[0][j] instanceof String) {
                    cell.setCellValue((String) data[0][j]);
                } else if (data[0][j] instanceof Integer) {
                    cell.setCellValue((Integer) data[0][j]);
                }
                //entering the data into the excel sheet
            }

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("Excel File Updated Successfully");

            } catch (IOException e) {
                System.out.println("Error While Updating Excel File");
                System.exit(1);
            }

        } catch (IOException e) {
            System.out.println("Error While Updating Excel File");
            System.exit(1);
        }
    }

    static void readFromExcel(String fileName, String usn) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet(usn.charAt(7) + "" + usn.charAt(8));//opening the excel sheet based on branch given in usn

            //iterating through rows and columns
            for (Row row : sheet) {
                //getting the first cell of each row
                Cell cell = row.getCell(0);

                //checking if the cell is not null
                if (cell != null) {
                    if(cell.getStringCellValue().equals(usn))
                    {
                        for(Cell requiredCell : sheet.getRow(0))
                        {
                            System.out.print(requiredCell + "\t");
                        }
                        System.out.println();
                        for(Cell requiredCell : row)
                        {
                            System.out.print(requiredCell + "\t");
                        }
                        System.out.println();
                        System.exit(0);
                    }
                    //printing all the data stored corresponding to the decoded usn
                }
            }
            System.out.println("Error USN Not Found");


        } catch (IOException e) {
            System.out.println("Error While Using Excel File");
        }
    }
}
