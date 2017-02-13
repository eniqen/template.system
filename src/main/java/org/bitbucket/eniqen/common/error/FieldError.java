package org.bitbucket.eniqen.common.error;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public enum FieldError implements ErrorInfo {
    FIELD_NOT_EXIST("Поле не найдено", 1000),
    ID_REQUARED("Идентификатор является обязательным параметром", 1001),
    FIELD_TYPE_REQUARED("Тип поля является обязательным параметром", 1002);

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
