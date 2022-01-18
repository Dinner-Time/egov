package egovframework.configuration;

import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace;
import org.egovframe.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.handler.TraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import org.egovframe.rte.fdl.cmmn.trace.manager.TraceHandlerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 * @Class Name : ContextCommon.java
 * @Description : context-common.xml에 구현되어 있던 aop 설정
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
@ComponentScan // : component 어노테이션이 붙은 class를 bean에 자동으로 등록한다.
(
    // 기준 패키지
    basePackages = {"egovframework", "kr"},
    // bean에 등록할 어노테이션 설정
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class)
    },
    // bean등록에서 제외할 어노테이션 설정
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
    }
)
public class ContextCommon {
    
    /**
     * @author 박태훈
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames(new String[]
        {
            "classpath:/egovframework/message/message-common",
            "classpath:/org/egovframe/rte/fdl/idgnr/messages/idgnr",
            "classpath:/org/egovframe/rte/fdl/property/messages/properties"
        });
        source.setCacheSeconds(60);
        return source;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public LeaveaTrace leaveaTrace
    (
        DefaultTraceHandleManager defaultTraceHandleManager
    )
    {
        LeaveaTrace trace = new LeaveaTrace();
        trace.setTraceHandlerServices(new TraceHandlerService[]{defaultTraceHandleManager});
        return trace;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public DefaultTraceHandleManager traceHandleManager
    (
        AntPathMatcher pathMatcher,
        DefaultTraceHandler defaultTraceHandler
    )
    {
        DefaultTraceHandleManager manager = new DefaultTraceHandleManager();
        manager.setReqExpMatcher(pathMatcher);
        manager.setPatterns(new String[]{"*"});
        manager.setHandlers(new TraceHandler[]{defaultTraceHandler});
        return manager;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public AntPathMatcher antPathMatcher(){
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public DefaultTraceHandler defaultTraceHandler(){
        DefaultTraceHandler handler = new DefaultTraceHandler();
        return handler;
    }
}
