/**
 * @author CWDS CALS API Team
 */
@TypeDefs(value = {
    @TypeDef(
        name = "ApplicantHistoryJsonType",
        typeClass = ApplicantHistoryJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    ),
    @TypeDef(
        name = "OtherAdultJsonType",
        typeClass = OtherAdultJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    ),
    @TypeDef(
        name = "ApplicationJsonType",
        typeClass = ApplicationJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    ),
    @TypeDef(
        name = "ApplicantJsonType",
        typeClass = ApplicantJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    ),
    @TypeDef(
        name = "ResidenceJsonType",
        typeClass = ResidenceJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}

    ),
    @TypeDef(
        name = "ApplicantsRelationshipJsonType",
        typeClass = ApplicantsRelationshipJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    ),
    @TypeDef(
        name = "MinorChildJsonType",
        typeClass = MinorChildJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    ),
    @TypeDef(
        name = "AdoptionHistoryJsonType",
        typeClass = AdoptionHistoryJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = SQLTypes.OTHER_TYPE_NAME)}
    )
})
package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.Constants.SQL_TYPE;

import gov.ca.cwds.cals.persistence.hibernate.SQLTypes;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
