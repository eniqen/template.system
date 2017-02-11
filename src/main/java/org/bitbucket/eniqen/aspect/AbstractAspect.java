package org.bitbucket.eniqen.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Аюстрактный класс для всех аспектов
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Aspect
public abstract class AbstractAspect {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(public * *(..))")
	protected void publicMethods() {
	}
}
