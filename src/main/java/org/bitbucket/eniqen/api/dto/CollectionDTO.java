package org.bitbucket.eniqen.api.dto;

import java.util.Collection;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class CollectionDTO<T> {
	private Collection<T> items;
	private long count;

	public CollectionDTO(Collection<T> items, long count) {
		this.items = items;
		this.count = count;
	}

	public Collection<T> getItems() {
		return items;
	}

	public long getCount() {
		return count;
	}
}
