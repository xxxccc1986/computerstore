package top.year21.computerstore.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 这是用于统计业务方法执行的事件的增强类
 * @date 2022/7/19 15:43
 */
@Aspect //将当前类标记为切面类，并生成代理对象，底层使用的是动态代理
@Component //将当前类对象的创建和管理交由spring容器维护
public class TimerAspect {


    //ProceedingJoinPoint接口表示指向目标方法的对象
    //切入点表达式解析
    // 第一个*表示不关注方法返回值
    //第二个* top.year21.computerstore.service.impl.* 表示包下的哪个实现类也不关注
    //第三个* 表示哪个方法名字也不关注
    //第四个(..)表示哪个方法中的参数列表也不关注
    //"execution(* top.year21.computerstore.service.*.*(..))"
    @Around("execution(* top.year21.computerstore.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //先记录业务执行前的时间
        long startedTime  = System.currentTimeMillis();

        Object result = pjp.proceed(); //调用目标方法，例如login方法
        //还可以在这个位置记录一下每个方法的执行名字和时间，并建议一张数据表记录
        //插入数据库

        //先记录业务执行前的时间
        long endTime  = System.currentTimeMillis();
        //计算耗时
        System.out.println("业务方法总共耗时：" + (endTime - startedTime));

        return result;
    }
}
