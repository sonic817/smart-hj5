package kr.smart.hj5.domain.chart;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chart")
@Api(tags = "ChartController", description = "차트")
public class ChartController {

    @Autowired
    ChartService chartService;

    @GetMapping("/day")
    @ApiOperation(value = "일별 차트 화면 가져오기", notes = "")
    public String getDayChartPage(
            Model model,
            @ApiParam(value = "sn", required = true) @RequestParam("sn") String sn
    ) {
        model.addAttribute("usageForDay", chartService.getDayWaterUsage(sn));
        model.addAttribute("weeklyMeterList", chartService.getWeeklyWaterFlowChangeUsage(sn));
        model.addAttribute("titleForDay", "일일 미터 사용량");
        model.addAttribute("title", "7일간 미터 사용량");
        return "/chart";
    }


    @GetMapping("/month")
    @ApiOperation(value = "월별 차트 화면 가져오기", notes = "")
    public String getMonthChartPage(
            Model model,
            @ApiParam(value = "sn", required = true) @RequestParam("sn") String sn
    ) {
        model.addAttribute("meterList", chartService.getWeeklyWaterFlowChangeUsage(sn));
        model.addAttribute("title", "월일 미터 사용량");
        return "/chart";
    }

    @GetMapping("/menu")
    @ApiOperation(value = "차트 상위 메뉴 화면 가져오기", notes = "")
    public String getMainMenuPage(
            Model model,
            @ApiParam(value = "sn", required = true) @RequestParam("sn") String sn
    ) {
        model.addAttribute("sn", sn);
        return "/chartMenu";
    }
}