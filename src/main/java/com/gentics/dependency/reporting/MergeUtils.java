package com.gentics.dependency.reporting;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;

import com.gentics.dependency.reporting.out.model.Mergable;

/**
 * Merge utilities class
 */
public class MergeUtils {
	/**
	 * Merge the given list of {@link Mergable}s into the given target list
	 * @param <T> type of Mergables
	 * @param toMerge list of {@link Mergable}s to merge
	 * @param mergeTarget list of {@link Mergable}s where to merge into. If this is null, a new (empty) list will be created
	 * @return possibly modified mergeTarget list
	 */
	public final static <T extends Mergable<T>> List<T> mergeInto(List<T> toMerge, List<T> mergeTarget) {
		if (CollectionUtils.isEmpty(toMerge)) {
			return mergeTarget;
		}

		if (mergeTarget == null) {
			mergeTarget = new ArrayList<>();
		} else {
			mergeTarget = new ArrayList<>(mergeTarget);
		}

		for (T other : toMerge) {
			T toMergeWith = IterableUtils.find(mergeTarget, i -> i.isMergable(other));

			if (toMergeWith != null) {
				toMergeWith.mergeIn(other);
			} else {
				mergeTarget.add(other.clone());
			}
		}

		return mergeTarget;
	}
}
