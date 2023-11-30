package kr.smart.hj5.domain.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.smart.hj5.domain.user.requestDto.SignUpRequestDto;
import kr.smart.hj5.utils.MailUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Api(tags = "UserController", description = "사용자")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입", notes = "" +
            "0 : 정상\n" +
            "1 : 계정 이미 등록되어있음\n" +
            "4 : 사전 데이터에 사용자 정보 없음" +
            "")
    @ResponseBody
    public JSONObject signUp(
            @RequestBody SignUpRequestDto singUp
    ) {
        JSONObject jSONOResponse = userService.signUp(singUp);
        return jSONOResponse;
    }

    @GetMapping("/sign-up")
    @ApiOperation(value = "회원가입 화면", notes = "")
    public String signUpPage() {
        return "/signUp";
    }

    @GetMapping("/test")
    @ApiOperation(value = "", notes = "")
    public void test() {
        MailUtil.sendMail();
    }
}