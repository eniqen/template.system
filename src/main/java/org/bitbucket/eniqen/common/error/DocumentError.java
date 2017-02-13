package org.bitbucket.eniqen.common.error;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public enum DocumentError implements ErrorInfo {

	NOT_EXIST("Документ не найден", 3000),
	ID_REQUIRED("Идентификатор является обязательным параметром", 3001);

	private final String status;
	private final int code;

	DocumentError(String status, int code) {
		this.status = status;
		this.code = code;
	}

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getStatus() {
		return this.status;
	}
}
