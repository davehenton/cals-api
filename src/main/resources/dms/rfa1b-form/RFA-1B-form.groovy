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

[
  'Have you ever been convicted of a crime in another state, federal court, military, or a jurisdiction outside of the U.S. ?  yes': yesValue(jsonMap.convicted_in_another_state),
  'Have you ever been convicted of a crime in California check yesHave you ever been convicted of a crime in another state, federal court, military, or a jurisdiction outside of the U.S. ?  no': noValue(jsonMap.convicted_in_another_state),

  'P1_CHK_Have_you_ever_been_convicted_of_a_crime_in_california?_yes': yesValue(jsonMap.convicted_in_california),
  'P1_CHK_Have_you_ever_been_convicted_of_a_crime_in_california_check_no': noValue(jsonMap.convicted_in_california),

  'P1_CHK_Have_you_ever_been_convicted_of_a_crime_in_california_check_yeshave_you_ever_been_arrested_for_a_crime_against_a_child_or_for_spousal/cohabitant_abuse?_yes': yesValue(jsonMap.arrested_for_crime),
  'P1_CHK_Have_you_ever_been_convicted_of_a_crime_in_california_check_yeshave_you_ever_been_convicted_of_a_crime_in_california_check_yeshave_you_ever_been_arrested_for_a_crime_against_a_child_or_for_spousal/cohabitant_abuse?_no': noValue(jsonMap.arrested_for_crime),

  'P1_CHK_Have_you_lived_in_a_state_other_than_california_within_the_last_five_years?__yes': yesValue(jsonMap.lived_in_other_state),
  'P1_CHK_Have_you_lived_in_a_state_other_than_california_within_the_last_five_years?__no': noValue(jsonMap.lived_in_other_state),
  'P1_If_yes_line_1': otherStatesOfLiving ? otherStatesOfLiving[0] : '',
  'P1_If_yes_line_2': otherStatesOfLiving && otherStatesOfLiving.size() > 1 ? otherStatesOfLiving[1..-1].join(', ') : '',

  'P1_County': jsonMap.application_county?.value,
  'P1_Date_of_birth': jsonMap.date_of_birth,
  'P1_Drivers_license_number/state': jsonMap.driver_license +'/'+ jsonMap.driver_license_state?.id,

  'P1_Social_security_number_see_privacy_statement': jsonMap.ssn,

  'P1_Your_full_name_print_clearly': getFullName(jsonMap)
]
