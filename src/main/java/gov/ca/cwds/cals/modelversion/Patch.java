package gov.ca.cwds.cals.modelversion;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by TPT2 on 1/2/2018.
 */
public interface Patch  {
  void apply(ObjectNode objectNode);
}
