package com.gentics.dependency.reporting.java.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO for a single dependency
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DependencyInfo {
	/**
	 * Group ID
	 */
	private String groupId;

	/**
	 * Artifact ID
	 */
	private String artifactId;

	/**
	 * Dependency scope
	 */
	private String scope;

	/**
	 * Currently used version
	 */
	private String currentVersion;

	/**
	 * Latest available version
	 */
	private String lastVersion;

	/**
	 * List of available incremental updates
	 */
	private List<String> incrementals;

	/**
	 * List of available minor updates
	 */
	private List<String> minors;

	/**
	 * List of available major updates
	 */
	private List<String> majors;

	/**
	 * Get the full name of the dependency, which is created of [groupId]:[artifactId]
	 * @param info dependency
	 * @return full name
	 */
	public final static String getFullName(DependencyInfo info) {
		return String.format("%s:%s", info.getGroupId(), info.getArtifactId());
	}

	/**
	 * Get the groupId
	 * @return groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Set the groupId
	 * @param groupId groupId
	 * @return fluent API
	 */
	public DependencyInfo setGroupId(String groupId) {
		this.groupId = groupId;
		return this;
	}

	/**
	 * Get the artifactId
	 * @return artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * Set the artifactId
	 * @param artifactId artifactId
	 * @return fluent API
	 */
	public DependencyInfo setArtifactId(String artifactId) {
		this.artifactId = artifactId;
		return this;
	}

	/**
	 * Get the scope
	 * @return scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * Set the scope
	 * @param scope scope
	 * @return fluent API
	 */
	public DependencyInfo setScope(String scope) {
		this.scope = scope;
		return this;
	}

	/**
	 * Get the currently used version
	 * @return current version
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * Set the currently used version
	 * @param currentVersion current version
	 * @return fluent API
	 */
	public DependencyInfo setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}

	/**
	 * Get the latest available version
	 * @return latest version
	 */
	public String getLastVersion() {
		return lastVersion;
	}

	/**
	 * Set the latest available version
	 * @param lastVersion latest version
	 * @return fluent API
	 */
	public DependencyInfo setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion;
		return this;
	}

	/**
	 * Get the list of available incremental updates
	 * @return list of versions
	 */
	public List<String> getIncrementals() {
		return incrementals;
	}

	/**
	 * Set the list of incremental updates
	 * @param incrementals list of versions
	 * @return fluent API
	 */
	public DependencyInfo setIncrementals(List<String> incrementals) {
		this.incrementals = incrementals;
		return this;
	}

	/**
	 * Get the list of availabe minor updates
	 * @return list of versions
	 */
	public List<String> getMinors() {
		return minors;
	}

	/**
	 * Set the list of available minor updates
	 * @param minors list of versions
	 * @return fluent API
	 */
	public DependencyInfo setMinors(List<String> minors) {
		this.minors = minors;
		return this;
	}

	/**
	 * Get the list of available major updates
	 * @return list of versions
	 */
	public List<String> getMajors() {
		return majors;
	}

	/**
	 * Set the list of available major updates
	 * @param majors list of versions
	 * @return fluent API
	 */
	public DependencyInfo setMajors(List<String> majors) {
		this.majors = majors;
		return this;
	}
}
