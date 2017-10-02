[
  "COUNTY": jsonMap.application_county.value,

  "NAME OF CHILDRow1": jsonMap.identified_children[0]?.first_name
      + " " + jsonMap.identified_children[0]?.middle_name
      + " " + jsonMap.identified_children[0]?.last_name,
  "DATE OF BIRTH OF CHILDRow1": jsonMap.identified_children[0]?.date_of_birth,
  "GENDERRow1": jsonMap.identified_children[0]?.gender?.value,
  "COUNTY OF JURISDICTIONRow1": jsonMap.identified_children[0]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow1": jsonMap.identified_children[0]?.date_of_placement,
  "RELATIONSHIP TO APPLICANTSRow1": jsonMap.identified_children[0]?.relationship_to_applicants[0]?.relationship_to_applicant?.value,
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow1": jsonMap.identified_children[0]?.school_grade?.value
      + ", " + jsonMap.identified_children[0]?.school_name
      + ". " + jsonMap.identified_children[0]?.school_address?.street_address
      + ", " + jsonMap.identified_children[0]?.school_address?.city
      + ", " + jsonMap.identified_children[0]?.school_address?.state?.value
      + ", " + jsonMap.identified_children[0]?.school_address?.zip,

  "NAME OF CHILDRow2": jsonMap.identified_children[1]?.first_name
      + " " + jsonMap.identified_children[1]?.middle_name
      + " " + jsonMap.identified_children[1]?.last_name,
  "DATE OF BIRTH OF CHILDRow2": jsonMap.identified_children[1]?.date_of_birth,
  "GENDERRow2": jsonMap.identified_children[1]?.gender?.value,
  "COUNTY OF JURISDICTIONRow2": jsonMap.identified_children[1]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow2": jsonMap.identified_children[1]?.date_of_placement,
//  "RELATIONSHIP TO APPLICANTSRow2": jsonMap.identified_children[1]?.relationship_to_applicants[0]?.relationship_to_applicant?.value,
  "RELATIONSHIP TO APPLICANTSRow2": jsonMap.identified_children[1]?,
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow2": jsonMap.identified_children[1]?.school_grade?.value
      + ", " + jsonMap.identified_children[1]?.school_name
      + ". " + jsonMap.identified_children[1]?.school_address?.street_address
      + ", " + jsonMap.identified_children[1]?.school_address?.city
      + ", " + jsonMap.identified_children[1]?.school_address?.state?.value
      + ", " + jsonMap.identified_children[1]?.school_address?.zip,

  "NAME OF CHILDRow3": jsonMap.identified_children[2]?.first_name
      + " " + jsonMap.identified_children[2]?.middle_name
      + " " + jsonMap.identified_children[2]?.last_name,
  "DATE OF BIRTH OF CHILDRow3": jsonMap.identified_children[2]?.date_of_birth,
  "GENDERRow3": jsonMap.identified_children[2]?.gender?.value,
  "COUNTY OF JURISDICTIONRow3": jsonMap.identified_children[2]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow3": jsonMap.identified_children[2]?.date_of_placement,
//  "RELATIONSHIP TO APPLICANTSRow3": jsonMap.identified_children[2]?.relationship_to_applicants[0]?.relationship_to_applicant?.value,
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow3": jsonMap.identified_children[2]?.school_grade?.value
      + ", " + jsonMap.identified_children[2]?.school_name
      + ". " + jsonMap.identified_children[2]?.school_address?.street_address
      + ", " + jsonMap.identified_children[2]?.school_address?.city
      + ", " + jsonMap.identified_children[2]?.school_address?.state?.value
      + ", " + jsonMap.identified_children[2]?.school_address?.zip,

  "NAME OF CHILDRow4": jsonMap.identified_children[3]?.first_name
      + " " + jsonMap.identified_children[3]?.middle_name
      + " " + jsonMap.identified_children[3]?.last_name,
  "DATE OF BIRTH OF CHILDRow4": jsonMap.identified_children[3]?.date_of_birth,
  "GENDERRow4": jsonMap.identified_children[3]?.gender?.value,
  "COUNTY OF JURISDICTIONRow4": jsonMap.identified_children[3]?.county_of_jurisdiction?.value,
  "DATE OF PLACEMENTRow4": jsonMap.identified_children[3]?.date_of_placement,
//  "RELATIONSHIP TO APPLICANTSRow4": jsonMap.identified_children[3]?.relationship_to_applicants[0]?.relationship_to_applicant?.value,
  "EDUCATION GRADE NAME  ADDRESS OF SCHOOLRow4": jsonMap.identified_children[3]?.school_grade?.value
      + ", " + jsonMap.identified_children[3]?.school_name
      + ". " + jsonMap.identified_children[3]?.school_address?.street_address
      + ", " + jsonMap.identified_children[3]?.school_address?.city
      + ", " + jsonMap.identified_children[3]?.school_address?.state?.value
      + ", " + jsonMap.identified_children[3]?.school_address?.zip,

  "Yes-Child has been identified": jsonMap.child_identified ? "Yes" : "Off",
  "No-Child has been identified": jsonMap.child_identified ? "Off" : "Yes",
  "Yes-Child currently in home": jsonMap.child_in_home ? "Yes" : "Off",
  "No-Child currently in home": jsonMap.child_in_home ? "Off" : "Yes"
]