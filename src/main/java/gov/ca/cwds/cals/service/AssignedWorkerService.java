package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.data.legacy.cms.dao.CaseDao;
import gov.ca.cwds.data.legacy.cms.dao.ReferralDao;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import gov.ca.cwds.data.legacy.cms.entity.Referral;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service for finding worker assigned to the child.
 *
 * @author CWDS CALS-1
 */
public class AssignedWorkerService {

  @Inject
  private CaseDao caseDao;

  @Inject
  private ReferralDao referralDao;

  public AssignedWorkerService() {
    // default constructor
  }

  public Optional<StaffPerson> findAssignedWorkerForClient(String clientId) {
    List<Case> caseList = caseDao.findActiveByClient(clientId);

    if (!caseList.isEmpty()) {
      return Optional.ofNullable(caseList.get(0)).map(Case::getStaffPerson);
    }

    List<Referral> referralList = referralDao.getReferralsByClientId(clientId);
    List<Referral> modifiableReferralList = new ArrayList<>(referralList);

    if (modifiableReferralList.isEmpty()) {
      return Optional.empty();
    }

    return Optional.ofNullable(sortReferralList(modifiableReferralList).get(0))
        .map(Referral::getPrimaryContactStaffPerson);
  }

  public List<Referral> sortReferralList(List<Referral> referralList) {
    if (referralList.size() > 1) {
      referralList.sort(Comparator
          .comparing(Referral::getClosureDate, Comparator.nullsFirst(Comparator.reverseOrder()))
          .thenComparing(Referral::getReceivedDate, Comparator.nullsLast(Comparator.reverseOrder()))
          .thenComparing(Referral::getReceivedTime, Comparator.nullsLast(Comparator.reverseOrder()))
      );
    }
    return referralList;
  }
}
