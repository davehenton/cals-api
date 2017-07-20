package gov.ca.cwds.cals.service.mapper.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;


/**
 * @author CWDS CALS API Team.
 */
@Mapper
public interface RFA1aFormMapper {

  @Named("toRFA1aFormDTO")
  @Mapping(target = "initialApplication", source = "application.initialApplication")
  @Mapping(target = "otherType", source = "application.otherType")
  @Mapping(target = "otherTypeDescription", source = "application.otherTypeDescription")
  @Mapping(target = "applicationCounty", source = "application.applicationCounty")
  @Mapping(target = "applicantsDeclaration", ignore = true)
  @Mapping(target = "references", ignore = true)
  @Mapping(target = "applicantsHistory", ignore = true)
  @Mapping(target = "adoptionHistory", ignore = true)
  @Mapping(target = "applicants", ignore = true)
  @Mapping(target = "minorChildren", ignore = true)
  @Mapping(target = "otherAdults", ignore = true)
  @Mapping(target = "rfa1bForms", ignore = true)
  @Mapping(target = "rfa1cForms", ignore = true)
  @Mapping(target = "applicantsRelationship", ignore = true)
  @Mapping(target = "residence", ignore = true)
  @Mapping(target = "childDesired", ignore = true)
  @Mapping(target = "messages", ignore = true)
  RFA1aFormDTO toRFA1aFormDTO(RFA1aForm form);

  @Named("toExpandedRFA1aFormDTO")
  @Mapping(target = "application", source = "application")
  @Mapping(target = "applicantsDeclaration", source = "applicantsDeclaration")
  @Mapping(target = "references", source = "references")
  @Mapping(target = "applicantsHistory", source = "applicantsHistory")
  @Mapping(target = "adoptionHistory", source = "adoptionHistory")
  @Mapping(target = "applicants", source = "applicants")
  @Mapping(target = "minorChildren", source = "minorChildren")
  @Mapping(target = "otherAdults", source = "otherAdults")
  @Mapping(target = "rfa1bForms", source = "rfa1bForms")
  @Mapping(target = "rfa1cForms", source = "rfa1cForms")
  @Mapping(target = "applicantsRelationship", source = "applicantsRelationship")
  @Mapping(target = "residence", source = "residence")
  @Mapping(target = "childDesired", source = "childDesired")
  @InheritConfiguration(name = "toRFA1aFormDTO")
  RFA1aFormDTO toExpandedRFA1aFormDTO(RFA1aForm form);

  @IterableMapping(qualifiedByName = "toRFA1aFormDTO")
  List<RFA1aFormDTO> toRFA1aFormsDTO(List<RFA1aForm> forms);

  @IterableMapping(qualifiedByName = "toExpandedRFA1aFormDTO")
  List<RFA1aFormDTO> toExpandedRFA1aFormsDTO(List<RFA1aForm> forms);

  @Mapping(target = "application.initialApplication", source = "initialApplication")
  @Mapping(target = "application.otherType", source = "otherType")
  @Mapping(target = "application.otherTypeDescription", source = "otherTypeDescription")
  @Mapping(target = "application.applicationCounty", source = "applicationCounty")
  @Mapping(target = "application", ignore = true)
  @Mapping(target = "applicantsDeclaration", ignore = true)
  @Mapping(target = "references", ignore = true)
  @Mapping(target = "applicantsHistory", ignore = true)
  @Mapping(target = "adoptionHistory", ignore = true)
  @Mapping(target = "applicants", ignore = true)
  @Mapping(target = "minorChildren", ignore = true)
  @Mapping(target = "otherAdults", ignore = true)
  @Mapping(target = "rfa1bForms", ignore = true)
  @Mapping(target = "rfa1cForms", ignore = true)
  @Mapping(target = "applicantsRelationship", ignore = true)
  @Mapping(target = "residence", ignore = true)
  @Mapping(target = "childDesired", ignore = true)
  @Mapping(target = "updateDateTime", ignore = true)
  @Mapping(target = "updateUserId", ignore = true)
  @Mapping(target = "createDateTime", ignore = true)
  @Mapping(target = "createUserId", ignore = true)
  @Mapping(target = "status", ignore = true)
  void toRFA1aForm(@MappingTarget RFA1aForm rfa1aForm, RFA1aFormDTO formDTO);
}
