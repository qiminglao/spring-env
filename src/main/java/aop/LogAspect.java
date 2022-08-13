package aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(public int bean.MathCalculator.*(..))")
    public void pointCut() {}

    @Before(value = "aop.LogAspect.pointCut()")
    public void logStart(JoinPoint joinPoint) {
        log.info("{}:@Before..参数列表是:{}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After(value = "aop.LogAspect.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        log.info("{}:@After..", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "aop.LogAspect.pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        log.info("{}:@AfterReturning..运行结果是:{}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "aop.LogAspect.pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.info("{}:@AfterThrowing..", joinPoint.getSignature().getName());
    }

    @Around(value = "aop.LogAspect.pointCut()")
    public Object logRound(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            log.info("{}:@Round before..", joinPoint.getSignature().getName());
            Object obj = joinPoint.proceed();
            log.info("{}:@Round afterReturning..", joinPoint.getSignature().getName());
            return obj;
        } catch (Throwable ex) {
            log.info("{}:@Round afterException..", joinPoint.getSignature().getName());
            throw ex;
        } finally {
            log.info("{}:@Round after..", joinPoint.getSignature().getName());
        }
    }
}
