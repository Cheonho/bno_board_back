package com.bno.board_back.provider.OTP;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.codec.binary.Base32;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class GoogleOtp {

    /**
     * 비밀키 생성.
     *
     * @return 32자리의 비밀키
     */
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    /**
     * QR코드 URL 생성.
     *
     * @param displayName 표시할 이름
     * @param secret      비밀키
     * @return QR코드 URL
     */
    public static String getQrCodeUrl(String displayName, String secret)
            throws Exception {
        String format = "otpauth://totp/" + URLEncoder.encode(displayName, StandardCharsets.UTF_8)
                .replace("+", "%20")
                + "?secret=" + secret;
        return generateQRCodeImage(format);
    }

    /**
     * QR코드 이미지 생성.
     *
     * @param barcodeText 바코드 텍스트
     * @return QR코드 이미지
     */
    public static String generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
    }
}