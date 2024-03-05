package com.gentics.dependency.reporting;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;

import com.gentics.dependency.reporting.out.model.OutComponent;
import com.gentics.dependency.reporting.out.model.OutDependency;
import com.gentics.dependency.reporting.out.model.OutModule;
import com.gentics.dependency.reporting.out.model.OutReport;

/**
 * Abstract base class for output Mojos
 */
public abstract class AbstractOutputMojo extends AbstractReportingMojo {
	/**
	 * Name of the generated file.
	 */
	@Parameter(required = true, defaultValue = "dependencies.json")
	private String outputName;

	/**
	 * Name of the component, for which the dependencies are reported.
	 */
	@Parameter(required = true)
	private String componentName;

	/**
	 * Name of the module, for which the dependencies are reported.
	 */
	@Parameter(required = true)
	private String moduleName;

	/**
	 * Optional file containing a static report (in the common format), which is merged into the generated report
	 */
	@Parameter
	private File staticInput;

	/**
	 * Optional list of regular expressions of dependency names, which must be ignored.
	 */
	@Parameter
	private List<String> ignoredNames;

	/**
	 * List of {@link Pattern}s which were compiled from the {@link #ignoredNames}.
	 */
	private List<Pattern> ignoreNamesPatterns;

	/**
	 * Output the dependencies to the configured file
	 * @param dependencies list of dependencies
	 * @throws MojoExecutionException
	 */
	protected void outputDependencies(List<OutDependency> dependencies) throws MojoExecutionException {
		getLog().info(String.format("Writing dependencies report for component %s, module %s containing %d dependencies", componentName, moduleName, dependencies.size()));
		OutReport output = new OutReport().setComponents(Arrays.asList(new OutComponent().setName(componentName)
				.setModules(Arrays.asList(new OutModule().setName(moduleName).setDependencies(dependencies)))));

		// if a static input was configured, we will load it and merge it into the report
		if (staticInput != null) {
			OutReport staticReport = readFromJsonFile(staticInput, OutReport.class);
			output.mergeIn(staticReport);
		}

		File outputFile = new File(getOutputDirectory(), outputName);
		try {
			getObjectMapper().writerWithDefaultPrettyPrinter().writeValue(outputFile, output);
		} catch (IOException e) {
			throw new MojoExecutionException(String.format("Failed to write output to %s", outputFile.getAbsolutePath()), e);
		}
	}

	/**
	 * Read the file, which is supposed to contain JSON data and parse into an instance of the valueType
	 * @param <T> type of the value type class
	 * @param file file containing JSON data
	 * @param valueType value type class
	 * @return parsed data
	 * @throws MojoExecutionException
	 */
	protected <T> T readFromJsonFile(File file, Class<T> valueType) throws MojoExecutionException {
		try {
			return getObjectMapper().readValue(file, valueType);
		} catch (IOException e) {
			throw new MojoExecutionException(String.format("Error while parsing %s", file.getAbsolutePath()), e);
		}
	}

	/**
	 * Read the file, which is supposed to contain XML data and parse into an instance of the valueType
	 * @param <T> type of the value type class
	 * @param file file containing XML data
	 * @param valueType value type class
	 * @return parsed data
	 * @throws MojoExecutionException
	 */
	protected <T> T readFromXmlFile(File file, Class<T> valueType) throws MojoExecutionException {
		try {
			return getXmlMapper().readValue(file, valueType);
		} catch (IOException e) {
			throw new MojoExecutionException(String.format("Error while parsing %s", file.getAbsolutePath()), e);
		}
	}

	/**
	 * Check whether the dependency with the given name should be ignored
	 * @param name dependency name
	 * @return true for ignoring
	 */
	protected boolean ignoreDependency(String name) {
		if (CollectionUtils.isEmpty(ignoredNames)) {
			return false;
		}
		if (CollectionUtils.isEmpty(ignoreNamesPatterns)) {
			ignoreNamesPatterns = ignoredNames.stream().map(regex -> Pattern.compile(regex)).collect(Collectors.toList());
		}

		for (Pattern pattern : ignoreNamesPatterns) {
			if (pattern.matcher(name).matches()) {
				return true;
			}
		}
		return false;
	}
}
