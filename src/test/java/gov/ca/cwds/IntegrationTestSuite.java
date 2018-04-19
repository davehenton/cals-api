package gov.ca.cwds;

import gov.ca.cwds.cals.web.rest.DictionariesResourceTest;
import gov.ca.cwds.cals.web.rest.ExceptionHandlingResponseTest;
import gov.ca.cwds.cals.web.rest.packet.RFAPacketResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.DraftApplicationsTest;
import gov.ca.cwds.cals.web.rest.rfa.LIC198bResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aAdoptionHistoryResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsDeclarationResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsHistoryResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aApplicantsRelationshipResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aChildDesiredResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aCoreSubmitApplicationTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aCornerCasesSubmitApplicationTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aFormsResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aMinorChildrenResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aOtherAdultsResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aPrintPDFTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aReferencesResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aRequiredFieldsValidationTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aResidenceResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1aSubmitValidationTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1bResourceTest;
import gov.ca.cwds.cals.web.rest.rfa.RFA1cResourceTest;
import gov.ca.cwds.cals.web.rest.system.SystemInformationResourceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author CWDS TPT-2 Team
 */


@RunWith(Suite.class)
@SuiteClasses({
    // Facility
    //FacilityChildAuthorizationTest.class,
    //FacilityChildResourceTest.class,
    //FacilityComplaintResourceTest.class,
    //FacilityInspectionsTest.class,
    //FacilityResourceTest.class,

    //Packet
    RFAPacketResourceTest.class,

    //RFA
    DraftApplicationsTest.class,
    LIC198bResourceTest.class,
    RFA1aAdoptionHistoryResourceTest.class,
    RFA1aApplicantResourceTest.class,
    RFA1aApplicantsDeclarationResourceTest.class,
    RFA1aApplicantsHistoryResourceTest.class,
    RFA1aApplicantsRelationshipResourceTest.class,
    RFA1aChildDesiredResourceTest.class,
    RFA1aCoreSubmitApplicationTest.class,
    RFA1aCornerCasesSubmitApplicationTest.class,
    RFA1aFormsResourceTest.class,
    RFA1aMinorChildrenResourceTest.class,
    RFA1aOtherAdultsResourceTest.class,
    RFA1aPrintPDFTest.class,
    RFA1aReferencesResourceTest.class,
    RFA1aRequiredFieldsValidationTest.class,
    RFA1aResidenceResourceTest.class,
    RFA1aSubmitValidationTest.class,
    RFA1bResourceTest.class,
    RFA1cResourceTest.class,

    //System
    SystemInformationResourceTest.class,


    DictionariesResourceTest.class,
    ExceptionHandlingResponseTest.class

})
public class IntegrationTestSuite {

}
