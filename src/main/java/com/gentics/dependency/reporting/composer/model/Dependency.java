package com.gentics.dependency.reporting.composer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO of a single dependency
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dependency {
	/**
	 * Name of the dependency
	 */
	private String name;

	/**
	 * Version of the dependency
	 */
	private String version;

	/**
	 * Latest version of the dependency
	 */
	private String latest;

	/**
	 * Get the dependency name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the dependency name
	 * @param name dependency name
	 * @return fluent API
	 */
	public Dependency setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get the dependency version
	 * @return dependency version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Set the dependency version
	 * @param version dependency version
	 * @return fluent API
	 */
	public Dependency setVersion(String version) {
		this.version = version;
		return this;
	}

	/**
	 * Get the latest available dependency version
	 * @return latest dependency version
	 */
	public String getLatest() {
		return latest;
	}

	/**
	 * Set the latest available dependency version
	 * @param latest dependency version
	 * @return fluent API
	 */
	public Dependency setLatest(String latest) {
		this.latest = latest;
		return this;
	}
}
