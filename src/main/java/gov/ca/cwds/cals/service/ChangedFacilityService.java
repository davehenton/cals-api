package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.CompositeIterator;
import gov.ca.cwds.cals.RecordChangeOperation;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.fas.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileFasDao;
import gov.ca.cwds.cals.persistence.dao.fas.RecordChangeFasDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.persistence.dao.lis.RecordChangeLisDao;
import gov.ca.cwds.cals.persistence.model.RecordChange;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.cms.RecordChangeCwsCmsDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.ChangedFacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2
 */
public class ChangedFacilityService extends FacilityService {

  private static final Logger LOG = LoggerFactory.getLogger(ChangedFacilityService.class);

  private RecordChangeCwsCmsDao recordChangeCwsCmsDao;
  private RecordChangeLisDao recordChangeLisDao;
  private RecordChangeFasDao recordChangeFasDao;

  @Inject
  @SuppressWarnings("squid:S00107")
  public ChangedFacilityService(
      LisFacFileLisDao lisFacFileLisDao,
      LisFacFileFasDao lisFacFileFasDao,
      PlacementHomeDao placementHomeDao,
      LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao, FacilityMapper facilityMapper,
      FasFacilityMapper fasFacilityMapper,
      RecordChangeCwsCmsDao recordChangeCwsCmsDao,
      RecordChangeLisDao recordChangeLisDao,
      RecordChangeFasDao recordChangeFasDao,
      ClientDao clientDao, FacilityChildMapper facilityChildMapper,
      InspectionDao inspectionDao, ComplaintReportLic802Dao complaintReportLic802Dao) {
    super(lisFacFileLisDao, lisFacFileFasDao, placementHomeDao, lpaInformationDao, countiesDao,
        facilityMapper, fasFacilityMapper, clientDao, facilityChildMapper, inspectionDao,
        complaintReportLic802Dao);
    this.recordChangeCwsCmsDao = recordChangeCwsCmsDao;
    this.recordChangeLisDao = recordChangeLisDao;
    this.recordChangeFasDao = recordChangeFasDao;
  }

  public Stream<ChangedFacilityDTO> changedFacilitiesStream(Date after) {
    RecordChanges cwsCmsRecordChanges = new RecordChanges();
    recordChangeCwsCmsDao.streamChangedFacilityRecords(after).forEach(cwsCmsRecordChanges::add);

    RecordChanges lisFasRecordChanges = new RecordChanges();
    recordChangeLisDao.streamChangedFacilityRecords(after).forEach(lisFasRecordChanges::add);
    recordChangeFasDao.streamChangedFacilityRecords(after).forEach(lisFasRecordChanges::add);

    return Stream.concat(cwsCmsRecordChanges.newStream(), lisFasRecordChanges.newStream())
        .map(recordChange -> {
          LOG.info("Getting facility by ID: {}", recordChange.getId());
          FacilityDTO facilityDTO = findExpandedById(recordChange.getId());
          LOG.info("Find facility by ID {} returned FacilityDTO with ID {}", recordChange.getId(),
              facilityDTO.getId());
          return new ChangedFacilityDTO(facilityDTO, recordChange.getRecordChangeOperation());
        })
        .filter(facilityDTO -> {
          if (facilityDTO.getId() == null) {
            LOG.error("Find facility by ID returned incorrect FacilityDTO with NULL id. Skipped.");
            return false;
          } else {
            return true;
          }
        });
  }

  private static class RecordChanges {

    private HashMap<String, RecordChange> toBeDeleted = new HashMap<>();
    private HashMap<String, RecordChange> toBeInserted = new HashMap<>();
    private HashMap<String, RecordChange> toBeUpdated = new HashMap<>();

    void add(RecordChange data) {
      if (RecordChangeOperation.D == data.getRecordChangeOperation()) {
        toBeDeleted.put(data.getId(), data);
      } else if (RecordChangeOperation.I == data.getRecordChangeOperation()) {
        toBeInserted.put(data.getId(), data);
      } else if (RecordChangeOperation.U == data.getRecordChangeOperation()) {
        toBeUpdated.put(data.getId(), data);
      }
    }

    private Iterable<RecordChange> newIterable() {
      compact();
      return () -> new CompositeIterator<>(
          toBeDeleted.values().iterator(),
          toBeInserted.values().iterator(),
          toBeUpdated.values().iterator()
      );
    }

    Stream<RecordChange> newStream() {
      return StreamSupport.stream(this.newIterable().spliterator(), false);
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
