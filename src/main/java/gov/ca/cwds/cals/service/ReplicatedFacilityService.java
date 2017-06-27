package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.CompositeIterator;
import gov.ca.cwds.cals.persistence.dao.cms.legacy.ClientDao;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPersistentEntity;
import gov.ca.cwds.cals.ReplicationOperation;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.legacy.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.rs.ReplicatedPersistentEntityDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityCompositeDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.rs.ReplicatedFacilityCompositeDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;

import gov.ca.cwds.data.CrudsDao;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedFacilityService extends FacilityService {

  private ReplicatedPersistentEntityDao replicatedPersistentEntityDao;
  private ClientDao clientDao;
  private FacilityChildMapper facilityChildMapper;

  @Inject
  public ReplicatedFacilityService(
      CrudsDao<LisFacFile> lisDsLisFacFileDao,
      CrudsDao<LisFacFile> fasDsLisFacFileDao,
      PlacementHomeDao placementHomeDao,
      LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao, FacilityMapper facilityMapper,
      FasFacilityMapper fasFacilityMapper,
      ReplicatedPersistentEntityDao replicatedPersistentEntityDao,
      ClientDao clientDao, FacilityChildMapper facilityChildMapper) {
    super(lisDsLisFacFileDao, fasDsLisFacFileDao, placementHomeDao, lpaInformationDao, countiesDao,
        facilityMapper, fasFacilityMapper);
    this.replicatedPersistentEntityDao = replicatedPersistentEntityDao;
    this.clientDao = clientDao;
    this.facilityChildMapper = facilityChildMapper;
  }

  public Stream<ReplicatedFacilityCompositeDTO> facilitiesStream(
      FacilityParameterObject paramObject) {
    ReplicatedFacilityDataHolder dataHolder = new ReplicatedFacilityDataHolder();
    replicatedPersistentEntityDao.streamUpdatedFacilityData(paramObject).forEach(dataHolder::add);

    return StreamSupport.stream(dataHolder.newIterable().spliterator(), false).map(
        replicatedEntity -> {
          FacilityDTO facilityDTO = findById(replicatedEntity.getId());
          FacilityCompositeDTO compositeDTO = facilityMapper.toFacilityCompositeDTO(facilityDTO);
          addChildren(compositeDTO);

          return new ReplicatedFacilityCompositeDTO(compositeDTO,
              replicatedEntity.getReplicationOperation());
        });
  }

  private void addChildren(FacilityCompositeDTO compositeDTO) {
    List<FacilityChildDTO> facilityChildren = clientDao.streamByFacilityId(compositeDTO.getId())
        .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());
    compositeDTO.setFacilityChildren(facilityChildren);
  }

  private class ReplicatedFacilityDataHolder {

    private HashMap<String, ReplicatedPersistentEntity> toBeDeleted = new HashMap<>();
    private HashMap<String, ReplicatedPersistentEntity> toBeInserted = new HashMap<>();
    private HashMap<String, ReplicatedPersistentEntity> toBeUpdated = new HashMap<>();

    void add(ReplicatedPersistentEntity data) {
      if (ReplicationOperation.D == data.getReplicationOperation()) {
        toBeDeleted.put(data.getId(), data);
      } else if (ReplicationOperation.I == data.getReplicationOperation()) {
        toBeInserted.put(data.getId(), data);
      } else if (ReplicationOperation.U == data.getReplicationOperation()) {
        toBeUpdated.put(data.getId(), data);
      }
    }

    Iterable<ReplicatedPersistentEntity> newIterable() {
      compact();
      return () -> new CompositeIterator<>(
          toBeDeleted.values().iterator(),
          toBeInserted.values().iterator(),
          toBeUpdated.values().iterator()
      );
    }

    private void compact() {
      toBeDeleted.forEach((id, e) -> {
        toBeInserted.remove(id);
        toBeUpdated.remove(id);
      });
      toBeInserted.forEach((id, e) -> toBeUpdated.remove(id));
    }
  }
}
