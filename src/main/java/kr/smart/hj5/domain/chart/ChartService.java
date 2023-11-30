package kr.smart.hj5.domain.chart;

import kr.smart.hj5.domain.chart.responseDto.MeteringWaterResponseDto;
import kr.smart.hj5.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartService {

    @Autowired
    ChartMapper chartMapper;

    public List<MeteringWaterResponseDto> getTwoHourlyWaterFlowChangeUsage(String sn) {
        return chartMapper.getTwoHourlyWaterFlowChangeList(sn, DateUtil.getyyyy_MM_dd());
    }

    /**
     * 일일 사용량
     *
     * @param sn
     * @return
     */
    public double getDayWaterUsage(String sn) {
        List<MeteringWaterResponseDto> meteringWaterList = getTwoHourlyWaterFlowChangeUsage(sn);

        double usageForDay = 0;

        for (int i = 0; i < meteringWaterList.size(); i++) {
            usageForDay += meteringWaterList.get(i).getMeter();
        }

        return usageForDay;
    }

    public List<MeteringWaterResponseDto> getWeeklyWaterFlowChangeUsage(String sn) {
        return chartMapper.getWeeklyWaterFlowChangeList(sn);
    }

    public List<MeteringWaterResponseDto> getMonthlySixWaterFlowChangeUsage(String sn) {
        return chartMapper.getMonthlySixWaterFlowChangeList(sn);
    }
}