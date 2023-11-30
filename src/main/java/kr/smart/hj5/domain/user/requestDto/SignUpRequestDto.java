package kr.smart.hj5.domain.user.requestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter를 자동 생성
@Setter // setter를 자동 생성
@ToString // toString을 자동 생성
public class SignUpRequestDto {
    private String userId;
    private String userPw;
    private String hp;
    private String dong;
    private String ho;
    private String sn;
    private String tempPw;

    /**
     * ho 값이 4자리 미만일 때 앞에 0을 붙이도록 처리
     *
     * @param ho
     */
    public void setHo(String ho) {
        if (ho != null) {
            this.ho = String.format("%04d", Integer.parseInt(ho));

            // setHo 함수 실행 후 sn 계산
            this.sn = this.dong + this.ho;
        } else {
            this.ho = ho;
        }
    }
}