package kr.smart.hj5.domain.login;

import kr.smart.hj5.domain.user.UserService;
import kr.smart.hj5.domain.user.responseDto.UserDto;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    /**
     * 로그인 처리
     *
     * @param userId
     * @param userPw
     * @return
     */
    public JSONObject login(String userId, String userPw) {
        JSONObject jSONOResponse = new JSONObject();

        try {
            Integer result = 0;
            UserDto userDto = userService.getUserById(userId);

            if (userDto == null) {
                result = 1;

                jSONOResponse.put("result", "true");
                jSONOResponse.put("message", "");
                jSONOResponse.put("data", result);
                return jSONOResponse;
            }

            if (userPw.equals(userDto.getUserPw())) {
                userService.addLoginHis(userDto.getUserSeq());

                jSONOResponse.put("result", "true");
                jSONOResponse.put("message", "");
                jSONOResponse.put("data", userDto.getSn());
            } else {
                result = 2;

                jSONOResponse.put("result", "true");
                jSONOResponse.put("message", "");
                jSONOResponse.put("data", result);
            }
        } catch (Exception ex) {
            jSONOResponse.put("result", "false");
            jSONOResponse.put("message", ex.getMessage());
            jSONOResponse.put("data", new HashMap<>());
        }

        return jSONOResponse;
    }
}