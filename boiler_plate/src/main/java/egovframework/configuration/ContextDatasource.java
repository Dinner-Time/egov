package egovframework.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @Class Name : ContextDatasource.java
 * @Description : jdbc 연결 설정
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
public class ContextDatasource {
    
    /**
     * @author 박태훈
     */
    // @Bean(destroyMethod = "close")
    // public DataSource dataSource()
    // {
    //     BasicDataSource basicDataSource = new BasicDataSource();
    //     basicDataSource.setDriverClassName("");
    //     basicDataSource.setUrl("");
    //     basicDataSource.setUsername("");
    //     basicDataSource.setPassword("");
    //     return basicDataSource;
    // }

    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.HSQL)
                    .addScript("classpath:db/sampledb.sql")
                    .build();
    }
}
