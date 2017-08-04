package gov.ca.cwds.cals.web.rest.utils;

import java.io.StringWriter;
import java.util.Map;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author CWDS CALS API Team
 */
public final class VelocityHelper {

  private static final VelocityEngine velocityEngine;
  private final VelocityContext context;

  static {
    velocityEngine = new VelocityEngine();
    velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    velocityEngine.init();
  }

  public VelocityHelper() {
    context = new VelocityContext();
  }

  public void setParameter(String name, Object value) {
    context.put(name, value);
  }

  public void setParameters(Map<String, Object> parameterValues) {
    parameterValues.forEach(context::put);
  }

  public String process(String fixture) {
    StringWriter writer = new StringWriter();
    Template template = velocityEngine.getTemplate(fixture);
    template.merge(context, writer);
    return writer.toString();
  }
}
