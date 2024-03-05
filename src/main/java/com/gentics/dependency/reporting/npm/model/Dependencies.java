package com.gentics.dependency.reporting.npm.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO of npm depencencies
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dependencies {
	/**
	 * Map of dependencies per name
	 */
	private Map<String, Dependency> dependencies;

	/**
	 * Get the dependencies map
	 * @return dependencies map
	 */
	public Map<String, Dependency> getDependencies() {
		return dependencies;
	}

	/**
	 * Set the dependencies map
	 * @param dependencies dependencies map
	 * @return fluent API
	 */
	public Dependencies setDependencies(Map<String, Dependency> dependencies) {
		this.dependencies = dependencies;
		return this;
	}
}
