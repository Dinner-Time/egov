package egovframework.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.egovframe.rte.psl.dataaccess.mapper.MapperConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @Class Name : ContextMapper.java
 * @Description : mybatis 연결 설정
 * @Modification Information
 * @
 * @  수정일        수정자      수정내용
 * @ -----------   ---------   -------------------------------
 * @ 2022-01-18     박태훈      최초 생성           
 *
 * @author 박태훈
 * @since 2022-01-18
 * @version 1.0
 * @see
 */
@Configuration
public class ContextMapper {

    /**
     * @author 박태훈
     */
    @Bean
    public SqlSessionFactoryBean sqlSession
    (
        DataSource dataSource
    ) throws IOException
    {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        factoryBean.setDataSource(dataSource);
        // mybatis 설정 파일 위치
        factoryBean.setConfigLocation(resolver.getResource("classpath:/egovframework/sqlmap/example/sql-mapper-config.xml"));
        // mybatis mapper 파일 위치
        factoryBean.setMapperLocations(resolver.getResources("classpath:/egovframework/sqlmap/example/mappers/*.xml"));
        return factoryBean;
    }

    @Bean
    public MapperConfigurer configurer
    (
        DataSource dataSource
    )
    {
        MapperConfigurer configurer = new MapperConfigurer();
        configurer.setBasePackage("egovframework.example.sample.service.impl");
        return configurer;
    }
}
