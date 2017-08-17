package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.OtherPeopleScpRelationship;
import gov.ca.cwds.cals.service.dto.rfa.RelationshipToApplicantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface OtherPeopleScpRelationshipMapper {

  @Mapping(target = "clntrelc", constant = "0")
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "fkothAdlt", expression = "")
  @Mapping(target = "fkothKidt", expression = "")
  OtherPeopleScpRelationship toOtherPeopleScpRelationship(
      RelationshipToApplicantDTO relationshipToApplicant,
      String relativeId);

}
