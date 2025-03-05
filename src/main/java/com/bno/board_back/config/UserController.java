package com.bno.board_back.config;

import com.bno.board_back.dto.responseDto.GetUserCheckEmailDto;
import com.bno.board_back.dto.responseDto.GetUserCheckNicknameDto;
import com.bno.board_back.dto.responseDto.GetUserJoinResponseDto;
import com.bno.board_back.dto.responseDto.GetUserLoginResponseDto;
import com.bno.board_back.dto.userDto.*;
import com.bno.board_back.service.OtpResultServlet;
import com.bno.board_back.service.OtpServlet;
import com.bno.board_back.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
public class UserController {

    private final UserService userService;
    private final OtpResultServlet otpResultServlet ;
    private final OtpServlet otpServlet ;

    @Autowired
    public UserController(UserService userService, OtpResultServlet otpResultServlet, OtpServlet otpServlet) {
        this.userService = userService;
        this.otpResultServlet = otpResultServlet ;
        this.otpServlet = otpServlet ;
    }

    @PostMapping("/login")
    public ResponseEntity<? super GetUserLoginResponseDto> LoginPage(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {

        ResponseEntity<? super GetUserLoginResponseDto> response = userService.loginPage(loginRequestDto);
        session.setAttribute("loginDto", response);
        return response ;
    }


   @PostMapping("/join")
    public ResponseEntity<? super GetUserJoinResponseDto> JoinPage(@RequestBody @Valid JoinRequestDto joinRequestDto, BindingResult bindingResult) {
        ResponseEntity<? super GetUserJoinResponseDto> response = userService.joinPage(joinRequestDto, bindingResult) ;
            return response ;
        }


    @GetMapping("/idcheck")
    public ResponseEntity<? super GetUserCheckEmailDto> EmailCheckPage(@RequestParam String email) {
        ResponseEntity<? super GetUserCheckEmailDto> response = userService.checkEmail(email) ;
        return response ;
    }

    @GetMapping("/namecheck")
    public ResponseEntity<? super GetUserCheckNicknameDto> NameCheckPage(@RequestParam String userNickname) {
        ResponseEntity<? super GetUserCheckNicknameDto> response = userService.checkNickname(userNickname) ;
        return response ;
    }

    /*
    @PostMapping("/nicknamecorrection")
    public ResponseEntity<UserDto> Nicknamecorrection (@RequestBody @Valid UserDto userDto) {
        UserDto usermodel = userService.NicknameUpdate(userDto);
        return new ResponseEntity<>(usermodel,HttpStatus.OK);
    }*/

  /*  @PostMapping("/passwordcorrection")
    public ResponseEntity<UserDto> Passwordcorrection (@RequestBody UserDto userDto) {
        UserDto usermodel = userService.PasswordUpdate(userDto) ;
        return new
    }*/

    @RequestMapping("/otp")
    public String generateOtp(HttpServletRequest request, ModelMap modelMap) {
        try {
            String encodedKey = OtpServlet.generateOtpAndGetKey();

            UserDto userDto = (UserDto) request.getSession().getAttribute("loggedInUser");

            String email = userDto.getEmail();
            String Nickname = userDto.getUserNickname();
            String qrCodeUrl = OtpServlet.getQRBarcodeURL(email, Nickname, encodedKey);

            modelMap.addAttribute("encodedKey", encodedKey);
            modelMap.addAttribute("qrCodeUrl", qrCodeUrl);
            modelMap.addAttribute("qr", OtpServlet.getQrCodeUrl(userDto.getUserNickname(), encodedKey));

            request.getSession().setAttribute("encodedKey", encodedKey);
            request.getSession().setAttribute("url", qrCodeUrl);

            return "/board";
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("error", "An error occurred");
            return "/error";
        }
    }

    /*
    사용자로부터 입력받은 OTP를 검증하는 메서드
    @param userCode 사용자가 입력한 OTP 코드
    @param encodedKey 서버에서 생성된 OTP 코드
    @param request HttpServletRequest 객체
    @param modelMap ModelMap 객체
    @return 검증 결과에 따라 이동할 페이지 경로
     */

    @PostMapping("/otp/")
    public String adminOtpVerification(@RequestParam("user_code") String userCode, @RequestParam("encodedKey") String encodedKey,
                                       HttpServletRequest request, ModelMap modelMap) {
        if (encodedKey == null || encodedKey.isEmpty()) {
            modelMap.addAttribute("error", "OTP 키가 유효하지 않습니다. 다시 시도해 주세요");
            return "/";
        }

        boolean otpVerificationResult = false;
        try {
            otpVerificationResult =
                    OtpResultServlet.check_Code(encodedKey, Long.parseLong(userCode), new
                            Date().getTime() / 30000);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        if (otpVerificationResult) {
// OTP 검증 성공 시
            return "redirect:/admin/dashboard"; // 대시보드로 리다이렉트
        } else {
// OTP 검증 실패 시
            modelMap.addAttribute("error", "OTP 검증에 실패했습니다. 다시 시도해 주세요.");
            return "/otpVerificationErrorPage"; // 오류 페이지 예시
        }
    }

}