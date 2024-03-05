package com.gentics.dependency.reporting.out.model;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gentics.dependency.reporting.MergeUtils;

/**
 * POJO of a report
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutReport implements Mergable<OutReport> {
	/**
	 * List of components
	 */
	private List<OutComponent> components;

	/**
	 * Get the list of components
	 * @return list of components
	 */
	public List<OutComponent> getComponents() {
		return components;
	}

	/**
	 * Set the list of components
	 * @param components components
	 * @return fluent API
	 */
	public OutReport setComponents(List<OutComponent> components) {
		this.components = components;
		return this;
	}

	@Override
	public void mergeIn(OutReport other) {
		if (other != null) {
			this.components = MergeUtils.mergeInto(other.components, this.components);
		}
	}

	@Override
	public boolean isMergable(OutReport other) {
		return true;
	}

	@Override
	public OutReport clone() {
		return this;
	}

	/**
	 * Sort the entries (components)
	 */
	public void sortEntries() {
		if (!CollectionUtils.isEmpty(components)) {
			components.sort(null);
			components.forEach(OutComponent::sortEntries);
		}
	}
}
