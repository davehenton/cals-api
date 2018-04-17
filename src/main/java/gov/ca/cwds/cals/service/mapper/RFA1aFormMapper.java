package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


/**
 * @author CWDS CALS API Team.
 */
@SuppressWarnings("squid:S1168") // "Empty arrays and collections should be returned instead of null", it was designed to return null for empty list
@Mapper
public interface RFA1aFormMapper {

  RFA1aFormMapper INSTANCE = Mappers.getMapper(RFA1aFormMapper.class);

  @Named("toRFA1aFormDTO")
  @Mapping(target = "initialApplication", source = "application.initialApplication")
  @Mapping(target = "otherTypeDescription", source = "application.otherTypeDescription")
  @Mapping(target = "applicationCounty", source = "application.applicationCounty")
  @Mapping(target = "metadata", source = "application.metadata")
  @Mapping(target = "applicantsDeclaration", ignore = true)
  @Mapping(target = "references", ignore = true)
  @Mapping(target = "applicantsHistory", ignore = true)
  @Mapping(target = "adoptionHistory", ignore = true)
  @Mapping(target = "applicants", ignore = true)
  @Mapping(target = "minorChildren", ignore = true)
  @Mapping(target = "otherAdults", ignore = true)
  @Mapping(target = "rfa1cForms", ignore = true)
  @Mapping(target = "applicantsRelationship", ignore = true)
  @Mapping(target = "residence", ignore = true)
  @Mapping(target = "childDesired", ignore = true)
  @Mapping(target = "messages", ignore = true)
  RFA1aFormDTO toRFA1aFormDTO(RFA1aForm form);

  @Named("toExpandedRFA1aFormDTO")
  @Mapping(target = "applicantsDeclaration", source = "applicantsDeclaration")
  @Mapping(target = "references", source = "references")
  @Mapping(target = "applicantsHistory", source = "applicantsHistory")
  @Mapping(target = "adoptionHistory", source = "adoptionHistory")
  @Mapping(target = "applicants", source = "applicants")
  @Mapping(target = "minorChildren", source = "minorChildren")
  @Mapping(target = "otherAdults", source = "otherAdults")
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
  @Mapping(target = "application.otherTypeDescription", source = "otherTypeDescription")
  @Mapping(target = "application.applicationCounty", source = "applicationCounty")
  @Mapping(target = "application.metadata", source = "metadata")
  @Mapping(target = "application", ignore = true)
  @Mapping(target = "applicantsDeclaration", ignore = true)
  @Mapping(target = "references", ignore = true)
  @Mapping(target = "applicantsHistory", ignore = true)
  @Mapping(target = "adoptionHistory", ignore = true)
  @Mapping(target = "applicants", ignore = true)
  @Mapping(target = "minorChildren", ignore = true)
  @Mapping(target = "otherAdults", ignore = true)
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

  default ApplicantDTO toApplicantDTO(RFA1aApplicant entity) {
    ApplicantDTO applicantDTO = entity.getApplicant();
    Optional.ofNullable(entity.getRfa1bForm())
        .ifPresent(rfa1bForm -> applicantDTO.setRfa1bForm(rfa1bForm.getEntityDTO()));

    Optional.ofNullable(entity.getLic198bForm())
        .ifPresent(lic198bForm -> applicantDTO.setLic198bForm(lic198bForm.getEntityDTO()));

    return applicantDTO;
  }

  default MinorChildDTO toMinorChildDTO(RFA1aMinorChild entity) {
    return entity.getMinorChild();
  }

  default OtherAdultDTO toOtherAdultDTO(RFA1aOtherAdult entity) {
    OtherAdultDTO otherAdultDTO = entity.getOtherAdult();
    Optional.ofNullable(entity.getRfa1bForm())
        .ifPresent(rfa1bForm -> otherAdultDTO.setRfa1bForm(rfa1bForm.getEntityDTO()));

    Optional.ofNullable(entity.getLic198bForm())
        .ifPresent(lic198bForm -> otherAdultDTO.setLic198bForm(lic198bForm.getEntityDTO()));

    return otherAdultDTO;
  }


  default RFA1cFormDTO toRFA1cFormDTO(RFA1cForm entity) {
    return entity.getEntityDTO();
  }

  default List<ApplicantDTO> toListApplicantDTO(List<RFA1aApplicant> list) {
    if (list == null || list.isEmpty()) {
      return null;
    }

    List<ApplicantDTO> newList = new ArrayList<>(list.size());
    for (RFA1aApplicant rFA1aApplicant : list) {
      newList.add(toApplicantDTO(rFA1aApplicant));
    }

    return newList;
  }

  default List<MinorChildDTO> toListMinorChildDTO(List<RFA1aMinorChild> list) {
    if (list == null || list.isEmpty()) {
      return null;
    }

    List<MinorChildDTO> newList = new ArrayList<>(list.size());
    for (RFA1aMinorChild rFA1aMinorChild : list) {
      newList.add(toMinorChildDTO(rFA1aMinorChild));
    }

    return newList;
  }

  default List<OtherAdultDTO> toListOtherAdultDTO(List<RFA1aOtherAdult> list) {
    if (list == null || list.isEmpty()) {
      return null;
    }

    List<OtherAdultDTO> newList = new ArrayList<>(list.size());
    for (RFA1aOtherAdult rFA1aOtherAdult : list) {
      newList.add(toOtherAdultDTO(rFA1aOtherAdult));
    }

    return newList;
  }

  default List<RFA1cFormDTO> toListRFA1cFormDTO(List<RFA1cForm> list) {
    if (list == null || list.isEmpty()) {
      return null;
    }

    List<RFA1cFormDTO> newList = new ArrayList<>(list.size());
    for (RFA1cForm rFA1cForm : list) {
      newList.add(toRFA1cFormDTO(rFA1cForm));
    }

    return newList;
  }


}
