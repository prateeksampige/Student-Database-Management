import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class QRCodeGenerator {
    static void Function() throws IOException, WriterException {
        Scanner s = new Scanner(System.in);
        System.out.println("Paste The Link Of EL Drive Folder : ");
        String data = s.nextLine();//taking input of drive link
        System.out.println("Enter the USN : ");
        String usn = s.next();
        String path = "QRCodePath" + usn + ".jpg";//saving qr code as your usn

        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);//storing the drive link for the qr code
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));

        System.out.println("QR Code Successfully Generated");
    }
}
