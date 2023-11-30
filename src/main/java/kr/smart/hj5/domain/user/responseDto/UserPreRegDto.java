package kr.smart.hj5.domain.user.responseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter를 자동 생성
@Setter // setter를 자동 생성
@ToString // toString을 자동 생성
public class UserPreRegDto {
    private Integer userPreSeq;
    private Integer dong;
    private Integer ho;
    private String hp;
    private String regTs;
    private Integer useFlag;
    private String insertTs;
    private String updateTs;
}