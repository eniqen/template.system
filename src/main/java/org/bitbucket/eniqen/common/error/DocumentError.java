package org.bitbucket.eniqen.common.error;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public enum DocumentError implements ErrorInfo {

	NOT_EXIST("Документ не найден", 3000),
	ID_REQUIRED("Идентификатор документа является обязательным параметром", 3001),
	NAME_REQUIRED("Название документа является обязательным параметром", 3002),
	TEMPLATE_REQUIRED("Документ не может быть создан без шаблона", 3002),
	INVALID_LINK_TEMPLATE("Документ не может быть привязан к разным шаблонам", 3003),
	TEMPLATE_NOT_EXIST_FIELDS("Документ не может быть создан, для шаблона в котором отсутствуют поля", 3004);

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
