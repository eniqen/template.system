package org.bitbucket.eniqen.common.exception;

import org.bitbucket.eniqen.common.error.ErrorInfo;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class EntityNotFoundException extends EntityException {


    public EntityNotFoundException(String message, int code) {
        super(message, code);
    }

    public EntityNotFoundException(ErrorInfo errorInfo) {
        super(errorInfo);
    }
}
