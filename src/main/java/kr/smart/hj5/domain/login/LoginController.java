package kr.smart.hj5.domain.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
@Api(tags = "LoginController", description = "로그인")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("")
    @ApiOperation(value = "로그인", notes = "")
    @ResponseBody
    public JSONObject login(
            @ApiParam(value = "아이디", required = true) @RequestParam("userId") String userId,
            @ApiParam(value = "비밀번호", required = true) @RequestParam("userPw") String userPw
    ) {
        JSONObject jSONOResponse = loginService.login(userId, userPw);
        return jSONOResponse;
    }
}