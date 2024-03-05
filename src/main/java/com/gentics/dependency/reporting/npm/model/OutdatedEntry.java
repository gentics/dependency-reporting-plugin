package com.gentics.dependency.reporting.npm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO for an outdated dependency
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutdatedEntry {
	/**
	 * Latest available version
	 */
	private String latest;

	/**
	 * Get the latest available version
	 * @return latest version
	 */
	public String getLatest() {
		return latest;
	}

	/**
	 * Set the latest available version
	 * @param latest latest version
	 * @return fluent API
	 */
	public OutdatedEntry setLatest(String latest) {
		this.latest = latest;
		return this;
	}
}
