package com.gentics.dependency.reporting.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gentics.dependency.reporting.java.model.DependencyUpdatesReport;

public class TestParsingXml {
	@Test
	public void read() throws StreamReadException, DatabindException, IOException {
		XmlMapper mapper = new XmlMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		DependencyUpdatesReport report = null;
		try (InputStream in = TestParsingXml.class.getResourceAsStream("/dependency-updates-report.xml")) {
			report = mapper.readValue(in, DependencyUpdatesReport.class);
		}

		System.out.println(report);
	}
}
