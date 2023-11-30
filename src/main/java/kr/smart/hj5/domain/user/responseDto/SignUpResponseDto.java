package kr.smart.hj5.domain.user.responseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter를 자동 생성
@Setter // setter를 자동 생성
@ToString // toString을 자동 생성
public class SignUpResponseDto {
    private Integer userSeq;
    private String userId;
    private String userPw;
    private String hp;
    private String dong;
    private String ho;
    private String sn;
}