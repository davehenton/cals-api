package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.RFA1aFormCollectionDTO;
import gov.ca.cwds.cals.service.mapper.rfa.RFA1aFormMapper;
import gov.ca.cwds.rest.api.Request;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormsCollectionService
    extends TypedCrudServiceAdapter<Boolean, Request, RFA1aFormCollectionDTO> {

  private RFA1aFormsDao dao;
  private RFA1aFormMapper rfa1aFormMapper;

  @Inject
  public RFA1aFormsCollectionService(RFA1aFormsDao dao, RFA1aFormMapper rfa1aFormMapper) {
    this.dao = dao;
    this.rfa1aFormMapper = rfa1aFormMapper;
  }

  @Override
  public RFA1aFormCollectionDTO find(Boolean expanded) {
    List<RFA1aForm> forms = dao.findAll();
    List<RFA1aFormDTO> formDTOs;
    if (expanded) {
      formDTOs = rfa1aFormMapper.toExpandedRFA1aFormsDTO(forms);
    } else {
      formDTOs = rfa1aFormMapper.toRFA1aFormsDTO(forms);
    }
    return new RFA1aFormCollectionDTO(formDTOs);
  }
}
