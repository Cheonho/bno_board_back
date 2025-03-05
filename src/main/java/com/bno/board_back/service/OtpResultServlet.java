package com.bno.board_back.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


@Service
public class OtpResultServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 클라이언트로부터 'user_code' 파라미터를 가져와 문자열로 저장
        String user_codeStr = req.getParameter("user_code");
        // 'user_code' 문자열을 정수로 변환하여 저장
        long user_code = Integer.parseInt(user_codeStr);
        // 클라이언트로부터 'encodedKey' 파라미터를 가져와 저장
        String encodedKey = req.getParameter("encodedKey");
        // 현재 시간을 밀리초로 가져옴
        long currentTime = new Date().getTime();
        // 현재 시간을 30초 단위로 나눔
        long timeSlot = currentTime / 30000;

        // OTP 검증 결과를 저장할 변수 초기화
        boolean check_code = false;
        try {
            // 'encodedKey', 'user_code', 'timeSlot' 값을 사용하여 OTP를 검증
            check_code = check_Code(encodedKey, user_code, timeSlot);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            // 예외 발생 시 스택 트레이스를 출력
            e.printStackTrace();
        }

        // 검증 결과를 콘솔에 출력
        System.out.println("check_code: " + check_code);
    }

    /*
     * 주어진 비밀키와 시간을 사용하여 OTP를 검증합니다.
     * @param secret 비밀키 (Base32로 디코딩된 바이트 배열)
     * @param code 사용자가 입력한 OTP 코드
     * @param t 현재 시간대를 나타내는 값 (시간 슬라이딩 윈도우에 사용됨)
     * @return 일치하는 OTP 코드가 발견되면 true, 그렇지 않으면 false 반환
     * @throws NoSuchAlgorithmException 암호화 알고리즘이 지원되지 않을 때 발생
     * @throws InvalidKeyException 주어진 키가 유효하지 않을 때 발생
     */
    public static boolean check_Code(String secret, long code, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        // Base32 인코딩 객체 생성
        Base32 codec = new Base32();

        // 비밀키를 Base32로 디코딩
        byte[] decodedKey = codec.decode(secret);

        // 시간 창 크기를 3으로 설정 (-3부터 +3까지의 시간 창을 확인)
        int window = 3;

        // -3부터 +3까지의 시간 창을 확인
        for (int i = -window; i <= window; ++i) {
            // 현재 시간 창에 대한 OTP 코드 생성
            long hash = verify_code(decodedKey, t + i);

            // 생성된 OTP 코드와 사용자가 입력한 코드가 일치하는지 확인
            if (hash == code) {
                // 일치하면 true 반환
                return true;
            }
        }

        // 일치하는 코드가 없으면 false 반환
        return false;
    }

    /*
     * 주어진 비밀키와 시간을 사용하여 OTP를 생성합니다.
     * @param decodedKey Base32로 디코딩된 비밀키
     * @param time 시간
     * @return 생성된 OTP 코드
     */
    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        // 8바이트 배열 생성
        byte[] data = new byte[8];

        // 주어진 시간 값을 바이트 배열로 변환
        long value = t ;
        for (int i = 8; i --> 0; value >>>=8) {
            data[i] = (byte)value;
        }

        // 비밀키를 사용하여 HMAC-SHA1 서명 객체 생성
        SecretKeySpec signkey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");

        // HMAC-SHA1 서명 객체 초기화
        mac.init(signkey);

        // 데이터에 대한 HMAC-SHA1 해시 생성
        byte[] hash = mac.doFinal(data);

        // 해시의 마지막 바이트에서 오프셋을 결정
        int offset = hash[20-1] & 0xF;

        // 해시의 일부분을 사용하여 OTP 코드 생성
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }

        // OTP 코드를 6자리로 변환
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;

        // 생성된 OTP 코드를 정수로 반환
        return (int) truncatedHash;
    }
}
