package gov.ca.cwds.cals.service;

import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS_FFA;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.fas.FasComplaintDao;
import gov.ca.cwds.cals.persistence.dao.fas.FasFfaComplaintDao;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.rest.exception.ExpectedException;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Service to cover facility complaints functionality.
 * @author CWDS CALS API Team
 */

public class ComplaintsService {

  @Inject
  private FasComplaintDao fasComplaintDao;

  @Inject
  private FasFfaComplaintDao fasFfaComplaintDao;

  @Inject
  private ComplaintMapper complaintMapper;

  /**
   * Get complaints by facility id.
   */
  public Set<ComplaintDTO> getComplaintsByFacilityId(String facilityNumber) {
    List<ComplaintReportLic802> facilityComplaints =
        aggregateComplaintsFromDifferentSources(facilityNumber);
    if (CollectionUtils.isEmpty(facilityComplaints)) {
      return Collections.emptySet();
    }
    Set<ComplaintDTO> sortedSet = new TreeSet<>(new DescendingDatesComparator());
    sortedSet.addAll(complaintMapper.complaintsListToComplaintsDTOList(facilityComplaints));
    return sortedSet;
  }

  /**
   * Get complaint by facility id and complaint id.
   */
  public ComplaintDTO getComplaintByFacilityIdAndComplaintId(String facilityId,
      String complaintId) {
    ComplaintReportLic802 complaintReportLic802 = null;
    if (StringUtils.isNumeric(facilityId)) {
      complaintReportLic802 = fasComplaintDao
          .findComplaintByFacilityIdAndComplaintId(facilityId, complaintId);
    }
    if (complaintReportLic802 == null) {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.COMPLAINT_NOT_FOUND_BY_ID, NOT_FOUND);
    } else {
      return complaintMapper.entityToDTO(complaintReportLic802);
    }
  }

  private List<ComplaintReportLic802> aggregateComplaintsFromDifferentSources(
      String facilityNumber) {
    List<ComplaintReportLic802> complaints = getComplaintsFromFas(facilityNumber);
    complaints.addAll(getComplaintsFromFasFfa(facilityNumber));
    return complaints;
  }

  @UnitOfWork(FAS)
  protected List<ComplaintReportLic802> getComplaintsFromFas(String facilityNumber) {
    return fasComplaintDao.findComplaintsByFacilityNumber(facilityNumber);
  }

  @UnitOfWork(FAS_FFA)
  protected List<ComplaintReportLic802> getComplaintsFromFasFfa(String facilityNumber) {
    return fasFfaComplaintDao.findComplaintsByFacilityNumber(facilityNumber);
  }

  static class DescendingDatesComparator implements Comparator<ComplaintDTO> {

    @Override
    public int compare(ComplaintDTO complaint1, ComplaintDTO complaint2) {
      if (complaint1.getComplaintDate() == null) {
        return 1;
      }
      if (complaint2.getComplaintDate() == null) {
        return -1;
      }
      if (complaint2.getComplaintDate().isAfter(complaint1.getComplaintDate())) {
        return 1;
      } else if (complaint2.getComplaintDate().isBefore(complaint1.getComplaintDate())) {
        return -1;
      } else {
        return 0;
      }
    }
  }


}
