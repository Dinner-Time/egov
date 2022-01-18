package egovframework.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Class Name : ContextTransaction.java
 * @Description : transaction 설정
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
@EnableTransactionManagement
public class ContextTransaction {
    
    /**
     * @author 박태훈
     */
    @Bean
    public DataSourceTransactionManager transactionManager
    (
        DataSource dataSource
    )
    {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }
}
