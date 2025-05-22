package model.payments;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class QRCodeGenerator {
    public static BufferedImage generateQRCode(String data, int width, int height) throws WriterException{
        QRCodeWriter qrCodeWritter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWritter.encode(data, BarcodeFormat.QR_CODE,width,height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


}
