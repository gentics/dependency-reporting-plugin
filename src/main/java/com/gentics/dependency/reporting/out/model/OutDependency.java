package com.gentics.dependency.reporting.out.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO of a dependency
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutDependency implements Mergable<OutDependency>, Comparable<OutDependency> {
	/**
	 * Name
	 */
	private String name;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Current version
	 */
	private String current;

	/**
	 * Latest available version
	 */
	private String latest;

	/**
	 * Incremental update
	 */
	private String incremental;

	/**
	 * Minor update
	 */
	private String minor;

	/**
	 * Major update
	 */
	private String major;

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
	public OutDependency setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get the description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description
	 * @param description description
	 * @return fluent API
	 */
	public OutDependency setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get the current version
	 * @return current version
	 */
	public String getCurrent() {
		return current;
	}

	/**
	 * Set the current version
	 * @param current current version
	 * @return fluent API
	 */
	public OutDependency setCurrent(String current) {
		this.current = current;
		return this;
	}

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
	public OutDependency setLatest(String latest) {
		this.latest = latest;
		return this;
	}

	/**
	 * Get the incremental update
	 * @return incremental update
	 */
	public String getIncremental() {
		return incremental;
	}

	/**
	 * Set the incremental update
	 * @param incremental incremental update
	 * @return fluent API
	 */
	public OutDependency setIncremental(String incremental) {
		this.incremental = incremental;
		return this;
	}

	/**
	 * Get the minor update
	 * @return minor update
	 */
	public String getMinor() {
		return minor;
	}

	/**
	 * Set the minor update
	 * @param minor minor update
	 * @return fluent API
	 */
	public OutDependency setMinor(String minor) {
		this.minor = minor;
		return this;
	}

	/**
	 * Get the major update
	 * @return major update
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Set the major update
	 * @param major major update
	 * @return fluent API
	 */
	public OutDependency setMajor(String major) {
		this.major = major;
		return this;
	}

	@Override
	public OutDependency clone() {
		return new OutDependency()
				.setName(this.name)
				.setDescription(this.description)
				.setCurrent(this.current)
				.setLatest(this.latest)
				.setIncremental(this.incremental)
				.setMinor(this.minor)
				.setMajor(this.major);
	}

	@Override
	public boolean isMergable(OutDependency other) {
		return false;
	}

	@Override
	public void mergeIn(OutDependency other) {
	}

	@Override
	public int compareTo(OutDependency o) {
		if (o == null) {
			return 1;
		}
		return StringUtils.compareIgnoreCase(name, o.name);
	}
}
