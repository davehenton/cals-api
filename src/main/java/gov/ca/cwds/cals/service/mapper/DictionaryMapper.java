package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.DictionaryDTO;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.LicenseStatus;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @author CWDS CALS API Team
 */
@Mapper(
    imports = StringUtils.class,
    uses = TrailingSpacesRemovalPostMappingProcessor.class
)
public interface DictionaryMapper {

  @Mapping(target = "code", source = "logicalId")
  @Mapping(target = "description", source = "shortDescription")
  DictionaryDTO toDictionary(LicenseStatus licenseStatus);

  @Named("facilityStatus")
  @Mapping(target = "code", source = "tblFacStatusCode")
  @Mapping(target = "description", source = "tblFacStatusDesc")
  DictionaryDTO facilityStatusToDictionary(LisTableFile lisTableFile);

  @Named("facilityVisitReason")
  @Mapping(target = "code", source = "tblVisitReasonCode")
  @Mapping(target = "description", source = "tblVisitReasonDesc")
  DictionaryDTO facilityVisitReasonToDictionary(LisTableFile lisTableFile);
}
