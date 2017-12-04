package gov.ca.cwds.cals.service.builder;

import static gov.ca.cwds.cals.Utils.StaffPerson.getStaffPersonId;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.Utils.Applicant;
import gov.ca.cwds.cals.Utils.StaffPerson;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.RelationshipToApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.mapper.OtherAdultsInPlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.PlacementHomeMapper;
import gov.ca.cwds.cals.service.mapper.SubstituteCareProviderMapper;
import gov.ca.cwds.cms.data.access.CWSIdentifier;
import gov.ca.cwds.cms.data.access.parameter.OtherAdultInHomeParameterObject;
import gov.ca.cwds.cms.data.access.parameter.OtherChildInHomeParameterObject;
import gov.ca.cwds.cms.data.access.parameter.PlacementHomeParameterObject;
import gov.ca.cwds.cms.data.access.parameter.SCPParameterObject;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.PhoneContactDetail;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import java.time.LocalDateTime;
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

public class PlacementHomeParameterObjectBuilder {

  private final PlacementHomeParameterObject placementHomeParameterObject;
  private RFA1aFormDTO form;
  private Map<Long, SubstituteCareProvider> rfaApplicantIdsMap = new HashMap<>();

  @Inject
  private PlacementHomeMapper placementHomeMapper;

  @Inject
  private SubstituteCareProviderMapper substituteCareProviderMapper;

  @Inject
  private OtherAdultsInPlacementHomeMapper otherAdultsInPlacementHomeMapper;

  public PlacementHomeParameterObjectBuilder() {
    placementHomeParameterObject = new PlacementHomeParameterObject(getStaffPersonId());
  }

  public void setForm(RFA1aFormDTO form) {
    this.form = form;
  }

  private Set<? extends CWSIdentifier> getHomeLanguages(RFA1aFormDTO form) {
    return Optional.ofNullable(form.getResidence())
        .map(ResidenceDTO::getHomeLanguages).orElse(Collections.emptySet());
  }

  private PlacementHome mapRfaFormToPlacementHome(RFA1aFormDTO form) {
    return placementHomeMapper.toPlacementHome(
        form, Utils.Address.getByType(form, Constants.AddressTypes.RESIDENTIAL));
  }

  public void setEntity() {
    placementHomeParameterObject.setEntity(mapRfaFormToPlacementHome(form));
  }

  public void setStaffPersonId() {
    placementHomeParameterObject.setStaffPersonId(StaffPerson.getStaffPersonId());
  }

  public void setHomeLanguages() {
    placementHomeParameterObject.setHomeLanguages(getHomeLanguages(form));
  }

  public void setSubstituteCareProviders() {
    List<ApplicantDTO> applicants = Optional.ofNullable(form.getApplicants())
        .orElse(Collections.emptyList());
    for (ApplicantDTO applicant : applicants) {
      SCPParameterObject substituteCareProviderParameterObject =
          buildSubstituteCareProviderParameterObject(form, applicant);
      rfaApplicantIdsMap.put(applicant.getId(), substituteCareProviderParameterObject.getEntity());
      placementHomeParameterObject.addSCPParameterObject(substituteCareProviderParameterObject);
    }
  }

  private SCPParameterObject buildSubstituteCareProviderParameterObject(
      RFA1aFormDTO form, ApplicantDTO applicant) {
    SCPParameterObject parameterObject = new SCPParameterObject(getStaffPersonId());
    parameterObject.setStaffPersonId(getStaffPersonId());
    parameterObject.setPrimaryApplicant(Applicant.isPrimary(form, applicant));
    parameterObject.setPhoneNumbers(mapPhoneContactDetails(applicant));
    parameterObject.setEthnicity(applicant.getEthnicity());
    parameterObject.setOtherStatesOfLiving(applicant.getRfa1bForm().getOtherStatesOfLiving());
    SubstituteCareProvider substituteCareProvider = mapRFAEntitiesToSCP(applicant);
    parameterObject.setEntity(substituteCareProvider);
    return parameterObject;
  }

