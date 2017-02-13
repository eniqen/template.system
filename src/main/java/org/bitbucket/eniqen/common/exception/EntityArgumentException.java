package org.bitbucket.eniqen.common.exception;

import org.bitbucket.eniqen.common.error.ErrorInfo;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class EntityArgumentException extends EntityException {

	public EntityArgumentException(String message, int code) {
		super(message, code);
	}

	public EntityArgumentException(ErrorInfo errorInfo) {
		super(errorInfo);
	}
}
