package org.bitbucket.eniqen.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@MappedSuperclass
public abstract class BaseAuditEntity extends BaseNamedDictionaryEntry {

    @PrePersist
    public void prePersist() {

    }

    @PreUpdate
    public void preUpdate() {

    }
}
