package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.OtherPeopleScpRelationship;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import gov.ca.cwds.cals.service.dto.rfa.RelationshipToApplicantDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface OtherPeopleScpRelationshipMapper {
  OtherPeopleScpRelationshipMapper INSTANCE = Mappers.getMapper(OtherPeopleScpRelationshipMapper.class);

  @Mapping(target = "clntrelc", constant = "0")
  @Mapping(target = "fkothAdlt", ignore = true)
  @Mapping(target = "fkothKidt", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "substituteCareProvider", source = "substituteCareProvider")
  OtherPeopleScpRelationship toOtherPeopleScpRelationship(
      RelationshipToApplicantDTO relationshipToApplicant,
      String childId, SubstituteCareProvider substituteCareProvider);

  @InheritConfiguration(name = "toOtherPeopleScpRelationship")
  @Mapping(target = "fkothAdlt", source = "adultId")
  @Mapping(target = "fkothKidt", ignore = true)
  OtherPeopleScpRelationship toOtherAdultScpRelationship(
      RelationshipToApplicantDTO relationshipToApplicant,
      String adultId, SubstituteCareProvider substituteCareProvider);

  @InheritConfiguration(name = "toOtherPeopleScpRelationship")
  @Mapping(target = "fkothAdlt", ignore = true)
  @Mapping(target = "fkothKidt", source = "childId")
  OtherPeopleScpRelationship toOtherChildScpRelationship(
      RelationshipToApplicantDTO relationshipToApplicant,
      String childId, SubstituteCareProvider substituteCareProvider);

}
