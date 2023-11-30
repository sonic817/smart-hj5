package kr.smart.hj5.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * SqlSessionFactory 빈을 설정하는 메서드
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/**/*.xml")); // MyBatis 매퍼 파일의 위치 설정
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.smart.hj5.*"); // DTO 클래스가 있는 패키지 설정
        sqlSessionFactoryBean.setConfiguration(mybatisConfig());

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * MyBatis의 Config 커스터마이징
     *
     * @return
     */
    private org.apache.ibatis.session.Configuration mybatisConfig() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true); // 데이터베이스 컬럼명의 스네이크 케이스를 자바 필드의 카멜 케이스로 매핑
        return configuration;
    }
}