package org.bitbucket.eniqen.common.exception;

import org.bitbucket.eniqen.common.error.ErrorInfo;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public abstract class EntityException extends RuntimeException {
	private final int code;

	public EntityException(String message, int code) {
		super(message);
		this.code = code;
	}

	public EntityException(ErrorInfo errorInfo) {
		super(errorInfo.getStatus());
		this.code = errorInfo.getCode();
	}
}
