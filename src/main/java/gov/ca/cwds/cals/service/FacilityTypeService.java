package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.google.inject.Inject;
import gov.ca.cwds.cals.exceptions.DictionaryEntryNotFoundException;
import gov.ca.cwds.cals.persistence.dao.calsns.DictionariesDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.FacilityType;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import java.util.function.Predicate;

/**
 * Service for FacilityType related operations.
 *
 * @author CWDS CALS-1 Team
 */
public class FacilityTypeService {

  private static List<FacilityType> dictionaryList;

  @Inject
  private DictionariesDao dictionariesDao;

  FacilityType getFacilityTypeByCmsFacilityTypeId(int cwsId) {
    return getFacilityTypeByPredicates(ft -> ft.getCwsId() != -1,
        fT -> fT.getCwsId() == cwsId);
  }

  @UnitOfWork(CALSNS)
  FacilityType getFacilityTypeByLisFacilityTypeId(Integer lisId) {
    return getFacilityTypeByPredicates(ft -> ft.getLisId() != null,
        fT -> Integer.valueOf(fT.getLisId().trim()).equals(lisId));
  }

  private FacilityType getFacilityTypeByPredicates(Predicate<FacilityType> notNullTypePredicate,
      Predicate<FacilityType> idPredicate) {
    checkDictionaryListAndCreateIfNeeded();

    FacilityType facilityType = dictionaryList.stream()
        .filter(notNullTypePredicate)
        .filter(idPredicate).findFirst().orElse(null);

    checkIfFacilityTypeIsFound(facilityType);
    return facilityType;
  }

  private synchronized void checkDictionaryListAndCreateIfNeeded() {
    if (null == dictionaryList) {
      dictionaryList = dictionariesDao.findDictionariesByType(FacilityType.class);
    }
  }

  private void checkIfFacilityTypeIsFound(FacilityType facilityType) {
    if (null == facilityType) {
      throw new DictionaryEntryNotFoundException();
    }
  }
}
