package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.exceptions.DictionaryEntryNotFoundException;
import gov.ca.cwds.cals.persistence.dao.calsns.DictionariesDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author CWDS CALS-1 Team
 */
public class FacilityTypeService {

  @Inject
  private DictionariesDao dictionariesDao;

  private static List<FacilityType> dictionaryList;

  public Long getDictionaryIdByCMSId(int CMSId) {
    return findFacilityTypeByPredicate(ft -> ft.getCwsId() == CMSId).getId();
  }

  public Long getDictionaryIdByLISId(String LISId) {
    return findFacilityTypeByPredicate(fT -> fT.getLisId().equalsIgnoreCase(LISId)).getId();
  }

  private FacilityType findFacilityTypeByPredicate(Predicate<FacilityType> predicate) {
    checkDictionaryListAndCreateIfNeeded();
    FacilityType facilityType = dictionaryList.stream().filter(predicate).findFirst().orElse(null);
    if (null == facilityType) {
      throw new DictionaryEntryNotFoundException();
    }
    return facilityType;
  }

  private void checkDictionaryListAndCreateIfNeeded() {
    if (null == dictionaryList) {
      dictionaryList = dictionariesDao.findDictionariesByType(FacilityType.class);
    }
  }
}