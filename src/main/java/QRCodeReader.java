import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class QRCodeReader {
    static void Function()
    {
        try
        {
            String path = "QRCodePath/QRCode.jpg";//keeping the qr code to scan in a seperate file

            BufferedImage bf = ImageIO.read(new FileInputStream(path));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));

            Result result = new MultiFormatReader().decode(bitmap);//decoding the qr code to extract the encoded link
            System.out.println(result.getText());

        }
        catch (Exception e)
        {
            System.out.println("Error While Reading QR Code");

        }
    }}