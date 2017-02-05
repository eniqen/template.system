package org.bitbucket.eniqen.models;

import javax.persistence.MappedSuperclass;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@MappedSuperclass
public abstract class BaseNamedDictionaryEntry extends BaseEntity {

    private String name;
}
