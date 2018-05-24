package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import gov.ca.cwds.cals.service.LegacyDictionariesCache;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.dao.CountiesDao;
import gov.ca.cwds.data.legacy.cms.dao.LicenseStatusDao;
import gov.ca.cwds.data.legacy.cms.dao.StateDao;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.LicenseStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCodeTable;
import java.util.HashMap;

/**
 * @author CWDS TPT-2 Team
 */
public class LegacyDictionariesCacheProvider implements Provider<LegacyDictionariesCache> {

  @Inject
  CountiesDao countiesDao;

  @Inject
  StateDao stateDao;

  @Inject
  LicenseStatusDao licenseStatusDao;

  private LegacyDictionariesCache cache;

  @Override
  public LegacyDictionariesCache get() {
    if (cache == null) {
      HashMap<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> map = new HashMap<>();
      map.put(County.class, countiesDao);
      map.put(State.class, stateDao);
      map.put(LicenseStatus.class, licenseStatusDao);
      cache = new LegacyDictionariesCache(map);
    }

    return cache;
  }
}
