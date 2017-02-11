package org.bitbucket.eniqen.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Аспект для логирования всех паблик методов
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Aspect
public class LogingAspect extends AbstractAspect {

	@Around("publicMethods()")
	public Object loging(ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}
}

