package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.DictionariesDao;
import gov.ca.cwds.cals.persistence.model.calsns.Dictionary;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import java.io.Serializable;
import java.util.List;

/** @author CWDS CALS API Team */
public class DictionariesService extends CrudServiceAdapter implements CrudsService {

  private DictionariesDao dao;

  @Inject
  public DictionariesService(DictionariesDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable dictionaryType) {
    List<Dictionary> dictionariesByType = dao.findDictionariesByType((String) dictionaryType);
    return new CollectionDTO<>(dictionariesByType);
  }
}
