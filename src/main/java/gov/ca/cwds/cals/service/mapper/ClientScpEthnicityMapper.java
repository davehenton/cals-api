package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.data.legacy.cms.entity.ClientScpEthnicity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface ClientScpEthnicityMapper {
  ClientScpEthnicityMapper INSTANCE = Mappers.getMapper(ClientScpEthnicityMapper.class);

  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "ethnctyc", source = "applicantDTO.ethnicity.cwsId")
  @Mapping(target = "estblshId", source = "substituteCareProviderId")
  @Mapping(target = "estblshCd", constant = "S")
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  ClientScpEthnicity toClientScpEthnicity(ApplicantDTO applicantDTO,
      String substituteCareProviderId);

}
