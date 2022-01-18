package egovframework.configuration;

import javax.sql.DataSource;

import org.egovframe.rte.psl.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @Class Name : ContextSqlMap.java
 * @Description : ibatis 연결 설정
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
public class ContextSqlMap {
    
	@Bean
	public SqlMapClientFactoryBean sqlMapClient(DataSource dataSource){
		SqlMapClientFactoryBean smcfb = new SqlMapClientFactoryBean();
		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
		
		smcfb.setConfigLocation(pmrpr.getResource("classpath:/egovframework/sqlmap/example/sql-map-config.xml"));
		smcfb.setDataSource(dataSource);
		
		return smcfb;
	}
}
