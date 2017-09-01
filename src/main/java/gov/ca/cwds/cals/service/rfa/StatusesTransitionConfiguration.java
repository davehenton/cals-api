package gov.ca.cwds.cals.service.rfa;

import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author CWDS CALS API Team
 */

public final class StatusesTransitionConfiguration {

  private static Map<RFAApplicationStatus, RFAApplicationStatus[]> allowedTransitionsMap = new EnumMap<>(
      RFAApplicationStatus.class);

  static {
    allowedTransitionsMap.put(RFAApplicationStatus.DRAFT, new RFAApplicationStatus[]{
        RFAApplicationStatus.DRAFT,
        RFAApplicationStatus.IN_PROGRESS});
    allowedTransitionsMap.put(RFAApplicationStatus.IN_PROGRESS, new RFAApplicationStatus[]{
        RFAApplicationStatus.SUBMITTED, RFAApplicationStatus.DRAFT});
    allowedTransitionsMap.put(RFAApplicationStatus.SUBMITTED,
        new RFAApplicationStatus[]{RFAApplicationStatus.SUBMITTED});
  }

  private StatusesTransitionConfiguration() {
    //this should never be called
  }

  public static boolean isTransitionAllowed(RFAApplicationStatus statusFrom,
      RFAApplicationStatus statusTo) {
    return ArrayUtils.contains(allowedTransitionsMap.get(statusFrom), statusTo);
  }

}
