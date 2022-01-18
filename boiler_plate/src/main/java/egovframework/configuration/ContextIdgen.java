package egovframework.configuration;

import javax.sql.DataSource;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrStrategy;
import org.egovframe.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import org.egovframe.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextIdgen {
    
    @Bean(destroyMethod = "destroy")
    public EgovTableIdGnrServiceImpl egovIdGnrService
    (
        DataSource dataSource,
        EgovIdGnrStrategy strategy
    )
    {
        EgovTableIdGnrServiceImpl idGen = new EgovTableIdGnrServiceImpl();

        idGen.setDataSource(dataSource);
        idGen.setStrategy(strategy);
        idGen.setBlockSize(10);
        idGen.setTable("IDS");
        idGen.setTableName("SAMPLE");
        return idGen;
    }

    @Bean
    public EgovIdGnrStrategyImpl mixPrefixSample()
    {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();

        strategy.setPrefix("SAMPLE-");
        strategy.setCipers(5);
        strategy.setFillChar('0');
        return strategy;
    }
}
