import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.nio.file.Paths;

import java.util.Scanner;

public class BarcodeGenerator
{
    static void Function()
    {
        Scanner in = new Scanner(System.in);
        try
        {
            System.out.print("Enter the name : ");
            String name = in.nextLine();
            name = name.toUpperCase();
            System.out.print("Enter the USN : ");
            String usn = in.next();
            usn = usn.toUpperCase();
            System.out.print("Enter marks of first subject : ");
            int marks1 = in.nextInt();
            System.out.print("Enter marks of second subject : ");
            int marks2 = in.nextInt();
            System.out.print("Enter marks of third subject : ");
            int marks3 = in.nextInt();
            //taking in input of usn, name and marks

            String excelPath = "ExcelPath.xlsx";

            ExcelSheetWorker.writeToExcel(excelPath, usn, name, marks1, marks2, marks3);//entering the data into the excel sheet

            String codePath = "BarcodePath"+usn+".jpg";

            Code128Writer writer = new Code128Writer();
            BitMatrix matrix = writer.encode(usn,BarcodeFormat.CODE_128,500,300);//encoding the usn into the barcode
            MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(codePath));
            System.out.println("Barcode Successfully Generated");
        }
        catch(Exception e)
        {
            System.out.println("Error While Creating Barcode");
        }
    }
}
