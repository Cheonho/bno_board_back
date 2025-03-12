package com.bno.board_back.dto.responseDto;
import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
@ToString
public class OtpSetupResponseDto extends ResponseDto{

    private final String otpAuthUrl;

    public OtpSetupResponseDto(String otpAuthUrl) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.otpAuthUrl = otpAuthUrl; // 이렇게 해야 받은 데이터 저장
    }

    public static ResponseEntity<OtpSetupResponseDto> success(String otpAuthUrl) {
        OtpSetupResponseDto result = new OtpSetupResponseDto(otpAuthUrl);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
