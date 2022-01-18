package egovframework.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.egovframe.rte.fdl.cmmn.aspect.ExceptionTransfer;

/**
 * @Class Name : AopExceptionTransfer.java
 * @Description : context-aspect.xml에 구현되어 있던 aop 설정
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
@Aspect
public class AopExceptionTransfer {
    
    private ExceptionTransfer exceptionTransfer;

    /**
     * @author 박태훈
     */
    public void setExceptionTransfer(ExceptionTransfer exceptionTransfer) {
        this.exceptionTransfer = exceptionTransfer;
    }

    /**
     * @author 박태훈
     */
    @Pointcut("execution(* egovframework.example..impl.*Impl.*(..))")
    private void exceptionTransferService() {}

    /**
     * @author 박태훈
     */
    @AfterThrowing(pointcut = "exceptionTransferService()", throwing = "ex")
    public void doAfterThrowingExceptionTransferService
    (
        JoinPoint joinPoint,
        Exception exception
    ) throws Exception //
    {
        exceptionTransfer.transfer(joinPoint, exception);
    }
}
