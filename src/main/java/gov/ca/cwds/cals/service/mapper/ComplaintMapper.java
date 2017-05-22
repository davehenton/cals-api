package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */

@Mapper(imports = StringUtils.class)
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @Mapping(target = "messages", ignore = true)
    @Mapping(source = "originalunidkey", target = "id")
    @Mapping(source = "crDate", target = "complaintDate")
    @Mapping(source = "lpaAssigned", target = "assignedWorker")
    @Mapping(source = "crControlnumber", target = "controlNumber")
    @Mapping(target = "priorityLevel",
            expression = "java(StringUtils.isEmpty(complaint.getCrPrioritynr())? -999999 : Integer.parseInt(complaint.getCrPrioritynr()))")
    @Mapping(source = "crStatus", target = "status")
    @Mapping(source = "dateSigned", target = "approvalDate")
    ComplaintDTO entityToDTO(ComplaintReportLic802 complaint);

    @Mapping(target = "messages", ignore = true)
    Set<ComplaintDTO> complaintsListToComplaintsDTOList(List<ComplaintReportLic802> complaints);

}
