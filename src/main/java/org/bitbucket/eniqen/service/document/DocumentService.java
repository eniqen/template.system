package org.bitbucket.eniqen.service.document;

import org.bitbucket.eniqen.domain.Document;
import org.bitbucket.eniqen.domain.TemplateField;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Интерфейс сервиса документов
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public interface DocumentService {

	/**
	 * Поиск документа по идентификатору
	 *
	 * @param id идентификатор
	 * @return опциональный результат документа
	 */
	Optional<Document> find(final String id);

	/**
	 * Пейдженированный запрос на поиск всех сущностей документов
	 *
	 * @param pageSize колличество объектов на странице
	 * @param pageNo   номер страницы
	 * @return пейдженированный результат запроса
	 */
	Page<Document> findAll(int pageSize, int pageNo);

	/**
	 * Поиск всех документов
	 *
	 * @return список документов
	 */
	Collection<Document> findAll();

	/**
	 * Обновление Документа
	 *
	 * @param id          идентификатор по которому будем обвновлять
	 * @param name        техническое название документа
	 * @param description идентификатор документа
	 * @return обновленная запись документа
	 */
	Document update(final String id,
					final String name,
					final String description,
					final Map<TemplateField, String> templateFields);

	/**
	 * Создание поля по переданным параметрам
	 *
	 * @param name        техническое наименование документа
	 * @param description описание поля
	 * @return созданный объект Поля
	 */
	Document create(final String name,
					final String description,
					final Map<TemplateField, String> templateFields);

	/**
	 * Удаление документа по его идентификатору
	 *
	 * @param id идентификатор объекта
	 */
	void delete(final String id);
}
