package com.gentics.dependency.reporting;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gentics.dependency.reporting.out.model.OutReport;

/**
 * Renders a combined report from reports which are found in the input directory (including sub-directories).
 */
@Mojo(name = "render", defaultPhase = LifecyclePhase.PACKAGE, threadSafe = true)
public class RenderCombinedReportMojo extends AbstractReportingMojo {
	/**
	 * Directory, which is scanned for files with the given <code>inputName</code>.
	 * All input files in the directory (or sub-directories) will be merged into a
	 * single report, which will then be rendered into a file named
	 * <code>outputName</code> in the <code>outputDirectory</code>.
	 */
	@Parameter(required = true)
	private File inputDirectory;

	/**
	 * Name of the input files.
	 */
	@Parameter(required = true, defaultValue = "dependencies.json")
	private String inputName;

	/**
	 * Name of the velocity template files used for rendering the combined report.
	 */
	@Parameter(required = true)
	private File templateFile;

	/**
	 * Name of the output file.
	 */
	@Parameter(required = true, defaultValue = "index.html")
	private String outputName;

	/**
	 * Additional properties for rendering
	 */
	@Parameter(required = false)
	private Map<String, String> properties;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Log log = getLog();
		if (isSkip()) {
			log.info("Skipping the execution.");
			return;
		}

		// read all dependencies files, parse into POJO, merge POJOs and render
		ObjectMapper objectMapper = getObjectMapper();
		if (inputDirectory.isDirectory()) {
			List<File> inputFiles = new ArrayList<>();
			List<OutReport> reports = new ArrayList<>();

			try {
				Files.walk(inputDirectory.toPath()).filter(path -> path.endsWith(inputName)).forEach(path -> {
					log.info(String.format("Found %s", path.toString()));
					inputFiles.add(path.toFile());
				});

				for (File file : inputFiles) {
					reports.add(objectMapper.readValue(file, OutReport.class));
				}

				OutReport combined = new OutReport();
				reports.forEach(combined::mergeIn);

				combined.sortEntries();

				VelocityEngine renderEngine = new VelocityEngine();
				Context ctx = new VelocityContext();
				ctx.put("report", combined);
				if (!MapUtils.isEmpty(properties)) {
					for (Entry<String, String> entry : properties.entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue();

						if (!StringUtils.equals(key, "report")) {
							ctx.put(key, value);
						}
					}
				}
				File outFile = new File(getOutputDirectory(), outputName);
				try (FileReader templateReader = new FileReader(templateFile, Charset.forName("UTF-8"));
						Writer outWriter = new FileWriter(outFile, Charset.forName("UTF-8"))) {
					if (!renderEngine.evaluate(ctx, outWriter, "", templateReader)) {
						throw new MojoExecutionException("Rendering the combined report failed");
					}
				}
			} catch (IOException e) {
				throw new MojoExecutionException("", e);
			}
		}
	}
}
