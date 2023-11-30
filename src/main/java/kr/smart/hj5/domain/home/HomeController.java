package kr.smart.hj5.domain.home;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "HomeController", description = "홈")
public class HomeController {

    @GetMapping("/")
    @ApiOperation(value = "처음으로 보여지는 화면", notes = "")
    public String homePage() {
        return "/login";
    }
}