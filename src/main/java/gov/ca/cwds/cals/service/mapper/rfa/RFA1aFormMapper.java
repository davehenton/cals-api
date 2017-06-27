package gov.ca.cwds.cals.service.mapper.rfa;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS CALS API Team.
 */
@Mapper
public interface RFA1aFormMapper {

  @Mapping(target = "initialApplication", source = "application.initialApplication")
  @Mapping(target = "otherType", source = "application.otherType")
  @Mapping(target = "otherTypeDescription", source = "application.otherTypeDescription")
  @Mapping(target = "applicationCounty", source = "application.applicationCounty")
  RFA1aFormDTO toRFA1aFormDTO(RFA1aForm form);

  List<RFA1aFormDTO> toRFA1aFormsDTO(List<RFA1aForm> forms);

  @Mapping(target = "id", ignore = true)
  @InheritInverseConfiguration
  void toRFA1aForm(@MappingTarget RFA1aForm rfa1aForm, RFA1aFormDTO formDTO);

}
