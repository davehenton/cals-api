package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.service.dto.AllegationDTO;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */

@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface ComplaintMapper {
    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @Mapping(target = "messages", ignore = true)
    @Mapping(source = "originalunidkey", target = "id")
    @Mapping(source = "crDate", target = "complaintDate")
    @Mapping(source = "lpaAssigned", target = "assignedWorker")
    @Mapping(source = "crControlnumber", target = "controlNumber")
    @Mapping(source = "crPrioritynr", target = "priorityLevel")
    @Mapping(source = "crStatus", target = "status")
    @Mapping(source = "dateSigned", target = "approvalDate")
    @Mapping(source = "crp2Precomments", target = "preInvestigationComments")
    @Mapping(source = "crp2Postcomments", target = "postInvestigationComments")
    @Mapping(source = "crp2Contactsummary", target = "contactSummary")
    @Mapping(source = "crp2Followup", target = "followupComments")
    ComplaintDTO entityToDTO(ComplaintReportLic802 complaint);

    @Mapping(target = "messages", ignore = true)
    Set<ComplaintDTO> complaintsListToComplaintsDTOList(List<ComplaintReportLic802> complaints);


    @Mapping(target = "complaintCode", source = "crCode")
    @Mapping(target = "allegation", source = "crAllegation")
    @Mapping(target = "resolutionCodeSub", source = "crSub")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub")
    AllegationDTO entityToAlligationDTO(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode1")
    @Mapping(target = "allegation", source = "crAllegation1")
    @Mapping(target = "resolutionCodeSub", source = "crSub1")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub1")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub1")
    AllegationDTO entityToAlligationDTO1(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode2")
    @Mapping(target = "allegation", source = "crAllegation2")
    @Mapping(target = "resolutionCodeSub", source = "crSub2")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub2")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub2")
    AllegationDTO entityToAlligationDTO2(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode3")
    @Mapping(target = "allegation", source = "crAllegation3")
    @Mapping(target = "resolutionCodeSub", source = "crSub3")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub3")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub3")
    AllegationDTO entityToAlligationDTO3(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode4")
    @Mapping(target = "allegation", source = "crAllegation4")
    @Mapping(target = "resolutionCodeSub", source = "crSub4")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub4")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub4")
    AllegationDTO entityToAlligationDTO4(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode5")
    @Mapping(target = "allegation", source = "crAllegation5")
    @Mapping(target = "resolutionCodeSub", source = "crSub5")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub5")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub5")
    AllegationDTO entityToAlligationDTO5(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode6")
    @Mapping(target = "allegation", source = "crAllegation6")
    @Mapping(target = "resolutionCodeSub", source = "crSub6")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub6")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub6")
    AllegationDTO entityToAlligationDTO6(ComplaintReportLic802 complaint);

    @Mapping(target = "complaintCode", source = "crCode7")
    @Mapping(target = "allegation", source = "crAllegation7")
    @Mapping(target = "resolutionCodeSub", source = "crSub7")
    @Mapping(target = "resolutionCodeInsub", source = "crInsub7")
    @Mapping(target = "resolutionCodeUnsub", source = "crUnsub7")
    AllegationDTO entityToAlligationDTO7(ComplaintReportLic802 complaint);


    @AfterMapping
    default ComplaintDTO mapAlligations(@MappingTarget ComplaintDTO complaintDTO, ComplaintReportLic802 complaint) {

        ArrayList<AllegationDTO> allegations = new ArrayList<>();
        complaintDTO.setAllegations(allegations);

        if (StringUtils.isNotEmpty(complaint.getCrCode())) {
            allegations.add(entityToAlligationDTO(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode1())) {
            allegations.add(entityToAlligationDTO1(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode2())) {
            allegations.add(entityToAlligationDTO2(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode3())) {
            allegations.add(entityToAlligationDTO3(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode4())) {
            allegations.add(entityToAlligationDTO4(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode5())) {
            allegations.add(entityToAlligationDTO5(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode6())) {
            allegations.add(entityToAlligationDTO6(complaint));
        }

        if (StringUtils.isNotEmpty(complaint.getCrCode7())) {
            allegations.add(entityToAlligationDTO7(complaint));
        }

        return complaintDTO;
    }

}
