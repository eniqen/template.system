package org.bitbucket.eniqen.domain;

import lombok.Builder;

import javax.persistence.MappedSuperclass;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@MappedSuperclass
public abstract class BaseNamedDictionaryEntry extends BaseEntity {

    private String name;

    private String description;

    public BaseNamedDictionaryEntry(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BaseNamedDictionaryEntry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
