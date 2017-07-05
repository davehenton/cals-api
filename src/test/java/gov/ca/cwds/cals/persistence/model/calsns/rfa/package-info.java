
/**
 * @author CWDS CALS API Team
 */
@TypeDefs(
    value = {
        @TypeDef(
            name = "ChildDesiredJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.ChildDesired"
                )
            }
        ),
        @TypeDef(
            name = "RFA1bFormJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bFormDTO"
                )
            }
        ),
        @TypeDef(
            name = "ApplicantHistoryJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsHistory"
                )
            }
        ),
        @TypeDef(
            name = "OtherAdultJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.OtherAdult"
                )
            }
        ),
        @TypeDef(
            name = "ApplicationJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.Application"
                )
            }
        ),
        @TypeDef(
            name = "ApplicantJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant"
                )
            }
        ),
        @TypeDef(
            name = "ResidenceJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence"
                )
            }
        ),
        @TypeDef(
            name = "ApplicantsRelationshipJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsRelationship"
                )
            }
        ),
        @TypeDef(
            name = "MinorChildJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild"
                )
            }
        ),
        @TypeDef(
            name = "AdoptionHistoryJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.AdoptionHistory"
                )
            }
        ),
        @TypeDef(
            name = "ReferencesJsonType",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.ReferencesDTO"
                )
            }
        ),
        @TypeDef(
            name = "ApplicantsDeclaration",
            typeClass = JsonType.class,
            parameters = {
                @Parameter(name = SQL_TYPE, value = SQLTypes.CLOB_TYPE_NAME),
                @Parameter(
                    name = RETURNED_CLASS_NAME_PARAM,
                    value = "gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsDeclaration"
                )
            }
        )
    }
)
package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.Constants.RETURNED_CLASS_NAME_PARAM;
import static gov.ca.cwds.cals.Constants.SQL_TYPE;

import gov.ca.cwds.cals.persistence.hibernate.JsonType;
import gov.ca.cwds.cals.persistence.hibernate.SQLTypes;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
