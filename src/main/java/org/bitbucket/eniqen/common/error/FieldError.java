package org.bitbucket.eniqen.common.error;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public enum FieldError implements ErrorInfo {

	NOT_EXIST("Поле не найдено", 1000),
	ID_REQUIRED("Идентификатор является обязательным параметром", 1001),
	TYPE_REQUIRED("Тип поля является обязательным параметром", 1002),
	NAME_REQUIRED("Имя поля является обязательным параметром", 1003);

	FieldError(String status, int code) {
		this.code = code;
		this.status = status;
	}

	private final int code;
	private final String status;

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getStatus() {
		return status;
	}
}
