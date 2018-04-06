package gov.ca.cwds.cals.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

import gov.ca.cwds.data.legacy.cms.dao.CaseDao;
import gov.ca.cwds.data.legacy.cms.dao.ReferralDao;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import gov.ca.cwds.data.legacy.cms.entity.Referral;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;

/**
 * Unit test for testing finding worker assigned to the child.
 *
 * @author CWDS CALS-1
 */
public class ChildAssignedWorkerServiceTest {

  private static final String TEST_CLIENT_ID = "testClientId";
  private static final String FIRST_NAME = "testFirstName";
  private static final String LAST_NAME = "testLastName";
  private static final String CASE_TEST_ID = "case1";
  private static final String REFERRAL_ID_1 = "1";
  private static final String REFERRAL_ID_2 = "2";
  private static final String REFERRAL_ID_3 = "3";
  private static final String REFERRAL_ID_4 = "4";

  @Spy
  @InjectMocks
  private ChildAssignedWorkerService childAssignedWorkerService; // "Class Under Test"

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void type() {
    assertThat(ChildAssignedWorkerService.class, notNullValue());
  }

  @Test
  public void instantiation() {
    assertThat(childAssignedWorkerService, notNullValue());
  }

  @Test
  public void testGetStaffPersonFromActiveCase() {
    List<Case> activeCases = new LinkedList<>();

    StaffPerson activeCaseStaffPerson = new StaffPerson();
    activeCaseStaffPerson.setFirstName(FIRST_NAME);
    activeCaseStaffPerson.setLastName(LAST_NAME);

    Case case1 = new Case();
    case1.setIdentifier(CASE_TEST_ID);
    case1.setStaffPerson(activeCaseStaffPerson);

    activeCases.add(case1);

    CaseDao caseDaoMock = Mockito.mock(CaseDao.class);
    ReferralDao referralDaoMock = Mockito.mock(ReferralDao.class);

    doReturn(activeCases).when(caseDaoMock).findActiveByClient(TEST_CLIENT_ID);

    Whitebox.setInternalState(childAssignedWorkerService, "caseDao", caseDaoMock);
    Whitebox.setInternalState(childAssignedWorkerService, "referralDao", referralDaoMock);

    Optional<StaffPerson> staffPerson = childAssignedWorkerService
        .findAssignedWorkerForClient(TEST_CLIENT_ID);
    Assert.assertNotNull(staffPerson);
    Assert.assertTrue(staffPerson.isPresent());
    Assert.assertEquals(FIRST_NAME, staffPerson.get().getFirstName());
    Assert.assertEquals(LAST_NAME, staffPerson.get().getLastName());
  }

  @Test
  public void testSortActiveReferralsByReceivedDate() {
    List<Referral> referrals = new LinkedList<>();
    Referral testReferral1 = new Referral();
    testReferral1.setId(REFERRAL_ID_1);
    testReferral1.setReceivedDate(LocalDate.ofYearDay(2015, 100));
    Referral testReferral2 = new Referral();
    testReferral2.setReceivedDate(LocalDate.ofYearDay(2017, 100));
    testReferral2.setId(REFERRAL_ID_2);
    Referral testReferral3 = new Referral();
    testReferral3.setReceivedDate(LocalDate.ofYearDay(2013, 100));
    testReferral3.setId(REFERRAL_ID_3);

    referrals.add(0, testReferral1);
    referrals.add(1, testReferral2);
    referrals.add(2, testReferral3);

    List<Referral> sortedReferralList = childAssignedWorkerService.sortReferralList(referrals);

    Assert.assertEquals(REFERRAL_ID_2, sortedReferralList.get(0).getId());
    Assert.assertEquals(REFERRAL_ID_1, sortedReferralList.get(1).getId());
    Assert.assertEquals(REFERRAL_ID_3, sortedReferralList.get(2).getId());
  }

