package com.bno.board_back.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/* OtpServlet 클래스는 OTP(One-Time Password)를 생성하고,
 * QR 코드 형태로 클라이언트에게 제공하는 기능을 담당합니다.
 * 이 클래스는 HttpServlet을 상속하여 HTTP 요청을 처리합니다.
 */

@Service
public class OtpServlet extends HttpServlet {

    /*
     * HTTP 요청을 처리하는 메서드입니다.
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @throws ServletException 서블릿 관련 예외
     * @throws IOException 입출력 관련 예외
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // OTP 생성 및 키 반환
        String encodedKey = generateOtpAndGetKey();
        // 생성된 Key 출력
        System.out.println("generated encodedKey: " + encodedKey);
        // 생성된 바코드 주소
        String url = getQRBarcodeURL("gj", "jju_blog", encodedKey);
        System.out.println("URL: " + url);
        // 뷰 경로 설정
        String view = "/WEB-INF/view/otpTest.jsp";
        // 요청에 속성 설정
        req.setAttribute("encodedKey", encodedKey);
        req.setAttribute("url", url);
        // 뷰로 포워딩
        req.getRequestDispatcher(view).forward(req, res);
    }

    /* OTP 키를 생성하고 Base32로 인코딩된 문자열을 반환합니다.
     * @return 생성된 OTP 키
     */
    public static String generateOtpAndGetKey() {
        // 80비트 랜덤 숫자 배열
        byte[] buffer = new byte[10];

        // 랜덤한 바이트 배열 생성
        new SecureRandom().nextBytes(buffer);
        // Base32 인코딩
        Base32 codec = new Base32();
        // 40비트 키 생성
        byte[] secretKey = Arrays.copyOf(buffer, 5);
        // 생성된 키를 반환
        String generatedKey = new String(codec.encode(secretKey));
        System.out.println("Generated Key: " + generatedKey);
        return generatedKey;
    }

    /* 주어진 사용자, 호스트, 비밀키를 사용하여 QR 바코드 URL을 생성합니다.
     * @param email 사용자명
     * @param host 호스트명
     * @param secret 비밀키
     * @return 생성된 QR 바코드 URL
     */
    public static String getQRBarcodeURL(String email, String host, String secret) {
        // QR 바코드 URL 포맷
        String format = "https://chart.googleapis.com/chart?cht=qr&chs=300x300&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
        // 포맷을 사용하여 URL 생성
        return String.format(format, email, host, secret);
    }

    /* 주어진 이름과 비밀키를 사용하여 QR 코드 URL을 생성합니다.
     * @param displayName 표시할 이름
     * @param secret 비밀키
     * @return 생성된 QR 코드 URL
     * @throws Exception 예외 발생 시
     */
    public static String getQrCodeUrl(String displayName, String secret) throws Exception {
        // QR 코드 텍스트 포맷
        String format = "otpauth://totp/" +
                URLEncoder.encode(displayName, StandardCharsets.UTF_8.toString()).replace("+", "%20") +
                "?secret=" + secret;
        return format;
    }

    /**
     * 주어진 바코드 텍스트를 사용하여 QR 코드 이미지를 생성합니다.
     * @param barcodeText 바코드 텍스트
     * @return 생성된 QR 코드 이미지 (Base64 인코딩)
     * @throws Exception 예외 발생 시
     */
    public static String generateQRCodeImage(String barcodeText) throws Exception {
        // QR 코드 작성기 생성
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // 바코드 텍스트를 QR 코드로 인코딩
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        // PNG 출력 스트림 생성
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        // 비트 매트릭스를 PNG 포맷으로 스트림에 작성
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        // Base64로 인코딩하여 반환
        return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
    }
}
