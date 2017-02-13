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

	Template create(String name,
					String description,
					Optional<Set<TemplateField>> templateFields);

	Template update(String id,
					String name,
					String description,
					Optional<Set<TemplateField>> templateFields);

	Page<Template> findAll(int pageNum, int pageSize);

	Collection<Template> findAll();

	Optional<Template> find(String id);

	void delete(String id);
}
