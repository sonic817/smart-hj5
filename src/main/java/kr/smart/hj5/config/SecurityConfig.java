package kr.smart.hj5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 아래 경로의 정적 자원에 대한 보안 설정 무시
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/static/html/*",
                "/static/**",
                "/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers(
                            "/**"
                    ).permitAll() // 모든 경로에 대해 접근 허용
                    .anyRequest().authenticated() // 나머지 요청에는 인증이 필요
                .and()
                    .csrf().disable() // CSRF 보안 비활성화
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 생성하지 않음
                .and()
                    .formLogin().disable() // 기본 로그인 폼 비활성화
                    .httpBasic().disable(); // HTTP 기본 인증 비활성화
//                .formLogin()
//                    .loginPage("/") // 사용자가 로그인을 시도할 때 사용할 로그인 페이지 경로 설정
//                    .defaultSuccessUrl("/home/a") // 로그인 성공 후 이동할 기본 페이지 경로 설정
//                    .failureUrl("/"); // 로그인 실패 시 이동할 페이지 경로 설정

    }
}