package org.bitbucket.eniqen.common.error;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public enum TemplateError implements ErrorInfo {

	NOT_EXIST("Шаблон не найден", 2000),
	ID_REQUIRED("Идентификатор является обязательным параметром", 2001),
	NAME_REQUIRED("Имя шаблона является обязательным параметром", 2002);

	private final String status;
	private final int code;

	TemplateError(String status, int code) {
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
