package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.Validation.FORM_SUBMISSION_VALIDATION_SESSION;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.Utils.Id;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.dao.calsns.XaRFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.mapper.rfa.RFA1aFormMapper;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.drools.DroolsConfiguration;
import gov.ca.cwds.drools.DroolsService;
import gov.ca.cwds.drools.validation.DroolsFieldValidationConfiguration;
import gov.ca.cwds.rest.exception.BusinessValidationException;
import gov.ca.cwds.rest.exception.ExpectedException;
import gov.ca.cwds.rest.exception.IssueDetails;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.setup.Environment;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormService
    extends TypedCrudServiceAdapter<RFA1aFormsParameterObject, RFA1aFormDTO, RFA1aFormDTO> {

  private static final Logger LOG = LoggerFactory
      .getLogger(RFA1aFormService.class);

  @Inject
  private RFA1aFormsDao rfa1AFormsDao;

  @Inject
  private XaRFA1aFormsDao xaRfa1AFormsDao;

  @Inject
  private RFA1aFormMapper rfa1aFomMapper;

  @Inject
  private FacilityService facilityService;

  @Inject
  private DroolsService droolsService;

  @Inject
  private Environment environment;

  public RFA1aFormService() {
    // default constructor
  }

  @UnitOfWork(CALSNS)
  @Override
  public RFA1aFormDTO create(RFA1aFormDTO formDTO) {

    RFA1aForm form = new RFA1aForm();
    rfa1aFomMapper.toRFA1aForm(form, formDTO);

    String staffPersonId = Id.getStaffPersonId();
    LocalDateTime now = LocalDateTime.now();
    form.setCreateDateTime(now);
    form.setCreateUserId(staffPersonId);
    form.setUpdateDateTime(now);
    form.setUpdateUserId(staffPersonId);
    form.setStatus(RFAApplicationStatus.DRAFT);
    form = rfa1AFormsDao.create(form);

    return rfa1aFomMapper.toRFA1aFormDTO(form);
  }

  @UnitOfWork(CALSNS)
  @Override
  public RFA1aFormDTO find(RFA1aFormsParameterObject parameterObject) {
    RFA1aForm form = rfa1AFormsDao.find(parameterObject.getFormId());

    RFA1aFormDTO formDTO;
    if (parameterObject.isExpanded()) {
      formDTO = rfa1aFomMapper.toExpandedRFA1aFormDTO(form);
    } else {
      formDTO = rfa1aFomMapper.toRFA1aFormDTO(form);
    }

    return formDTO;
  }

  @UnitOfWork(CALSNS)
  @Override
  public RFA1aFormDTO update(RFA1aFormsParameterObject parameterObject, RFA1aFormDTO formDTO) {
    RFA1aForm form = findFormById(
        parameterObject.getFormId());
    rfa1aFomMapper.toRFA1aForm(form, formDTO);
    rfa1AFormsDao.update(fillFormUpdateAttributes(form));
    return rfa1aFomMapper.toRFA1aFormDTO(form);
  }

  private RFA1aForm fillFormUpdateAttributes(RFA1aForm form) {
    form.setUpdateDateTime(LocalDateTime.now());
    form.setUpdateUserId(Utils.Id.getStaffPersonId());
    return form;
  }

  @UnitOfWork(CALSNS)
  public RFAApplicationStatusDTO getApplicationStatus(Long formId) {
    RFA1aForm form = findFormById(formId);
    return new RFAApplicationStatusDTO(form.getStatus());
  }

  public void setApplicationStatus(Long formId, RFAApplicationStatusDTO statusDTO) {
    RFAApplicationStatus newStatus = statusDTO.getStatus();
    if (!changeStatusIfNotSubmitted(formId, newStatus)) {
      try {
        submitApplication(formId, newStatus);
      } catch (NotSupportedException | SystemException e) {
        throw new IllegalStateException(e);
      }
    }
  }

  @UnitOfWork(CALSNS)
  protected boolean changeStatusIfNotSubmitted(Long formId, RFAApplicationStatus newStatus) {
    RFA1aForm form = findFormById(formId);
    if (!StatusesTransitionConfiguration.isTransitionAllowed(form.getStatus(), newStatus)) {
      throw new ExpectedException(Constants.ExpectedExceptionMessages.TRANSITION_IS_NOT_ALLOWED,
          BAD_REQUEST);
    }
    if (form.getStatus() == newStatus) {
      return true;
    }
    if (newStatus != RFAApplicationStatus.SUBMITTED) {
      form.setStatus(newStatus);
      return true;
    }
    return false;
  }

  /**
   * There is using XA Transaction
   */
  private void submitApplication(Long formId, RFAApplicationStatus newStatus)
      throws NotSupportedException, SystemException {

    RFA1aFormDTO expandedFormDTO = getExpandedFormDTO(formId);
    performSubmissionValidation(expandedFormDTO);

    // Start transaction here
    UserTransaction userTransaction = new UserTransactionImp();
    userTransaction.setTransactionTimeout(3600);
    userTransaction.begin();

    PlacementHome storedPlacementHome = null;

    try {
      storedPlacementHome = storePlaceMentHome(expandedFormDTO);
      updateFormAfterPlacementHomeCreation(formId, storedPlacementHome.getIdentifier(),
          newStatus);
      userTransaction.commit();
    } catch (Exception e) {
      userTransaction.rollback();
      LOG.error("Can not create Placement Home", e);
      throw new SystemException(e.getMessage());
    }
  }

  @UnitOfWork(CALSNS)
  protected RFA1aFormDTO getExpandedFormDTO(Long formId) {
    RFA1aForm form = rfa1AFormsDao.find(formId);
    return rfa1aFomMapper.toExpandedRFA1aFormDTO(form);
  }

  private PlacementHome storePlaceMentHome(RFA1aFormDTO expandedFormDTO) {
    return facilityService.createPlacementHomeByRfaApplication(expandedFormDTO);
  }

  private void updateFormAfterPlacementHomeCreation(
      Long formId, String placementHomeId, RFAApplicationStatus newStatus) {
    RFA1aForm form = xaRfa1AFormsDao.find(formId);
    form.setStatus(newStatus);
    form.setPlacementHomeId(placementHomeId);
    xaRfa1AFormsDao.update(fillFormUpdateAttributes(form));
  }

  private void performSubmissionValidation(RFA1aFormDTO formDTO) {
    Validator validator = environment.getValidator();
    Optional.ofNullable(validator.validate(formDTO))
        .ifPresent(violations -> {
          if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
          }
        });

    Set<IssueDetails> detailsList = droolsService.validate(formDTO, createConfiguration());
    if (!detailsList.isEmpty()) {
      throw new BusinessValidationException(detailsList);
    }
  }

  private DroolsConfiguration<RFA1aFormDTO> createConfiguration() {
    return new DroolsFieldValidationConfiguration<RFA1aFormDTO>() {

      @Override
      public String getDroolsSessionName() {
        return FORM_SUBMISSION_VALIDATION_SESSION;
      }

      @Override
      public String getAgendaGroup() {
        return BusinessRulesAgendaGroups.FORM_SUBMISSION_VALIDATION;
      }
    };
  }

  private RFA1aForm findFormById(Long formId) {
    RFA1aForm form = rfa1AFormsDao.find(formId);
    if (form == null) {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    return form;
  }

}
