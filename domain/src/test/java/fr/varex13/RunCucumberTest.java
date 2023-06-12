package fr.varex13;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.annotation.DirtiesContext;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("fr/varex13")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "fr.varex13")
@DirtiesContext
public class RunCucumberTest {
}
