package com.gentics.dependency.reporting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.gentics.dependency.reporting.npm.model.Dependencies;
import com.gentics.dependency.reporting.npm.model.Dependency;
import com.gentics.dependency.reporting.npm.model.Outdated;
import com.gentics.dependency.reporting.npm.model.OutdatedEntry;
import com.gentics.dependency.reporting.out.model.OutDependency;

/**
 * Read the <code>dependenciesFile</code> and the <code>outdatedFile</code> and transform the contained information into the common report format, which is written into a file named <code>outputName</code> in the <code>outputDirectory</code>.
 * The <code>dependenciesFile</code> should be in JSON format and be generated with the command <code>npm list --prod --json</code>.
 * The <code>outdatedFile</code> should also be in JSON format and be generated with the command <code>npm outdated --json --long</code>.
 */
@Mojo(name = "npm-transform", defaultPhase = LifecyclePhase.PACKAGE, threadSafe = true)
public class TransformNpmReportMojo extends AbstractOutputMojo {
	/**
	 * Dependencies file in JSON format, which was generated with the command <code>npm list --prod --json</code>.
	 */
	@Parameter(required = true)
	private File dependenciesFile;

	/**
	 * Outdated file in JSON format, which was generated with the command <code>npm outdated --json --long</code>.
	 */
	@Parameter(required = true)
	private File outdatedFile;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Log log = getLog();
		if (isSkip()) {
			log.info("Skipping the execution.");
			return;
		}

		log.info(String.format("Reading dependencies from %s", dependenciesFile.getAbsolutePath()));
		log.info(String.format("Reading outdated info from %s", outdatedFile.getAbsolutePath()));

		Dependencies deps = readFromJsonFile(dependenciesFile, Dependencies.class);
		Map<String, Dependency> depMap = deps.getDependencies();
		if (depMap == null) {
			log.info("Did not find any dependencies");
		} else {
			// remove ignored dependencies
			deps.getDependencies().entrySet().removeIf(entry -> ignoreDependency(entry.getKey()));

			log.info(String.format("Found %d dependencies", depMap.size()));
		}

		Outdated outdated = readFromJsonFile(outdatedFile, Outdated.class);
		log.info(String.format("Found %d outdated entries", outdated.size()));

		List<OutDependency> dependencies = new ArrayList<>();
		for (Map.Entry<String, Dependency> entry : depMap.entrySet()) {
			String name = entry.getKey();
			Dependency dep = entry.getValue();
			String current = dep.getVersion();
			String latest = current;

			OutdatedEntry outdatedEntry = outdated.get(name);
			if (outdatedEntry != null) {
				latest = outdatedEntry.getLatest();
			}

			dependencies.add(new OutDependency().setName(name).setCurrent(current).setLatest(latest));
		}

		outputDependencies(dependencies);
	}
}
