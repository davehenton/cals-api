package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProviderUc;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, Utils.class, StringUtils.class, Constants.class})
public interface SubstituteCareProviderUCMapper {
  SubstituteCareProviderUCMapper INSTANCE = Mappers.getMapper(SubstituteCareProviderUCMapper.class);

  @Mapping(target = "pksbPvdrt", source = "identifier")
  @Mapping(target = "caDlicNo", expression = "java(StringUtils.upperCase(" +
          "Utils.Applicant.getCaliforniaDriverLicense(applicant, Constants.SPACE)))")
  @Mapping(target = "firstNm", expression = "java(StringUtils.upperCase(applicant.getFirstName()))")
  @Mapping(target = "lastNm", expression = "java(StringUtils.upperCase(applicant.getLastName()))")
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  SubstituteCareProviderUc toSubstituteCareProviderUC(String identifier, ApplicantDTO applicant);
}
