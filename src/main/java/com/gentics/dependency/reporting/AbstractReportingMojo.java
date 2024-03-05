package com.gentics.dependency.reporting;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Base class for reporting Mojos
 */
public abstract class AbstractReportingMojo extends AbstractMojo {
	/**
	 * Directory where the output file(s) will be generated.
	 */
	@Parameter(required = true, defaultValue = "${project.build.directory}")
	private File outputDirectory;

	/**
	 * Skip plugin execution completely.
	 */
	@Parameter(defaultValue = "false")
	private boolean skip;

	/**
	 * ObjectMapper instance for handling of JSON
	 */
	private ObjectMapper objectMapper;

	/**
	 * XmlMapper instance for handling of XML
	 */
	private XmlMapper xmlMapper;

	/**
	 * Check whether execution shall be skipped
	 * @return true for skipping execution
	 */
	protected boolean isSkip() {
		return skip;
	}

	/**
	 * Get the configured output directory, which is created first if it does not exist
	 * @return output directory
	 * @throws MojoExecutionException when the output directory cannot be created
	 */
	protected File getOutputDirectory() throws MojoExecutionException {
		if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
			throw new MojoExecutionException(String.format("Failed to create output directory %s", outputDirectory.getAbsolutePath()));
		}
		return outputDirectory;
	}

	/**
	 * Get the {@link #objectMapper}, which is created first if it does not exist
	 * @return object mapper
	 */
	protected ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}

	/**
	 * Get the {@link #xmlMapper}, which is created first if it does not exist
	 * @return xml mapper
	 */
	protected XmlMapper getXmlMapper() {
		if (xmlMapper == null) {
			xmlMapper = new XmlMapper();
			xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}
		return xmlMapper;
	}
}
