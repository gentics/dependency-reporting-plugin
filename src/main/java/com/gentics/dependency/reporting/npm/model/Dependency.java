package com.gentics.dependency.reporting.npm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO for an npm dependency version
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dependency {
	/**
	 * Version
	 */
	private String version;

	/**
	 * Get the version
	 * @return version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Set the version
	 * @param version version
	 * @return fluent API
	 */
	public Dependency setVersion(String version) {
		this.version = version;
		return this;
	}
}
