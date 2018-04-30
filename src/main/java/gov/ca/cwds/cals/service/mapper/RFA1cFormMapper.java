package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1cFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for RFA 1c form.
 *
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S1609", "squid:S1214"})
@Mapper
public interface RFA1cFormMapper {

  RFA1cFormMapper INSTANCE = Mappers.getMapper(RFA1cFormMapper.class);

  /**
   * Maps data from RFA1aFormDTO to given RFA1cFormDTO.
   *
   * @param target RFA1cFormDTO
   * @param rfa1a RFA1aFormDTO
   * @return mapped RFA1bFormDTO
   */
  default RFA1cFormDTO toRFA1cFormDTO(RFA1cFormDTO target, RFA1aFormDTO rfa1a) {
    // County
    target.setApplicationCounty(rfa1a.getApplicationCounty());
    return target;
  }

}