  private List<PhoneContactDetail> mapPhoneContactDetails(ApplicantDTO applicantDTO) {
    final String staffPersonId = getStaffPersonId();
    if (CollectionUtils.isNotEmpty(applicantDTO.getPhones())) {
      return applicantDTO.getPhones().stream().map(phoneNumber -> {
            PhoneContactDetail phoneContactDetail = new PhoneContactDetail();
            phoneContactDetail.setEstblshCd("S");
            phoneContactDetail.setPhoneNo(Long.parseLong(phoneNumber.getNumber()));
            if (StringUtils.isNotEmpty(phoneNumber.getExtension())) {
              phoneContactDetail.setPhextNo(Integer.valueOf(phoneNumber.getExtension()));
            }
            phoneContactDetail.setPhnTypCd(phoneNumber.getPhoneType().getCwsShortCode());
            phoneContactDetail.setLstUpdId(staffPersonId);
            phoneContactDetail.setLstUpdTs(LocalDateTime.now());
            return phoneContactDetail;
          }
      ).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  private SubstituteCareProvider mapRFAEntitiesToSCP(ApplicantDTO applicant) {
    SubstituteCareProvider substituteCareProvider =
        substituteCareProviderMapper.toSubstituteCareProvider(applicant);

    substituteCareProviderMapper
        .toSubstituteCareProvider(substituteCareProvider, applicant.getRfa1bForm());

    RFAAddressDTO residentialAddress = Utils.Address
        .getByType(form, Constants.AddressTypes.RESIDENTIAL);
    substituteCareProviderMapper.toSubstituteCareProviderFromResidentialAddress(
        substituteCareProvider, residentialAddress);

    RFAAddressDTO mailingAddress = Utils.Address.getByType(form, Constants.AddressTypes.MAIL);
    substituteCareProviderMapper.toSubstituteCareProviderFromMailingAddress(
        substituteCareProvider, mailingAddress);

    return substituteCareProvider;
  }

  public void setOtherChildrenInHome() {
    for (MinorChildDTO minorChildDTO : form.getMinorChildren()) {
      OtherChildrenInPlacementHome otherChildInHome = new OtherChildrenInPlacementHome();
      otherChildInHome.setBirthDt(minorChildDTO.getDateOfBirth());
      otherChildInHome.setGenderCd(minorChildDTO.getGender().getCwsShortCode());
      otherChildInHome.setOthchldNm("Undisclosed");
      OtherChildInHomeParameterObject parameterObject = new OtherChildInHomeParameterObject(
          getStaffPersonId());
      parameterObject.setEntity(otherChildInHome);
      placementHomeParameterObject.addOtherChildrenInHomeParameterObject(parameterObject);
      prepareRelationships(minorChildDTO, parameterObject);
    }
  }

  private void prepareRelationships(MinorChildDTO minorChildDTO,
      OtherChildInHomeParameterObject parameterObject) {
    for (RelationshipToApplicantDTO relationshipToApplicantDTO : minorChildDTO
        .getRelationshipToApplicants()) {
      SubstituteCareProvider substituteCareProvider = rfaApplicantIdsMap
          .get(relationshipToApplicantDTO.getApplicantId());
      OtherPeopleScpRelationship relationship = new OtherPeopleScpRelationship();
      relationship.setClntrelc((short) 0);
      relationship.setSubstituteCareProvider(substituteCareProvider);
      parameterObject.addRelationship(relationship);
    }
  }

  public void setOtherAdultsInPlacementHome() {
    for (OtherAdultDTO otherAdultDTO : form.getOtherAdults()) {
      OtherAdultsInPlacementHome otherAdult = otherAdultsInPlacementHomeMapper
          .toOtherAdult(otherAdultDTO);
      OtherAdultInHomeParameterObject parameterObject = new OtherAdultInHomeParameterObject(
          getStaffPersonId());
      parameterObject.setEntity(otherAdult);
      prepareRelationships(otherAdultDTO, parameterObject);
      prepareOutOfStateChecks(otherAdultDTO, parameterObject);
      placementHomeParameterObject.addOtherAdultInHomeParameterObject(parameterObject);
    }
  }

  private void prepareOutOfStateChecks(OtherAdultDTO otherAdultDTO,
      OtherAdultInHomeParameterObject parameterObject) {
    if (otherAdultDTO.getRfa1bForm() != null &&
        CollectionUtils.isNotEmpty(otherAdultDTO.getRfa1bForm().getOtherStatesOfLiving())) {
      for (StateType state : otherAdultDTO.getRfa1bForm().getOtherStatesOfLiving()) {
        OutOfStateCheck outOfStateCheck = new OutOfStateCheck();
        outOfStateCheck.setStateC((short) state.getCwsId());
        parameterObject.addOutOfStateCheck(outOfStateCheck);
      }
    }
  }

  private void prepareRelationships(OtherAdultDTO minorChildDTO,
      OtherAdultInHomeParameterObject parameterObject) {
    for (RelationshipToApplicantDTO relationship : minorChildDTO.getRelationshipToApplicants()) {
      SubstituteCareProvider substituteCareProvider = rfaApplicantIdsMap
          .get(relationship.getApplicantId());
      OtherPeopleScpRelationship otherPeopleScpRelationship = new OtherPeopleScpRelationship();
      otherPeopleScpRelationship.setClntrelc((short) 0);
      otherPeopleScpRelationship.setSubstituteCareProvider(substituteCareProvider);
      parameterObject.addRelationship(otherPeopleScpRelationship);
    }
  }

  public PlacementHomeParameterObject getResult() {
    return placementHomeParameterObject;
  }

}
