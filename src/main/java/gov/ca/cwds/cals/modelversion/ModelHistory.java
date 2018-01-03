package gov.ca.cwds.cals.modelversion;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jonpeterson.jackson.module.versioning.VersionedModelConverter;
import gov.ca.cwds.rest.api.ApiException;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Created by TPT2 on 1/2/2018.
 */
abstract public class ModelHistory implements VersionedModelConverter {
  public static final String BASE_VERSION = "0";
  private SortedMap<String, Patch> history = new TreeMap<>();

  protected ModelHistory() {

  }

  protected void change(String version, Patch patch) {
    history.put(version, patch);
  }

  private Stream<Patch> getHistory(String version) {
    if (version.equals(BASE_VERSION)) {
      return history.values().stream();
    }
    if (!history.containsKey(version)) {
      throw new ApiException("There is no such version: " + version + " in history : " + getClass());
    }
    return history.tailMap(version).values().stream().skip(1);
  }

  private void patch(String objectVersion, ObjectNode objectNode) {
    getHistory(objectVersion).forEach(patch -> patch.apply(objectNode));
  }

  public ObjectNode convert(ObjectNode modelData, String modelVersion, String targetModelVersion, JsonNodeFactory nodeFactory) {
    patch(modelVersion, modelData);
    return modelData;
  }
}
