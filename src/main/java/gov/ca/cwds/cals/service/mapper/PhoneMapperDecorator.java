package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.service.dto.PhoneDTO;

/**
 * @author CWDS CALS API Team
 */
public abstract class PhoneMapperDecorator implements PhoneMapper {

    private PhoneMapper delegate;

    public PhoneMapperDecorator(PhoneMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public PhoneDTO toPrimaryPhoneDTO(PlacementHome placementHome) {
        PhoneDTO phoneDTO = null;
        if (placementHome.getPrmTelNo() > 0) {
            phoneDTO = delegate.toPrimaryPhoneDTO(placementHome);
        }
        return phoneDTO;
    }

    @Override
    public PhoneDTO toAlternatePhoneDTO(PlacementHome placementHome) {
        PhoneDTO phoneDTO = null;
        if (placementHome.getBckTelNo() > 0) {
            phoneDTO = delegate.toAlternatePhoneDTO(placementHome);
        }
        return phoneDTO;
    }
}
