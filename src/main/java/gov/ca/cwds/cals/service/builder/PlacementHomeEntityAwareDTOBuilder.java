package gov.ca.cwds.cals.service.builder;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.mapper.CountyLicenseCaseMapper;
import gov.ca.cwds.cals.service.mapper.OtherAdultsInPlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.SubstituteCareProviderMapper;
import gov.ca.cwds.cals.util.RfaAddressUtil;
import gov.ca.cwds.cals.util.Utils.Applicant;
import gov.ca.cwds.cms.data.access.CWSIdentifier;
import gov.ca.cwds.cms.data.access.dto.OtherAdultInHomeEntityAwareDTO;
import gov.ca.cwds.cms.data.access.dto.OtherChildInHomeEntityAwareDTO;
import gov.ca.cwds.cms.data.access.dto.PlacementHomeEntityAwareDTO;
import gov.ca.cwds.cms.data.access.dto.SCPEntityAwareDTO;
import gov.ca.cwds.data.legacy.cms.entity.CountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.EmergencyContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.PhoneContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CWDS CALS API Team
 */

public class PlacementHomeEntityAwareDTOBuilder {

  private final PlacementHomeEntityAwareDTO placementHomeEntityAwareDTO;
  private RFA1aFormDTO form;
  private Map<Long, SubstituteCareProvider> rfaApplicantIdsMap = new HashMap<>();

  @Inject
  private PlacementHomeMapper placementHomeMapper;

  @Inject
  private SubstituteCareProviderMapper substituteCareProviderMapper;

  @Inject
  private OtherAdultsInPlacementHomeMapper otherAdultsInPlacementHomeMapper;

  @Inject
  private CountyLicenseCaseMapper countyLicenseCaseMapper;

  public PlacementHomeEntityAwareDTOBuilder() {
    placementHomeEntityAwareDTO = new PlacementHomeEntityAwareDTO();
  }

  public PlacementHomeEntityAwareDTOBuilder appendForm(RFA1aFormDTO form) {
    this.form = form;
    return this;
  }

  private Set<? extends CWSIdentifier> getHomeLanguages(RFA1aFormDTO form) {
    return Optional.ofNullable(form.getResidence())
        .map(ResidenceDTO::getHomeLanguages).orElse(Collections.emptySet());
  }

  private PlacementHome mapRfaFormToPlacementHome(RFA1aFormDTO form) {
    return placementHomeMapper.toPlacementHome(
        form, RfaAddressUtil.getByType(form, Constants.AddressTypes.RESIDENTIAL));
  }

  public PlacementHomeEntityAwareDTOBuilder appendEntity() {
    placementHomeEntityAwareDTO.setEntity(mapRfaFormToPlacementHome(form));
    return this;
  }

  public PlacementHomeEntityAwareDTOBuilder appendHomeLanguages() {
    placementHomeEntityAwareDTO.setHomeLanguages(getHomeLanguages(form));
    return this;
  }

  public PlacementHomeEntityAwareDTOBuilder appendSubstituteCareProviders() {
    List<ApplicantDTO> applicants = Optional.ofNullable(form.getApplicants())
        .orElse(Collections.emptyList());
    for (ApplicantDTO applicant : applicants) {
      SCPEntityAwareDTO scpEntityAwareDTO =
          buildSubstituteCareProviderParameterObject(form, applicant);
      rfaApplicantIdsMap.put(applicant.getId(), scpEntityAwareDTO.getEntity());
      placementHomeEntityAwareDTO.addSCPParameterObject(scpEntityAwareDTO);
    }
    return this;
  }

