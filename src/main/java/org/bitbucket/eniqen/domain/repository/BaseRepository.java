package org.bitbucket.eniqen.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    Page<T> findAll(Pageable pageable);

    List<T> findAll();

    Optional<T> findOne(ID id);

    T save(T entity);

    void delete(ID id);
}
