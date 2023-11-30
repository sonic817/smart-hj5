package kr.smart.hj5.domain.chart.responseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getter를 자동 생성
@Setter // setter를 자동 생성
@ToString // toString을 자동 생성
public class MeteringWaterResponseDto {
    private double meter;
    private String meterTs;
}