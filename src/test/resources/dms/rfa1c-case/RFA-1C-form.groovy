import org.apache.commons.lang3.StringUtils

def getChildName = {
    StringUtils.trimToNull(StringUtils.joinWith(" ",
        it?.first_name,
        it?.middle_name,
        it?.last_name
    ));
}

def getSchoolName = {
    StringUtils.trimToNull(StringUtils.joinWith(" ",
        it?.school_grade?.value,
        it?.school_name,
        it?.school_address?.street_address,
        it?.school_address?.city,
        it?.school_address?.state?.value,
        it?.school_address?.zip
    ));
}

def getRelationshipToApplicants = {
    it? it.relationship_to_applicants[0]?.relationship_to_applicant?.value : null;
}

[
  "COUNTY": jsonMap.application_county.value,

  "NAME OF CHILDRow1": getChildName(jsonMap.identified_children[0]),
  "DATE OF BIRTH OF CHILDRow1": jsonMap.identified_children[0]?.date_of_birth,
  "GENDERRow1": jsonMap.identified_children[0]?.gender?.value,
  "COUNTY OF JURISDICTIONRow1": jsonMap.identified_children[0]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow1": jsonMap.identified_children[0]?.date_of_placement,
  "RELATIONSHIP TO APPLICANTSRow1": getRelationshipToApplicants(jsonMap.identified_children[0]),
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow1": getSchoolName(jsonMap.identified_children[0]),

  "NAME OF CHILDRow2": getChildName(jsonMap.identified_children[1]),
  "DATE OF BIRTH OF CHILDRow2": jsonMap.identified_children[1]?.date_of_birth,
  "GENDERRow2": jsonMap.identified_children[1]?.gender?.value,
  "COUNTY OF JURISDICTIONRow2": jsonMap.identified_children[1]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow2": jsonMap.identified_children[1]?.date_of_placement,
  "RELATIONSHIP TO APPLICANTSRow2": getRelationshipToApplicants(jsonMap.identified_children[1]),
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow2": getSchoolName(jsonMap.identified_children[1]),

  "NAME OF CHILDRow3": getChildName(jsonMap.identified_children[2]),
  "DATE OF BIRTH OF CHILDRow3": jsonMap.identified_children[2]?.date_of_birth,
  "GENDERRow3": jsonMap.identified_children[2]?.gender?.value,
  "COUNTY OF JURISDICTIONRow3": jsonMap.identified_children[2]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow3": jsonMap.identified_children[2]?.date_of_placement,
  "RELATIONSHIP TO APPLICANTSRow3": getRelationshipToApplicants(jsonMap.identified_children[2]),
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow3": getSchoolName(jsonMap.identified_children[2]),

  "NAME OF CHILDRow4": getChildName(jsonMap.identified_children[3]),
  "DATE OF BIRTH OF CHILDRow4": jsonMap.identified_children[3]?.date_of_birth,
  "GENDERRow4": jsonMap.identified_children[3]?.gender?.value,
  "COUNTY OF JURISDICTIONRow4": jsonMap.identified_children[3]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow4": jsonMap.identified_children[3]?.date_of_placement,
  "RELATIONSHIP TO APPLICANTSRow4": getRelationshipToApplicants(jsonMap.identified_children[3]),
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow4": getSchoolName(jsonMap.identified_children[3]),

  "Yes-Child has been identified": jsonMap.child_identified ? "Yes" : "Off",
  "No-Child has been identified": jsonMap.child_identified ? "Off" : "Yes",
  "Yes-Child currently in home": jsonMap.child_in_home ? "Yes" : "Off",
  "No-Child currently in home": jsonMap.child_in_home ? "Off" : "Yes"
]