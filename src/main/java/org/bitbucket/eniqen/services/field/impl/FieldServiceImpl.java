package org.bitbucket.eniqen.services.field.impl;

import org.bitbucket.eniqen.models.Field;
import org.bitbucket.eniqen.models.FieldType;
import org.bitbucket.eniqen.repositories.FieldRepository;
import org.bitbucket.eniqen.services.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Service
@Transactional(readOnly = true)
public class FieldServiceImpl implements FieldService {

	@Autowired
	private FieldRepository fieldRepository;

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
