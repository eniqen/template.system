package org.bitbucket.eniqen.repositories;


import org.bitbucket.eniqen.models.Template;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Repository
public interface TemplateRepository extends BaseRepository<Template, String>, JpaSpecificationExecutor<Template> {
}
