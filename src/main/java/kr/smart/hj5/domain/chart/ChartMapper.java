package kr.smart.hj5.domain.chart;

import kr.smart.hj5.domain.chart.responseDto.MeteringWaterResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChartMapper {

    /**
     *
     * @param sn
     * @param yyyy_MM_dd
     * @return
     */
    List<MeteringWaterResponseDto> getTwoHourlyWaterFlowChangeList(@Param("sn") String sn, @Param("yyyy_MM_dd") String yyyy_MM_dd);

    /**
     *
     * @param sn
     * @return
     */
    List<MeteringWaterResponseDto> getWeeklyWaterFlowChangeList(@Param("sn") String sn);

    /**
     *
     * @param sn
     * @return
     */
    List<MeteringWaterResponseDto> getMonthlySixWaterFlowChangeList(@Param("sn") String sn);
}