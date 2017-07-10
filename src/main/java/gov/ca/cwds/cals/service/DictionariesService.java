package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.DictionaryType;
import gov.ca.cwds.cals.persistence.dao.calsns.DictionariesDao;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.BaseDictionary;
import gov.ca.cwds.cals.service.dto.DictionaryValuesDTO;
import gov.ca.cwds.rest.services.TypedCrudsService;
import java.util.List;

/** @author CWDS CALS API Team */
public class DictionariesService
    extends TypedCrudServiceAdapter<DictionaryType, BaseDictionary, DictionaryValuesDTO>
    implements TypedCrudsService<DictionaryType, BaseDictionary, DictionaryValuesDTO> {

  private DictionariesDao dao;

  @Inject
  public DictionariesService(DictionariesDao dao) {
    this.dao = dao;
  }

  @Override
  public DictionaryValuesDTO find(DictionaryType dictionaryType) {
    List<BaseDictionary> dictionariesByType = dao.findDictionariesByType(dictionaryType);
    return new DictionaryValuesDTO(dictionariesByType);
  }
}
