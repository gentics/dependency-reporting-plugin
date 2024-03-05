package com.gentics.dependency.reporting.npm.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO for the npm outdated report
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Outdated extends HashMap<String, OutdatedEntry> {

	private static final long serialVersionUID = 8222866150810886714L;

}
