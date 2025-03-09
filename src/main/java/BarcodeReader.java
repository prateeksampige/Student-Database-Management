import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BarcodeReader {

    static void Function() {
        try {
            File barcodeFile = new File("BarcodePath/Barcode.jpg");//load the barcode file to be scanned
            BufferedImage image = ImageIO.read(barcodeFile);

            //creating a BinaryBitmap from the image
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            //setting up decoding parameters
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            //using the MultiFormatReader to decode the barcode
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);

            //extracting and printing the decoded data
            String decodedData = result.getText();
            String fileName = "ExcelPath/ExcelFile.xlsx";
            ExcelSheetWorker.readFromExcel(fileName, decodedData);//sending the excel filename and usn to extract data from the excel sheet

        } catch (NotFoundException | IOException e) {
            System.out.println("Error While Reading Barcode");
        }
    }
}
