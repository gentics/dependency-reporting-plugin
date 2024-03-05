package com.gentics.dependency.reporting.out.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gentics.dependency.reporting.MergeUtils;

/**
 * POJO of a component
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutComponent implements Mergable<OutComponent>, Comparable<OutComponent> {
	/**
	 * Component name
	 */
	private String name;

	/**
	 * Id - will be constructed from the name
	 */
	private String id;

	/**
	 * List of modules in the component
	 */
	private List<OutModule> modules;

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
	public OutComponent setName(String name) {
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
	 * Get the list of modules
	 * @return list of modules
	 */
	public List<OutModule> getModules() {
		return modules;
	}

	/**
	 * Set the list of modules
	 * @param modules list of modules
	 * @return fluent API
	 */
	public OutComponent setModules(List<OutModule> modules) {
		this.modules = modules;
		return this;
	}

	@Override
	public OutComponent clone() {
		List<OutModule> clonedModules = new ArrayList<>();
		this.modules.forEach(mod -> clonedModules.add(mod.clone()));

		return new OutComponent()
				.setName(this.name)
				.setModules(clonedModules);
	}

	@Override
	public boolean isMergable(OutComponent other) {
		return other != null && StringUtils.equals(name, other.name);
	}

	@Override
	public void mergeIn(OutComponent other) {
		if (other != null) {
			this.modules = MergeUtils.mergeInto(other.modules, this.modules);
		}
	}

	/**
	 * Sort the entries (modules)
	 */
	public void sortEntries() {
		if (!CollectionUtils.isEmpty(modules)) {
			modules.sort(null);
			modules.forEach(OutModule::sortEntries);
		}
	}

	@Override
	public int compareTo(OutComponent o) {
		if (o == null) {
			return 1;
		}
		return StringUtils.compareIgnoreCase(name, o.name);
	}
}
