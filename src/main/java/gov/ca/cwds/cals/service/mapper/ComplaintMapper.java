package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */

@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface ComplaintMapper {

    @Mapping(target = "messages", ignore = true)
    @Mapping(source = "originalunidkey", target = "id")
    @Mapping(source = "crDate", target = "complaintDate")
    @Mapping(source = "lpaAssigned", target = "assignedWorker")
    @Mapping(source = "crControlnumber", target = "controlNumber")
    @Mapping(source = "crPrioritynr", target = "priorityLevel")
    @Mapping(source = "crStatus", target = "status")
    @Mapping(source = "dateSigned", target = "approvalDate")
    ComplaintDTO entityToDTO(ComplaintReportLic802 complaint);

    @Mapping(target = "messages", ignore = true)
    Set<ComplaintDTO> complaintsListToComplaintsDTOList(List<ComplaintReportLic802> complaints);

}
