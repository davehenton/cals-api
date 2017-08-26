package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformation;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, LocalDate.class, Constants.class, Utils.class})
public interface PlacementHomeInformationMapper {

  @Mapping(target = "thirdId", expression = "java(Utils.Id.generate())")
  @Mapping(target = "startDt", expression = "java(LocalDate.now())")
  @Mapping(target = "endDt", ignore = true)
  @Mapping(target = "licnseeCd", constant = "U")
  @Mapping(target = "crprvdrCd", constant = "Y")
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "fksbPvdrt", source = "substituteCareProviderId")
  @Mapping(target = "fkplcHmT", source = "placementHomeId")
  @Mapping(target = "prprvdrCd",
      expression = "java(Utils.Applicant.isPrimary(form, applicant) ? Constants.Y : Constants.N)")
  @Mapping(target = "cdsPrsn", constant = Constants.SPACE)
  @Mapping(target = "scprvdInd",
      expression = "java(Utils.Applicant.isPrimary(form, applicant) ? Constants.N : Constants.Y)")
  PlacementHomeInformation toPlacementHomeInformation(
      RFA1aFormDTO form, ApplicantDTO applicant, String placementHomeId, String substituteCareProviderId);

}
