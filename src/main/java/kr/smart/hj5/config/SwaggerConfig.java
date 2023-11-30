package kr.smart.hj5.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Docket 설정
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(getProduceContentTypes())
                .groupName("스마트 아미")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("kr.smart.hj5"))
                .paths(docketApi())
                .build();
    }

    /**
     * @return
     */
    private Predicate<String> docketApi() {
        return
                Predicates.or(regex("/chart.*"),
                        Predicates.or(regex("/login.*")),
                        Predicates.or(regex("/user.*"))
                );
    }

    /**
     * API 정보를 설정하는 ApiInfo 객체를 생성합니다.
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Smart Ami Api")
//				.description("Project API 1.0")
//				.contact(new Contact("Cho", "http://www.test.kr/", "test@gmail.com"))
//				.version("0.0.1")
                .build();
    }

    /**
     * 지원하는 콘텐츠 유형을 설정하는 Set을 생성합니다.
     *
     * @return
     */
    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json");
        return produces;
    }
}