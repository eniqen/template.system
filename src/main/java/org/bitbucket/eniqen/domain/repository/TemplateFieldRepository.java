package org.bitbucket.eniqen.domain.repository;

import org.bitbucket.eniqen.domain.TemplateField;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Repository
public interface TemplateFieldRepository extends BaseRepository<TemplateField, Long> {

	Optional<TemplateField> findByTemplateIdAndFieldId(String templateId, String fieldId);
}