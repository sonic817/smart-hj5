package kr.smart.hj5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		// 정적 리소스 핸들러 설정
		// "/static/**" 경로로 들어오는 요청을 처리하기 위해 "classpath:/templates/"와 "classpath:/static/" 경로에서 리소스를 찾음
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/templates/", "classpath:/static/");

		// Swagger UI 리소스 핸들러 설정
		// "/swagger-ui.html**"와 "/webjars/**" 경로로 들어오는 요청을 처리하기 위해
		// "classpath:/META-INF/resources/swagger-ui.html"와 "classpath:/META-INF/resources/webjars/" 경로에서 리소스를 찾음
		registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// CORS(Cross-Origin Resource Sharing) 설정
		// 모든 경로("/**")에 대해 모든 origin에서 접근을 허용하도록 설정
		registry.addMapping("/**").allowedOrigins("*");
	}
}