  @Test
  public void testSortActiveReferralsByReceivedTime() {
    List<Referral> referrals = new LinkedList<>();
    Referral testReferral1 = new Referral();
    testReferral1.setId(REFERRAL_ID_1);
    testReferral1.setReceivedDate(LocalDate.now());
    testReferral1.setReceivedTime(LocalTime.now().minus(4, ChronoUnit.HOURS));
    Referral testReferral2 = new Referral();
    testReferral2.setId(REFERRAL_ID_2);
    testReferral2.setReceivedDate(LocalDate.now());
    testReferral2.setReceivedTime(LocalTime.now().minus(3, ChronoUnit.HOURS));
    Referral testReferral3 = new Referral();
    testReferral3.setId(REFERRAL_ID_3);
    testReferral3.setReceivedDate(LocalDate.now());
    testReferral3.setReceivedTime(LocalTime.now().minus(2, ChronoUnit.HOURS));

    referrals.add(0, testReferral1);
    referrals.add(1, testReferral2);
    referrals.add(2, testReferral3);

    List<Referral> sortedReferralList = childAssignedWorkerService.sortReferralList(referrals);

    Assert.assertEquals(REFERRAL_ID_3, sortedReferralList.get(0).getId());
    Assert.assertEquals(REFERRAL_ID_2, sortedReferralList.get(1).getId());
    Assert.assertEquals(REFERRAL_ID_1, sortedReferralList.get(2).getId());
  }


  @Test
  public void testSortClosedReferrals() {
    List<Referral> referrals = new LinkedList<>();
    Referral testReferral1 = new Referral();
    testReferral1.setId(REFERRAL_ID_1);
    testReferral1.setClosureDate(LocalDate.ofYearDay(2013, 10));
    Referral testReferral2 = new Referral();
    testReferral2.setId(REFERRAL_ID_2);
    testReferral2.setClosureDate(LocalDate.ofYearDay(2015, 100));
    Referral testReferral3 = new Referral();
    testReferral3.setId(REFERRAL_ID_3);
    testReferral3.setClosureDate((LocalDate.ofYearDay(2015, 101)));

    referrals.add(0, testReferral1);
    referrals.add(1, testReferral2);
    referrals.add(2, testReferral3);

    List<Referral> sortedReferralList = childAssignedWorkerService.sortReferralList(referrals);

    Assert.assertEquals(REFERRAL_ID_3, sortedReferralList.get(0).getId());
    Assert.assertEquals(REFERRAL_ID_2, sortedReferralList.get(1).getId());
    Assert.assertEquals(REFERRAL_ID_1, sortedReferralList.get(2).getId());
  }

  @Test
  public void testActiveAndClosedReferrals() {
    List<Referral> referrals = new LinkedList<>();
    Referral testReferral1 = new Referral();
    testReferral1.setId(REFERRAL_ID_1);
    testReferral1.setClosureDate(LocalDate.ofYearDay(2012, 10));
    Referral testReferral2 = new Referral();
    testReferral2.setId(REFERRAL_ID_2);
    testReferral2.setClosureDate(LocalDate.ofYearDay(2014, 100));
    testReferral2.setReceivedTime(LocalTime.now().minus(5, ChronoUnit.HOURS));
    Referral testReferral3 = new Referral();
    testReferral3.setId(REFERRAL_ID_3);
    testReferral3.setClosureDate(null);
    testReferral3.setReceivedDate(LocalDate.now());
    testReferral3.setReceivedTime(LocalTime.now());
    Referral testReferral4 = new Referral();
    testReferral4.setId(REFERRAL_ID_4);
    testReferral4.setClosureDate(LocalDate.ofYearDay(2014, 100));
    testReferral4.setReceivedTime(null);

    referrals.add(0, testReferral1);
    referrals.add(1, testReferral2);
    referrals.add(2, testReferral3);
    referrals.add(3, testReferral4);

    List<Referral> sortedReferralList = childAssignedWorkerService.sortReferralList(referrals);

    Assert.assertEquals(REFERRAL_ID_3, sortedReferralList.get(0).getId());
    Assert.assertEquals(REFERRAL_ID_2, sortedReferralList.get(1).getId());
    Assert.assertEquals(REFERRAL_ID_4, sortedReferralList.get(2).getId());
    Assert.assertEquals(REFERRAL_ID_1, sortedReferralList.get(3).getId());
  }

  @Test
  public void testNullReferralList() {
    Assert.assertNull(childAssignedWorkerService.sortReferralList(null));
  }
}
