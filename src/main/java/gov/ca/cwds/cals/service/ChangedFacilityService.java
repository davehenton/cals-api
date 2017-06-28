package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.CompositeIterator;
import gov.ca.cwds.cals.RecordChangeOperation;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.model.RecordChangeObject;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
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

import gov.ca.cwds.data.CrudsDao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2
 */
public class ChangedFacilityService extends FacilityService {

  private static final Logger LOG = LoggerFactory.getLogger(ChangedFacilityService.class);

  private ReplicatedPersistentEntityDao replicatedPersistentEntityDao;
  private ClientDao clientDao;
  private FacilityChildMapper facilityChildMapper;

  @Inject
  public ChangedFacilityService(
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

  public Stream<ReplicatedFacilityCompositeDTO> changedFacilitiesStream(Date after) {
    RecordChanges recordChanges = new RecordChanges();
    replicatedPersistentEntityDao.streamChangedFacilityRecords(after).forEach(recordChanges::add);

    return StreamSupport.stream(recordChanges.newIterable().spliterator(), false).map(
        recordChange -> {
          LOG.info("Getting facility by ID: " + recordChange.getId());
          FacilityDTO facilityDTO = findById(recordChange.getId());
          FacilityCompositeDTO compositeDTO = facilityMapper.toFacilityCompositeDTO(facilityDTO);
          addChildren(compositeDTO);

          return new ReplicatedFacilityCompositeDTO(compositeDTO,
              recordChange.getRecordChangeOperation());
        });
  }

  private void addChildren(FacilityCompositeDTO compositeDTO) {
    List<FacilityChildDTO> facilityChildren = clientDao.streamByFacilityId(compositeDTO.getId())
        .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());
    compositeDTO.setChildren(facilityChildren);
  }

  private class RecordChanges {

    private HashMap<String, RecordChangeObject> toBeDeleted = new HashMap<>();
    private HashMap<String, RecordChangeObject> toBeInserted = new HashMap<>();
    private HashMap<String, RecordChangeObject> toBeUpdated = new HashMap<>();

    void add(RecordChangeObject data) {
      if (RecordChangeOperation.D == data.getRecordChangeOperation()) {
        toBeDeleted.put(data.getId(), data);
      } else if (RecordChangeOperation.I == data.getRecordChangeOperation()) {
        toBeInserted.put(data.getId(), data);
      } else if (RecordChangeOperation.U == data.getRecordChangeOperation()) {
        toBeUpdated.put(data.getId(), data);
      }
    }

    Iterable<RecordChangeObject> newIterable() {
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
