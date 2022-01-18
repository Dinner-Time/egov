package egovframework.configuration;

import org.egovframe.rte.fdl.cmmn.aspect.ExceptionTransfer;
import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import org.egovframe.rte.fdl.cmmn.exception.manager.DefaultExceptionHandleManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.AntPathMatcher;

import egovframework.example.cmmn.EgovSampleExcepHndlr;
import egovframework.example.cmmn.EgovSampleOthersExcepHndlr;

/**
 * @Class Name : ContextAspect.java
 * @Description : 구현한 Service class 동작 도중 예외 발생 처리
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
@EnableAspectJAutoProxy
public class ContextAspect {
    
    /**
     * @author 박태훈
     */
    @Bean
    public EgovSampleExcepHndlr egovHandler()
    { //
        EgovSampleExcepHndlr egovSampleExcepHndlr = new EgovSampleExcepHndlr();
        return egovSampleExcepHndlr;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public EgovSampleOthersExcepHndlr otherHandler() 
    { //
        EgovSampleOthersExcepHndlr egovSampleOthersExcepHndlr = new EgovSampleOthersExcepHndlr();
        return egovSampleOthersExcepHndlr;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public DefaultExceptionHandleManager defaultExceptionHandleManager
    (
        AntPathMatcher pathMatcher, 
        EgovSampleExcepHndlr excepHndlr
    ){ //
        DefaultExceptionHandleManager handleManager = new DefaultExceptionHandleManager();
        handleManager.setReqExpMatcher(pathMatcher);
        handleManager.setPatterns(new String[]{"**service.impl.*"});
        handleManager.setHandlers(new ExceptionHandler[]{excepHndlr});
        return handleManager;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public DefaultExceptionHandleManager otherExceptionHandleManager
    (
        AntPathMatcher pathMatcher, 
        EgovSampleOthersExcepHndlr excepHndlr
    ){ //
        DefaultExceptionHandleManager handleManager = new DefaultExceptionHandleManager();
        handleManager.setReqExpMatcher(pathMatcher);
        handleManager.setPatterns(new String[]{"**service.impl.*"});
        handleManager.setHandlers(new ExceptionHandler[]{excepHndlr});
        return handleManager;
    }

    /**
     * @author 박태훈
     */
    @Bean
    public ExceptionTransfer exceptionTransfer
    (
        @Qualifier("defaultExceptionHandleManager") DefaultExceptionHandleManager dHandleManager,
        @Qualifier("otherExceptionHandleManager") DefaultExceptionHandleManager oHandleManager
    ){ //
        ExceptionTransfer transfer = new ExceptionTransfer();
        transfer.setExceptionHandlerService(new DefaultExceptionHandleManager [] {dHandleManager, oHandleManager});
        return transfer;
    }

    /**
     * @author 박태훈
     */
    public AopExceptionTransfer aopExceptionTransfer
    (
        ExceptionTransfer exceptionTransfer
    ){ //
        AopExceptionTransfer transfer = new AopExceptionTransfer();
        transfer.setExceptionTransfer(exceptionTransfer);
        return transfer;
    }
}
