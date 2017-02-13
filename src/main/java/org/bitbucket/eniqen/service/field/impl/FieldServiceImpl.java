package org.bitbucket.eniqen.service.field.impl;

import org.bitbucket.eniqen.common.error.FieldError;
import org.bitbucket.eniqen.common.exeption.EntityNotFoundExeption;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.bitbucket.eniqen.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.bitbucket.eniqen.common.error.FieldError.FIELD_NOT_EXIST;

/**
 * Реализация сервиса для работы с Полями
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Service
@Transactional(readOnly = true)
public class FieldServiceImpl implements FieldService {

	private final FieldRepository fieldRepository;

	@Autowired
	public FieldServiceImpl(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

	@Override
	public Optional<Field> find(String id) {
		return fieldRepository.findOne(id);
	}

	@Override
	public Page<Field> findAll(int pageSize, int pageNo) {
		return fieldRepository.findAll(new PageRequest(pageNo, pageSize));
	}

	@Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Field update(String id, FieldType type, String name, String description) {
        checkArgument(id != null, FieldError.ID_REQUARED.getStatus());

        Field field = fieldRepository.findOne(id).orElseThrow(() -> new EntityNotFoundExeption(FIELD_NOT_EXIST));
        field.setType(type);
        field.setDescription(description);
        field.setName(name);

        return fieldRepository.save(field);
    }

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Field create(FieldType type, String name, String description) {
		return fieldRepository.save(Field.builder()
                .name(name)
                .description(description)
                .type(type)
                .build());
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void delete(String id) {
        final Field field = this.find(id).orElseThrow(() -> new EntityNotFoundExeption(FIELD_NOT_EXIST));
        fieldRepository.delete(field.getId());
	}
}
