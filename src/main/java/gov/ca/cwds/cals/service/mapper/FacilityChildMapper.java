package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = {PersonMapper.class,
    TrailingSpacesRemovalPostMappingProcessor.class},
    imports = {gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator.class},
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@DecoratedWith(FacilityChildMapperDecorator.class)
public interface FacilityChildMapper {

  FacilityChildMapper INSTANCE = Mappers.getMapper(FacilityChildMapper.class);

  @Mapping(target = "id", source = "client.identifier")
  @Mapping(target = "displayClientId",
      expression = "java(CmsKeyIdGenerator.getUIIdentifierFromKey(client.getIdentifier()))")
  @Mapping(target = "person", source = "client")
  FacilityChildDTO toFacilityChildDTO(Client client);

  @Mapping(target = "assignedWorker", source = "staffPerson")
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDto,
      BaseStaffPerson staffPerson);

}