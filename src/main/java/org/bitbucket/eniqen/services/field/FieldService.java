package org.bitbucket.eniqen.services.field;

import org.bitbucket.eniqen.models.Field;
import org.bitbucket.eniqen.models.FieldType;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервис для работы с полями
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Transactional(readOnly = true)
public interface FieldService {

	/**
	 * Поиск Поля по ключу
	 *
	 * @param id идентификатор
	 * @return опциональный результат Поля
	 */
	Optional<Field> find(final String id);

	/**
	 * Пейдженированный запрос на поиск всех сущностей Полей
	 *
	 * @param pageSize колличество объектов на странице
	 * @param pageNo   номер страницы
	 * @return пейдженированный результат запроса
	 */
	Page<Field> findAll(int pageSize, int pageNo);

	/**
	 * Обновление Поля
	 *
	 * @param id          идентификатор по которому будем обвновлять
	 * @param type        тип Поля
	 * @param name        техническое название поля
	 * @param description идентификатор поля
	 * @return обновленная запись Поля
	 */
	Field update(final String id,
				 final FieldType type,
				 final String name,
				 final String description);

	/**
	 * Создание поля по переданным параметрам
	 *
	 * @param type        Тип поля
	 * @param name        техническое наименование поля
	 * @param description описание поля
	 * @return созданный объект Поля
	 */
	Field create(final FieldType type,
				 final String name,
				 final String description);

	/**
	 * Удаление поля по его идентификатору
	 *
	 * @param id идентификатор объекта
	 */
	void delete(final String id);
}
