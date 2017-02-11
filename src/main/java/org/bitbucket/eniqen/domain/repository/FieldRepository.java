package org.bitbucket.eniqen.domain.repository;

import org.bitbucket.eniqen.domain.Field;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Repository
public interface FieldRepository extends BaseRepository<Field, String>, JpaSpecificationExecutor<Field> {
}
