package org.bitbucket.eniqen.common.exeption;

import org.bitbucket.eniqen.common.error.ErrorInfo;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class EntityNotFoundExeption extends RuntimeException {

    private final ErrorInfo errorInfo;

    public EntityNotFoundExeption(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
