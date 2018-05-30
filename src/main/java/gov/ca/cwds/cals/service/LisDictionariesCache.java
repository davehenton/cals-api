package gov.ca.cwds.cals.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.lis.LisTableFileDao;
import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * LIS Dictionaries Cache Holder.
 *
 * @author CWDS TPT-2 Team
 */
public class LisDictionariesCache {

  @Inject
  private LisTableFileDao lisTableFileDao;

  public enum LisDictionary {
    COUNTY, FACILITY_STATUS, VISIT_REASON_TYPE, FACILITY_TYPE
  }

  /**
   * LIS Cache Loader.
   */
  private static class LisCacheLoader implements Callable<Map<Integer, LisTableFile>> {

    private LisTableFileDao lisTableFileDao;
    private LisDictionary key;

    LisCacheLoader(LisTableFileDao lisTableFileDao) {
      this.lisTableFileDao = lisTableFileDao;
    }

    public void setKey(LisDictionary key) {
      this.key = key;
    }

    @Override
    public Map<Integer, LisTableFile> call() {
      List<LisTableFile> valuesList = null;
      Collector<LisTableFile, ?, Map<Integer, LisTableFile>> lisTableFileMapCollector;
      switch (key) {
        case COUNTY:
          valuesList = lisTableFileDao.findAllCounties();
          lisTableFileMapCollector = Collectors.toMap(LisTableFile::getTblCoNbr, o -> o);
          break;
        case FACILITY_STATUS:
          valuesList = lisTableFileDao.findAllFacilityStatuses();
          lisTableFileMapCollector = Collectors
              .toMap(LisTableFile::getTblFacStatusCode, o -> o);
          break;
        case VISIT_REASON_TYPE:
          valuesList = lisTableFileDao.findAllVisitReasons();
          lisTableFileMapCollector = Collectors
              .toMap(LisTableFile::getTblVisitReasonCode, o -> o);
          break;
        case FACILITY_TYPE:
          valuesList = lisTableFileDao.findAllFacilityTypes();
          lisTableFileMapCollector = Collectors.toMap(LisTableFile::getTblFacTypeCode, o -> o);
          break;
        default:
          throw new IllegalArgumentException("There is no dictionary for: " + key);
      }

      return Optional.ofNullable(valuesList)
          .map(lisTableFiles -> lisTableFiles.stream().collect(lisTableFileMapCollector))
          .orElseThrow(() -> new IllegalStateException("Can't get dictionary for: " + key));
    }
  }

  private LisCacheLoader lisCacheLoader;

  private static Cache<LisDictionary, Map<Integer, LisTableFile>> cache = CacheBuilder.newBuilder()
      .expireAfterAccess(1, TimeUnit.DAYS).build();

  /**
   * Finds county by countyCode.
   *
   * @param countyCode County code
   * @return LisTableFile
   */
  public LisTableFile findCounty(Integer countyCode) {
    return getLisTableFileFromCache(countyCode, LisDictionary.COUNTY);
  }

  /**
   * Finds Facility Status.
   *
   * @param facilityStatusCode facility status Code
   * @return LisTableFile
   */
  public LisTableFile findFacilityStatus(Integer facilityStatusCode) {
    return getLisTableFileFromCache(facilityStatusCode, LisDictionary.FACILITY_STATUS);
  }

  /**
   * Finds a visit reason type.
   *
   * @param visitReasonCode visit reason code
   * @return LisTableFile
   */
  public LisTableFile findVisitReasonType(Integer visitReasonCode) {
    return getLisTableFileFromCache(visitReasonCode, LisDictionary.VISIT_REASON_TYPE);
  }

  /**
   * Finds a facility type.
   *
   * @param fascilityTypeCode facility type code
   * @return LisTableFile
   */
  public LisTableFile findFacilityType(Integer fascilityTypeCode) {
    return getLisTableFileFromCache(fascilityTypeCode, LisDictionary.FACILITY_TYPE);
  }

  private LisTableFile getLisTableFileFromCache(Integer code, LisDictionary dictionaryType) {
    try {
      return cache.get(dictionaryType, getCacheLoader(dictionaryType)).get(code);
    } catch (ExecutionException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }

  private LisCacheLoader getCacheLoader(LisDictionary key) {
    if (lisCacheLoader == null) {
      lisCacheLoader = new LisCacheLoader(lisTableFileDao);
    }
    lisCacheLoader.setKey(key);
    return lisCacheLoader;
  }
}
