package org.bitbucket.eniqen.service.template;

import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Сервис для работы с шаблонами
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public interface TemplateService {

	/**
	 * @param name           название шаблона
	 * @param description    описание шаблона
	 * @param templateFields связь шаблона с полями
	 * @return созданый шаблон
	 */
	Template create(String name,
					String description,
					Set<TemplateField> templateFields);

	/**
	 * Метод обновления шаблона
	 *
	 * @param id             идентификатор шаблона
	 * @param name           название шаблона
	 * @param description    описание шаблона
	 * @param templateFields связь шаблонов с полями
	 * @return обновленный шаблон
	 */
	Template update(String id,
					String name,
					String description,
					Set<TemplateField> templateFields);

	/**
	 * Получения постраничного результата
	 *
	 * @param pageNum  номер страницы
	 * @param pageSize колличество данных
	 * @return результат выборки
	 */
	Page<Template> findAll(int pageNum, int pageSize);

	/**
	 * Получение списка шаблонов
	 *
	 * @return список шаблонов
	 */
	Collection<Template> findAll();

	/**
	 * Поиск шаблона по его идентификатору
	 *
	 * @param id идентификатор документа
	 * @return опциональный результат
	 */
	Optional<Template> find(String id);

	/**
	 * Удаление докумена по его идентификатору
	 *
	 * @param id идентификатор документа
	 */
	void delete(String id);
}
