import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Scanner;
public class MainPage {
    public static void main(String[] args) throws IOException, WriterException {
        Scanner in = new Scanner(System.in);
        System.out.print("1 for barcode generator\n2 for QR code generator\n3 for scanning barcode\n4 for scanning QR code\nEnter input : ");
        int n = in.nextInt();
        switch (n)
        {
            case 1:
                BarcodeGenerator.Function();
                break;
            case 2:
                QRCodeGenerator.Function();
                break;
            case 3:
                BarcodeReader.Function();
                break;
            case 4:
                QRCodeReader.Function();
                break;
            default:
                System.out.println("Enter valid input");
        }
    }
}
