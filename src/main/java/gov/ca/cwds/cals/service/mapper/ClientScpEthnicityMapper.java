package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.ClientScpEthnicity;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface ClientScpEthnicityMapper {
  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "ethnctyc", source = "applicantDTO.ethnicity.cwsId")
  @Mapping(target = "estblshId", source = "substituteCareProviderId")
  @Mapping(target = "estblshCd", constant = "S")
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  ClientScpEthnicity toClientScpEthnicity(ApplicantDTO applicantDTO,
                                          String substituteCareProviderId);

}
