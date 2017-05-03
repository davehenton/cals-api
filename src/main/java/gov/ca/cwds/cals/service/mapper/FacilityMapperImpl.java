package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;

public class FacilityMapperImpl implements FacilityMapper {

    @Override
    public FacilityDTO facilityToFacilityDTO(LisFacFile lisFacFile) {
        return new FacilityDTO(
                lisFacFile.getFacNbr().longValue(),
                "unimplemented",
                //lis_table_file.tbl_fac_type_desc
                lisFacFile.getFacName(),
                lisFacFile.getFacLicenseeName(),
                lisFacFile.getFacLicenseeType(),
                lisFacFile.getFacDoEvalCode(),
                "unimplemented", //lis_do_file.do_name
                Long.valueOf(lisFacFile.getFacNbr()),
                "unimplemented",  //lis_fac_file.fac_status
                lisFacFile.getFacCapacity(),
                (lisFacFile.getFacLicEffDate() != null ? lisFacFile.getFacLicEffDate().toLocalDate() : null),
                (lisFacFile.getFacOrigApplRecDate() != null ? lisFacFile.getFacOrigApplRecDate().toLocalDate() : null),
                (lisFacFile.getFacLastVisitDate() != null ? lisFacFile.getFacLastVisitDate().toLocalDate() : null),
                lisFacFile.getFacEmailAddress(),
                String.valueOf(lisFacFile.getFacLastVisitReason()),
                "unimplemented",  //lis_table_file.tbl_co_desc
                null,
                null);
    }

}
