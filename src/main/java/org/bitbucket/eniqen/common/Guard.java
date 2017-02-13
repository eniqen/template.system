package org.bitbucket.eniqen.common;

import org.bitbucket.eniqen.common.error.ErrorInfo;
import org.bitbucket.eniqen.common.exception.EntityArgumentException;
import org.bitbucket.eniqen.common.exception.EntityException;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public enum Guard {
	CHECK_ARGUMENT {
		@Override
		public <T> void check(T argument, ErrorInfo errorInfo) throws EntityException {
			if (checkArgumentPredicate.test(argument))
				throw new EntityArgumentException(errorInfo);
		}
	},
	CHECK_STRING {
		@Override
		public <T> void check(T argument, ErrorInfo errorInfo) throws EntityException {
			if (argument instanceof String
				&& checkStringPredicate.test((String) argument))
				throw new EntityArgumentException(errorInfo);
		}
	},
	CHECK_COLLECTION {
		@Override
		public <T> void check(T argument, ErrorInfo errorInfo) throws EntityException {
			if (argument instanceof Collection
				&& checkCollectionPredicate.test((Collection) argument))
				throw new EntityArgumentException(errorInfo);
		}
	},
	CHECK_CONDITION {
		@Override
		public <T> void check(T argument, ErrorInfo errorInfo) throws EntityException {
			if (argument instanceof Boolean
				&& !checkConditionPredicate.test((Boolean) argument))
				throw new EntityArgumentException(errorInfo);
		}
	};

	public abstract <T> void check(T argument, ErrorInfo errorInfo) throws EntityException;

	private static Predicate<Object> checkArgumentPredicate = argument -> argument == null;

	private static Predicate<String> checkStringPredicate = line -> line == null || line.trim().isEmpty();

	private static Predicate<Boolean> checkConditionPredicate = Boolean::booleanValue;

	private static Predicate<Collection> checkCollectionPredicate = collection -> collection == null || collection.isEmpty();
}