  private SCPEntityAwareDTO buildSubstituteCareProviderParameterObject(
      RFA1aFormDTO form, ApplicantDTO applicant) {
    SCPEntityAwareDTO entityAwareDTO = new SCPEntityAwareDTO();
    entityAwareDTO.setPrimaryApplicant(Applicant.isPrimary(form, applicant));
    entityAwareDTO.setPhoneNumbers(mapPhoneContactDetails(applicant));
    entityAwareDTO.setEthnicityList(Arrays.asList(applicant.getEthnicity()));
    entityAwareDTO.setOtherStatesOfLiving(applicant.getRfa1bForm().getOtherStatesOfLiving());
    SubstituteCareProvider substituteCareProvider = mapRFAEntitiesToSCP(applicant);
    entityAwareDTO.setEntity(substituteCareProvider);
    return entityAwareDTO;
  }

  private List<PhoneContactDetail> mapPhoneContactDetails(ApplicantDTO applicantDTO) {
    if (CollectionUtils.isNotEmpty(applicantDTO.getPhones())) {
      return applicantDTO.getPhones().stream().map(phoneNumber -> {
            PhoneContactDetail phoneContactDetail = new PhoneContactDetail();
            phoneContactDetail.setEstblshCd("S");
            phoneContactDetail.setPhoneNo(Long.parseLong(phoneNumber.getNumber()));
            if (StringUtils.isNotEmpty(phoneNumber.getExtension())) {
              phoneContactDetail.setPhextNo(Integer.valueOf(phoneNumber.getExtension()));
            }
            phoneContactDetail.setPhnTypCd(phoneNumber.getPhoneType().getCwsShortCode());
            phoneContactDetail.setLstUpdId(PrincipalUtils.getStaffPersonId());
            phoneContactDetail.setLstUpdTs(LocalDateTime.now());
            return phoneContactDetail;
          }
      ).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  private SubstituteCareProvider mapRFAEntitiesToSCP(ApplicantDTO applicant) {
    SubstituteCareProvider substituteCareProvider =
        substituteCareProviderMapper.toSubstituteCareProvider(applicant);

    substituteCareProviderMapper
        .toSubstituteCareProvider(substituteCareProvider, applicant.getRfa1bForm());

    RFAAddressDTO residentialAddress = RfaAddressUtil
        .getByType(form, Constants.AddressTypes.RESIDENTIAL);
    substituteCareProviderMapper.toSubstituteCareProviderFromResidentialAddress(
        substituteCareProvider, residentialAddress);

    RFAAddressDTO mailingAddress = RfaAddressUtil.getByType(form, Constants.AddressTypes.MAIL);
    substituteCareProviderMapper.toSubstituteCareProviderFromMailingAddress(
        substituteCareProvider, mailingAddress);

    return substituteCareProvider;
  }

  public PlacementHomeEntityAwareDTOBuilder appendOtherChildrenInHome() {
    Optional.ofNullable(form.getMinorChildren()).ifPresent(minorChildren ->
        minorChildren.forEach(minorChildDTO -> {
          OtherChildrenInPlacementHome otherChildInHome = new OtherChildrenInPlacementHome();
          otherChildInHome.setBirthDt(minorChildDTO.getDateOfBirth());
          otherChildInHome.setGenderCd(minorChildDTO.getGender().getCwsShortCode());
          otherChildInHome.setOthchldNm("Undisclosed");
          OtherChildInHomeEntityAwareDTO entityAwareDTO = new OtherChildInHomeEntityAwareDTO();
          entityAwareDTO.setEntity(otherChildInHome);
          placementHomeEntityAwareDTO.addOtherChildrenInHomeParameterObject(entityAwareDTO);
          prepareRelationships(minorChildDTO, entityAwareDTO);
        })
    );
    return this;
  }

  private void prepareRelationships(MinorChildDTO minorChildDTO,
      OtherChildInHomeEntityAwareDTO entityAwareDTO) {
    Optional.ofNullable(minorChildDTO.getRelationshipToApplicants())
        .ifPresent(relationshipToApplicantDTOs ->
            relationshipToApplicantDTOs.forEach(relationshipToApplicantDTO -> {
              SubstituteCareProvider substituteCareProvider = rfaApplicantIdsMap
                  .get(relationshipToApplicantDTO.getApplicantId());
              OtherPeopleScpRelationship relationship = new OtherPeopleScpRelationship();
              relationship.setClntrelc((short) 0);
              relationship.setSubstituteCareProvider(substituteCareProvider);
              entityAwareDTO.addRelationship(relationship);
            })
        );
  }

  public PlacementHomeEntityAwareDTOBuilder appendOtherAdultsInPlacementHome() {
    Optional.ofNullable(form.getOtherAdults()).ifPresent(otherAdultDTOs ->
        otherAdultDTOs.forEach(otherAdultDTO -> {
          OtherAdultsInPlacementHome otherAdult = otherAdultsInPlacementHomeMapper
              .toOtherAdult(otherAdultDTO);
          OtherAdultInHomeEntityAwareDTO entityAwareDTO = new OtherAdultInHomeEntityAwareDTO();
          entityAwareDTO.setEntity(otherAdult);
          prepareRelationships(otherAdultDTO, entityAwareDTO);
          prepareOutOfStateChecks(otherAdultDTO, entityAwareDTO);
          placementHomeEntityAwareDTO.addOtherAdultInHomeParameterObject(entityAwareDTO);
        })
    );
    return this;
  }

  private void prepareOutOfStateChecks(OtherAdultDTO otherAdultDTO,
      OtherAdultInHomeEntityAwareDTO entityAwareDTO) {
    if (otherAdultDTO.getRfa1bForm() != null &&
        CollectionUtils.isNotEmpty(otherAdultDTO.getRfa1bForm().getOtherStatesOfLiving())) {
      for (StateType state : otherAdultDTO.getRfa1bForm().getOtherStatesOfLiving()) {
        OutOfStateCheck outOfStateCheck = new OutOfStateCheck();
        outOfStateCheck.setStateC((short) state.getCwsId());
        entityAwareDTO.addOutOfStateCheck(outOfStateCheck);
      }
    }
  }

  private void prepareRelationships(OtherAdultDTO minorChildDTO,
      OtherAdultInHomeEntityAwareDTO entityAwareDTO) {
    Optional.ofNullable(minorChildDTO.getRelationshipToApplicants())
        .ifPresent(relationshipToApplicantDTOs ->
            relationshipToApplicantDTOs.forEach(relationship -> {
              SubstituteCareProvider substituteCareProvider = rfaApplicantIdsMap
                  .get(relationship.getApplicantId());
              OtherPeopleScpRelationship otherPeopleScpRelationship = new OtherPeopleScpRelationship();
              otherPeopleScpRelationship.setClntrelc((short) 0);
              otherPeopleScpRelationship.setSubstituteCareProvider(substituteCareProvider);
              entityAwareDTO.addRelationship(otherPeopleScpRelationship);
            })
        );
  }

  public PlacementHomeEntityAwareDTO getPlacementHomeEntityAwareDTO() {
    return placementHomeEntityAwareDTO;
  }

  public PlacementHomeEntityAwareDTOBuilder appendEmergencyContactDetail() {
    EmergencyContactDetail emergencyContactDetail = new EmergencyContactDetail();

    //this is bussiness information
    //FOREIGN_ADDRESS_IND_VAR - This indicator variable is used to indicate
    // if there are any occurrences of FOREIGN ADDRESSes related to this
    // EMERGENCY CONTACT DETAIL. This will save unnecessary processing time
    // from searching for information that does not exist in the database.
    emergencyContactDetail.setFrgAdrtB(Constants.N);

    placementHomeEntityAwareDTO.setEmergencyContactDetail(emergencyContactDetail);
    return this;
  }

  public PlacementHomeEntityAwareDTOBuilder appendCountyLicenseCase() {
    CountyLicenseCase countyLicenseCase = countyLicenseCaseMapper.toCountyLicenseCase(form);
    placementHomeEntityAwareDTO.setCountyLicenseCase(countyLicenseCase);
    return this;
  }
}
