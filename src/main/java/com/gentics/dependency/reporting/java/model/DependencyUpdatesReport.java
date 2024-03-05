package com.gentics.dependency.reporting.java.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO of the aggregate dependencies update report
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DependencyUpdatesReport {
	/**
	 * List of managed dependencies
	 */
	private List<DependencyInfo> dependencyManagements;

	/**
	 * List of dependencies
	 */
	private List<DependencyInfo> dependencies;

	/**
	 * Get the managed dependencies
	 * @return list of managed dependencies
	 */
	public List<DependencyInfo> getDependencyManagements() {
		if (dependencyManagements == null) {
			dependencyManagements = new ArrayList<>();
		}
		return dependencyManagements;
	}

	/**
	 * Set the managed dependencies
	 * @param dependencyManagements list of managed dependencies
	 * @return fluent API
	 */
	public DependencyUpdatesReport setDependencyManagements(List<DependencyInfo> dependencyManagements) {
		this.dependencyManagements = dependencyManagements;
		return this;
	}

	/**
	 * Get the dependencies
	 * @return list of dependencies
	 */
	public List<DependencyInfo> getDependencies() {
		if (dependencies == null) {
			dependencies = new ArrayList<>();
		}
		return dependencies;
	}

	/**
	 * Set the dependencies
	 * @param dependencies list of dependencies
	 * @return fluent API
	 */
	public DependencyUpdatesReport setDependencies(List<DependencyInfo> dependencies) {
		this.dependencies = dependencies;
		return this;
	}
}
