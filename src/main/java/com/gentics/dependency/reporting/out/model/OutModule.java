package com.gentics.dependency.reporting.out.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gentics.dependency.reporting.MergeUtils;

/**
 * POJO of a module
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutModule implements Mergable<OutModule>, Comparable<OutModule> {
	/**
	 * Name
	 */
	private String name;

	/**
	 * Id - will be constructed from the name
	 */
	private String id;

	/**
	 * List of dependencies
	 */
	private List<OutDependency> dependencies;

	/**
	 * Get the name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name
	 * @param name name
	 * @return fluent API
	 */
	public OutModule setName(String name) {
		this.name = name;
		this.id = name.replaceAll("\\s", "_");
		return this;
	}

	/**
	 * Get the id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the list of dependencies
	 * @return list of dependencies
	 */
	public List<OutDependency> getDependencies() {
		return dependencies;
	}

	/**
	 * Set the list of dependencies
	 * @param dependencies list of dependencies
	 * @return fluent API
	 */
	public OutModule setDependencies(List<OutDependency> dependencies) {
		this.dependencies = dependencies;
		return this;
	}

	@Override
	public OutModule clone() {
		List<OutDependency> clonedDependencies = new ArrayList<>();
		this.dependencies.forEach(dep -> clonedDependencies.add(dep.clone()));

		return new OutModule()
				.setName(this.name)
				.setDependencies(clonedDependencies);
	}

	@Override
	public boolean isMergable(OutModule other) {
		return other != null && StringUtils.equals(name, other.name);
	}

	@Override
	public void mergeIn(OutModule other) {
		if (other != null) {
			this.dependencies = MergeUtils.mergeInto(other.dependencies, this.dependencies);
		}
	}

	/**
	 * Sort the entries (dependencies)
	 */
	public void sortEntries() {
		if (!CollectionUtils.isEmpty(dependencies)) {
			dependencies.sort(null);
		}
	}

	@Override
	public int compareTo(OutModule o) {
		if (o == null) {
			return 1;
		}
		return StringUtils.compareIgnoreCase(name, o.name);
	}
}
