package org.bitbucket.eniqen.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO<T> {
	private Collection<T> items;
	private long count;
}
