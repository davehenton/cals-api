import org.apache.commons.lang3.StringUtils

def yesValue = { it ? "Yes" : "Off" }
def noValue = { it ? "Off" : "Yes" }

def otherStatesOfLiving = jsonMap.other_states_of_living?.value

def getFullName = {
    StringUtils.trimToNull(StringUtils.joinWith(" ",
            it?.applicant_name_prefix?.value,
            it?.applicant_first_name,
            it?.applicant_middle_name,
            it?.applicant_last_name,
            it?.applicant_name_suffix?.value
    ))
}

def getAddressStreetCityStateZip = {
    [
            it?.street_address,
            it?.city,
            it?.state?.id,
            it?.zip
    ]
}

def formatAddress = {
    stateZip = it[2, 3].join(' ')
    [it[0], it[1], stateZip].join(', ')
}

def dateIsoToUs = {
    [it[5..6], it[8..9], it[0..3]].join('/')
}

[
  'Have you ever been convicted of a crime in another state, federal court, military, or a jurisdiction outside of the U': yesValue(jsonMap.convicted_in_another_state),
  'Have you ever been convicted of a crime in California check yesHave you ever been convicted of a crime in another state, federal court, military, or a jurisdiction outside of the U': noValue(jsonMap.convicted_in_another_state),

  'Have you ever been convicted of a crime in California? Yes': yesValue(jsonMap.convicted_in_california),
  'Have you ever been convicted of a crime in California check no': noValue(jsonMap.convicted_in_california),

  'Have you ever been convicted of a crime in California check yesHave you ever been arrested for a crime against a child or for spousal/cohabitant abuse? yes': yesValue(jsonMap.arrested_for_crime),
  'Have you ever been convicted of a crime in California check yesHave you ever been convicted of a crime in California check yesHave you ever been arrested for a crime against a child or for spousal/cohabitant abuse? no': noValue(jsonMap.arrested_for_crime),

  'Have you lived in a state other than California within the last five years?  yes': yesValue(jsonMap.lived_in_other_state),
  'Have you lived in a state other than California within the last five years?  no': noValue(jsonMap.lived_in_other_state),
  'If Yes Line 1': otherStatesOfLiving ? otherStatesOfLiving[0] : '',
  'If Yes Line 2': otherStatesOfLiving && otherStatesOfLiving.size() > 1 ? otherStatesOfLiving[1..-1].join(', ') : '',

  'COUNTY': jsonMap.application_county?.value,
  'DATE': dateIsoToUs(jsonMap.application_date),
  'NAME OF RESOURCE FAMILY': jsonMap.resource_family_name,
  'RESIDENCE ADDRESS STREET CITY ZIP': formatAddress(getAddressStreetCityStateZip(jsonMap.residence_address)),

  'Date Of Birth': dateIsoToUs(jsonMap.date_of_birth),
  'Drivers License Number/State': jsonMap.driver_license +'/'+ jsonMap.driver_license_state?.id,
  'SOCIAL SECURITY NUMBER SEE PRIVACY STATEMENT': jsonMap.ssn,
  'YOUR FULL NAME PRINT CLEARLY': getFullName(jsonMap),

  'DATE_2': dateIsoToUs(jsonMap.application_date),
  'In which state and city did you commit the offense': [jsonMap.disclosures[0]?.offense_city, jsonMap.disclosures[0]?.offense_state?.value].join(', '),
  'When did this happen': dateIsoToUs(jsonMap.disclosures[0]?.offense_date),

  'What was the offense line 1': jsonMap.disclosures[0]?.offense,
  'Explain what happened line 1': jsonMap.disclosures[0]?.offense_details
]
