/**
 * @author CWDS CALS API Team
 */
@TypeDefs(value = {
    @TypeDef(
        name = "ApplicationJsonType",
        typeClass = ApplicationJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = OTHER_TYPE)}
    ),
    @TypeDef(
        name = "ApplicantJsonType",
        typeClass = ApplicantJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = OTHER_TYPE)}
    ),
    @TypeDef(
        name = "ResidenceJsonType",
        typeClass = ResidenceJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = OTHER_TYPE)}

    ),
    @TypeDef(
        name = "ApplicantsRelationshipJsonType",
        typeClass = ApplicantsRelationshipJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = OTHER_TYPE)}
    ),
    @TypeDef(
        name = "MinorChildJsonType",
        typeClass = MinorChildJsonType.class,
        parameters = {@Parameter(name = SQL_TYPE,
            value = OTHER_TYPE)}
    )
})
package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.Constants.OTHER_TYPE;
import static gov.ca.cwds.cals.Constants.SQL_TYPE;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
