package org.bitbucket.eniqen.service.field.impl;

import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.bitbucket.eniqen.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
	public Field update(String id, FieldType type, String name, String description) {
		Field field = fieldRepository.findOne(id).orElseThrow(IllegalArgumentException::new);
		field.setName(name);
		field.setType(type);
		field.setDescription(description);

		return fieldRepository.save(field);
	}

	@Override
	public Field create(FieldType type, String name, String description) {
		return fieldRepository.save(new Field(type, description, name));
	}

	@Override
	public void delete(String id) {
		fieldRepository.delete(id);
	}
}
