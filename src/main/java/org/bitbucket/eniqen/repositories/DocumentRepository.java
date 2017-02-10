package org.bitbucket.eniqen.repositories;

import org.bitbucket.eniqen.models.Document;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Repository
public interface DocumentRepository extends BaseRepository<Document, String>, JpaSpecificationExecutor<Document> {
}
