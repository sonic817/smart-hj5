package kr.smart.hj5.domain.user;

import kr.smart.hj5.domain.user.requestDto.SignUpRequestDto;
import kr.smart.hj5.domain.user.responseDto.SignUpResponseDto;
import kr.smart.hj5.domain.user.responseDto.UserDto;
import kr.smart.hj5.domain.user.responseDto.UserPreRegDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /**
     * 사용자 가져오기
     *
     * @param sn
     * @return
     */
    UserDto getUser(@Param("sn") String sn);

    /**
     * 회원가입
     *
     * @param signUpResponseDto
     * @return
     */
    Integer addUser(@Param("dto") SignUpResponseDto signUpResponseDto);

    /**
     * @param dong
     * @param ho
     * @param tempPw
     * @return
     */
    UserPreRegDto getUserPreRegDto(@Param("dong") String dong, @Param("ho") String ho, @Param("tempPw") String tempPw);

    /**
     * @param dong
     * @param ho
     * @param tempPw
     * @param regTs
     * @param flag
     * @return
     */
    Integer setUserPreRegDto(@Param("dong") String dong, @Param("ho") String ho, @Param("tempPw") String tempPw, @Param("regTs") String regTs, @Param("flag") Integer flag);

    /**
     * 로그인 로그 남기기
     *
     * @param userSeq
     * @return
     */
    Integer addLoginHis(@Param("userSeq") Integer userSeq);

    /**
     * id로 user 가져오기
     *
     * @param userId
     * @return
     */
    UserDto getUserById(@Param("userId") String userId);
}