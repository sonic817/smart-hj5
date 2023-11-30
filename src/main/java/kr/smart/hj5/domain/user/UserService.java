package kr.smart.hj5.domain.user;

import kr.smart.hj5.domain.user.requestDto.SignUpRequestDto;
import kr.smart.hj5.domain.user.responseDto.SignUpResponseDto;
import kr.smart.hj5.domain.user.responseDto.UserDto;
import kr.smart.hj5.domain.user.responseDto.UserPreRegDto;
import kr.smart.hj5.utils.DateUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 회원가입 처리
     *
     * @param signUpRequestDto
     * @return
     */
    public JSONObject signUp(SignUpRequestDto signUpRequestDto) {
        JSONObject jSONOResponse = new JSONObject();

        try {
            Integer result = 0;

            // 사전 등록자 확인하기
            result = checkPreUser(signUpRequestDto);

            if (!result.equals(0)) {
                jSONOResponse.put("result", "true");
                jSONOResponse.put("message", "");
                jSONOResponse.put("data", result);
                return jSONOResponse;
            }

            UserDto userDto = userMapper.getUser(signUpRequestDto.getSn());

            if (userDto != null) {
                result = 1;

                jSONOResponse.put("result", "true");
                jSONOResponse.put("message", "");
                jSONOResponse.put("data", result);
                return jSONOResponse;
            }

            SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
            signUpResponseDto.setUserId(signUpRequestDto.getUserId());
            signUpResponseDto.setUserPw(signUpRequestDto.getUserPw());
            signUpResponseDto.setHp(signUpRequestDto.getHp());
            signUpResponseDto.setDong(signUpRequestDto.getDong());
            signUpResponseDto.setHo(signUpRequestDto.getHo());
            signUpResponseDto.setSn(signUpRequestDto.getSn());

            // 회원가입
            userMapper.addUser(signUpResponseDto);

            setPreUser(signUpRequestDto);

            jSONOResponse.put("result", "true");
            jSONOResponse.put("message", "");
            jSONOResponse.put("data", result);
        } catch (Exception ex) {
            jSONOResponse.put("result", "false");
            jSONOResponse.put("message", ex.getMessage());
            jSONOResponse.put("data", new HashMap<>());
        }

        return jSONOResponse;
    }

    /**
     * 사전 등록자인지 확인하기
     *
     * @param signUpRequestDto
     */
    private Integer checkPreUser(SignUpRequestDto signUpRequestDto) {
        UserPreRegDto userPreRegDto = getUserPreRegDto(signUpRequestDto.getDong(), signUpRequestDto.getHo(), signUpRequestDto.getTempPw());

        if (userPreRegDto == null) {
            return 4;
        }

        return 0;
    }

    /**
     * @param dong
     * @param ho
     * @param tempPw
     * @return
     */
    private UserPreRegDto getUserPreRegDto(String dong, String ho, String tempPw) {
        return userMapper.getUserPreRegDto(dong, ho, tempPw);
    }

    /**
     * @param signUpRequestDto
     */
    private void setPreUser(SignUpRequestDto signUpRequestDto) {
        UserPreRegDto userPreRegDto = getUserPreRegDto(signUpRequestDto.getDong(), signUpRequestDto.getHo(), signUpRequestDto.getTempPw());

        if (userPreRegDto == null) {
            return;
        }

        setUserPreRegDto(signUpRequestDto.getDong(), signUpRequestDto.getHo(), signUpRequestDto.getTempPw(), 1);
    }

    /**
     * 기존에 등록되어 있는 사용자 플래그 수정하기
     *
     * @param dong
     * @param ho
     * @param tempPw
     * @param flag
     */
    private void setUserPreRegDto(String dong, String ho, String tempPw, Integer flag) {
        userMapper.setUserPreRegDto(dong, ho, tempPw, DateUtil.getyyyyMMddHHmmssS(), flag);
    }

    /**
     * 로그인 로그 남기기
     *
     * @param userSeq
     */
    public void addLoginHis(Integer userSeq) {
        userMapper.addLoginHis(userSeq);
    }

    /**
     * id로 user 가져오기
     *
     * @param userId
     * @return
     */
    public UserDto getUserById(String userId) {
        return userMapper.getUserById(userId);
    }
}