package gov.ca.cwds.cals.service;

import static java.util.Arrays.sort;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.service.ComplaintsService.DescendingDatesComparator;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import java.time.LocalDateTime;
import org.junit.Test;

/**
 * Created by Alexander Serbin on 6/4/2018.
 */
public class DescendingDatesComparatorTest {

  @Test
  public void happyPathTest() {
    ComplaintDTO[] complaints = {
        createComplaintByDate(LocalDateTime.of(2015, 5, 30, 11, 30)),
        createComplaintByDate(LocalDateTime.of(2013, 6, 30, 5, 15)),
        createComplaintByDate(LocalDateTime.of(2017, 1, 5, 8, 3))
    };
    sort(complaints, new DescendingDatesComparator());
    assertEquals(3, complaints.length);
    assertEquals(LocalDateTime.of(2017, 1, 5, 8, 3),
        complaints[0].getComplaintDate());
    assertEquals(LocalDateTime.of(2015, 5, 30, 11, 30),
        complaints[1].getComplaintDate());
    assertEquals(LocalDateTime.of(2013, 6, 30, 5, 15),
        complaints[2].getComplaintDate());
  }

  @Test
  public void oneNullDateTest() {
    ComplaintDTO[] complaints = {createComplaintByDate(null),
        createComplaintByDate(LocalDateTime.of(2013, 6, 30, 5, 15)),
        createComplaintByDate(LocalDateTime.of(2017, 1, 5, 8, 3))
    };
    sort(complaints, new DescendingDatesComparator());
    assertEquals(3, complaints.length);
    assertEquals(LocalDateTime.of(2017, 1, 5, 8, 3),
        complaints[0].getComplaintDate());
    assertEquals(LocalDateTime.of(2013, 6, 30, 5, 15),
        complaints[1].getComplaintDate());
    assertEquals(null, complaints[2].getComplaintDate());
  }

  @Test
  public void twoNullDatesTest() {
    ComplaintDTO[] complaints = {createComplaintByDate(null), createComplaintByDate(null),
        createComplaintByDate(LocalDateTime.of(2017, 1, 5, 8, 3))
    };
    sort(complaints, new DescendingDatesComparator());
    assertEquals(3, complaints.length);
    assertEquals(LocalDateTime.of(2017, 1, 5, 8, 3),
        complaints[0].getComplaintDate());
    assertEquals(null, complaints[1].getComplaintDate());
    assertEquals(null, complaints[2].getComplaintDate());
  }

  @Test
  public void threeNullDatesTest() {
    ComplaintDTO[] complaints = {createComplaintByDate(null), createComplaintByDate(null),
        createComplaintByDate(null)};
    sort(complaints, new DescendingDatesComparator());
    assertEquals(3, complaints.length);
    assertEquals(null, complaints[0].getComplaintDate());
    assertEquals(null, complaints[1].getComplaintDate());
    assertEquals(null, complaints[2].getComplaintDate());
  }

  @Test
  public void EqualDatesTest() {
    LocalDateTime now = LocalDateTime.now();
    ComplaintDTO[] complaints = {createComplaintByDate(now), createComplaintByDate(now),
        createComplaintByDate(now)};
    sort(complaints, new DescendingDatesComparator());
    assertEquals(3, complaints.length);
    assertEquals(now, complaints[0].getComplaintDate());
    assertEquals(now, complaints[1].getComplaintDate());
    assertEquals(now, complaints[2].getComplaintDate());
  }

  private static ComplaintDTO createComplaintByDate(LocalDateTime complaintDate) {
    ComplaintDTO complaintDTO = new ComplaintDTO();
    complaintDTO.setComplaintDate(complaintDate);
    return complaintDTO;
  }

}