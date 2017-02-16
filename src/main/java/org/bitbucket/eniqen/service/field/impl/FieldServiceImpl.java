package org.bitbucket.eniqen.service.field.impl;

import org.bitbucket.eniqen.common.exception.EntityNotFoundException;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.bitbucket.eniqen.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static org.bitbucket.eniqen.common.Guard.CHECK_ARGUMENT;
import static org.bitbucket.eniqen.common.Guard.CHECK_STRING;
import static org.bitbucket.eniqen.common.error.FieldError.*;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

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
	public Collection<Field> findAll() {
		return fieldRepository.findAll();
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Field update(String id, FieldType type, String name, String description) {

		CHECK_STRING.check(id, ID_REQUIRED);
		CHECK_ARGUMENT.check(type, TYPE_REQUIRED);
		CHECK_STRING.check(name, NAME_REQUIRED);

		Field field = fieldRepository.findOne(id)
									 .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		field.setType(type);
		field.setDescription(description);
		field.setName(name);

		return fieldRepository.save(field);
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Field create(FieldType type, String name, String description) {

		CHECK_ARGUMENT.check(type, TYPE_REQUIRED);
		CHECK_STRING.check(name, NAME_REQUIRED);

		return fieldRepository.save(new Field(type, name, description));
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public void delete(String id) {

		CHECK_STRING.check(id, ID_REQUIRED);

		Field field = this.find(id)
						  .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		fieldRepository.delete(field.getId());
	}
}
