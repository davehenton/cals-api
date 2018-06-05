package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.util.Utils;
import gov.ca.cwds.data.legacy.cms.entity.CountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, Utils.class, StringUtils.class,
    Constants.class, PrincipalUtils.class, StaffPerson.class})
public interface CountyLicenseCaseMapper {

  CountyLicenseCaseMapper INSTANCE = Mappers.getMapper(CountyLicenseCaseMapper.class);

    @Mapping(target = "fireInd", constant = "N")
    @Mapping(target = "licGndr", constant = "B")
    @Mapping(target = "licAgeFr", constant = "0")
    @Mapping(target = "licAgeTo", constant = "18")
    @Mapping(target = "trngPlan", constant = "")
    @Mapping(target = "prtyInfo", constant = "")
    @Mapping(target = "trngCmplt", constant = "")
    @Mapping(target = "ffhType", constant = "3179")
//    @Mapping(target = "staffPerson", expression = "java(PrincipalUtils.getStaffPersonId())")
//    @Mapping(target = "cntySpfcd", expression = "java(new StaffPerson())")
    CountyLicenseCase toCountyLicenseCase(RFA1aFormDTO form);
}
