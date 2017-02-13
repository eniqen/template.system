package org.bitbucket.eniqen.common.exeption;

import org.bitbucket.eniqen.common.error.ErrorInfo;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class EntityArgumentExeption extends RuntimeException {

    private final ErrorInfo errorInfo;

    public EntityArgumentExeption(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
