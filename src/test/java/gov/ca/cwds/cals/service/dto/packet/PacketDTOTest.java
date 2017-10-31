package gov.ca.cwds.cals.service.dto.packet;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.junit.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * @author CWDS TPT-2
 */
public class PacketDTOTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Spy
  @InjectMocks
  private PacketDTO packetDTO; // "Class Under Test"

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void type() throws Exception {
    assertThat(PacketDTO.class, notNullValue());
  }

  @Test
  public void instantiation() throws Exception {
    assertThat(packetDTO, notNullValue());
  }

  @Test
  public void testConstructorWithParameters() throws Exception {

    List<SectionSummaryDTO> rfa1aSections = new ArrayList<>();
    List<PersonSummaryDTO> applicants = new ArrayList<>();
    List<PersonSummaryDTO> otherAdults = new ArrayList<>();
    List<PersonSummaryDTO> adultChildren = new ArrayList<>();

    packetDTO = new PacketDTO(rfa1aSections, applicants, otherAdults, adultChildren);
    assertNotNull(packetDTO);
    assertNotNull(packetDTO.getApplicants());
    assertNotNull(packetDTO.getAdultChildren());
    assertNotNull(packetDTO.getOtherAdults());
    assertNotNull(packetDTO.getRfa1aSections());
    assertTrue(packetDTO.getApplicants().isEmpty());
    assertTrue(packetDTO.getAdultChildren().isEmpty());
    assertTrue(packetDTO.getOtherAdults().isEmpty());
    assertTrue(packetDTO.getRfa1aSections().isEmpty());
  }
}