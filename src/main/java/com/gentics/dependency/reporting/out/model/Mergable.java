package com.gentics.dependency.reporting.out.model;

import com.gentics.dependency.reporting.MergeUtils;

/**
 * Interface for instances, which can be merged by {@link MergeUtils#mergeInto(java.util.List, java.util.List)}.
 */
public interface Mergable<T> extends Cloneable {
	/**
	 * Merge the other instance into this instance
	 * @param other other instance to merge in
	 */
	void mergeIn(T other);

	/**
	 * Check whether the other instance can be merged into this instance
	 * @param other instance which may be merged into this instance
	 * @return true, when the other instance can be merged into this instance
	 */
	boolean isMergable(T other);

	/**
	 * Clone the instance
	 * @return clone
	 */
	T clone();
}
