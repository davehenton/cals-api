package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.packet.PacketDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.rfa.builder.PacketBuilder;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;

/**
 * @author CWDS TPT-2
 */
public class RFAFormsPackageService extends
    TypedCrudServiceAdapter<Long, PacketDTO, PacketDTO> {

  @Inject
  private RFA1aFormService rfa1aFormService;

  @Override
  public PacketDTO find(Long formId) {
    RFA1aFormDTO rfa1aFormDTO = rfa1aFormService.find(new RFA1aFormsParameterObject(formId, true));

    return null != rfa1aFormDTO ? fillPacketSummary(rfa1aFormDTO) : null;
  }

  private PacketDTO fillPacketSummary(RFA1aFormDTO rfa1aFormDTO) {
    return new PacketBuilder()
        .rfa1aSections(rfa1aFormDTO)
        .applicants(rfa1aFormDTO)
        .otherAdults(rfa1aFormDTO)
        .adultChildren(rfa1aFormDTO)
        .build();
  }
}
