package gov.ca.cwds.cals;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

/**
 * @author CWDS CALS API Team
 */
public final class VelocityHelper {

  private final VelocityEngine velocityEngine;
  private final VelocityContext context;
  private String fixture;

  public VelocityHelper(String fixture) {
    this.fixture = fixture;
    velocityEngine = new VelocityEngine();
    velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    velocityEngine.init();
    context = new VelocityContext();
  }

  public void setParameter(String name, Object value) {
    context.put(name, value);
  }

  public String process() {
    StringWriter writer = new StringWriter();
    Template template = velocityEngine.getTemplate(fixture);
    template.merge(context, writer);
    return writer.toString();
  }
}
