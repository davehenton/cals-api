import org.apache.commons.lang3.StringUtils

def  getSafeJoinWith = {String separator, String... values ->
    String result = "";
    for(value in values) {
        if (!value) {
            continue;
        }
        if (result) {
            result = StringUtils.joinWith(separator, result, value)
        } else {
            result = value
        }
    }
    result
}

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
    def stateZip = it[2, 3].join(' ')
    [it[0], it[1], stateZip].join(', ')
}

def dateIsoToUs = {
    it ? [it[5..6], it[8..9], it[0..3]].join('/') : ''
}

def sliceData = { String str, int... sizes ->
    def result = [];
    int pointer = 0;
    for (size in sizes) {
        String part = str.drop(pointer).take(size)
        if (part.length() < size) {
            result.add(part)
            pointer += part.length()
            break
        }

        int indexOf = part.lastIndexOf(" ")
        if (indexOf == -1) {
            result.add(part)
            pointer += part.length()
            continue
        }

        part = part.substring(0, indexOf)

        result.add(part)
        pointer += indexOf
    }
    String part = str.drop(pointer).take(str.length())
    if (part != "") {
        result.add(part)
    }
    result
}

def offenseData = sliceData(getSafeJoinWith(". ", jsonMap.convicted_in_california_disclosures[0]?.offense), [100, 130, 130] as int[])
def offenseDetailsData = sliceData(getSafeJoinWith(". ", jsonMap.convicted_in_california_disclosures[0]?.offense_details), [60, *([130]*12)] as int[])
[
        'COUNTY' : jsonMap.application_county?.value,
        'NAME OF RESOURCE FAMILY' : jsonMap.resource_family_name,
        'YOUR FULL NAME PRINT CLEARLY' : getFullName(jsonMap),
        'RESIDENCE ADDRESS STREET CITY ZIP' : formatAddress(getAddressStreetCityStateZip(jsonMap.residence_address)),
        'DATE' : dateIsoToUs(jsonMap.application_date),
        'In which state and city did you commit the offense' : [jsonMap.convicted_in_california_disclosures[0]?.offense_city, jsonMap.convicted_in_california_disclosures[0]?.offense_state?.value].join(', '),
        'When did this happen' : dateIsoToUs(jsonMap.convicted_in_california_disclosures[0]?.when_offense_happen),
        'DATE_2' : dateIsoToUs(jsonMap.application_date),
        'Explain what happened line 1' : offenseDetailsData[0],
        'Explain what happened line 2' : offenseDetailsData[1],
        'Explain what happened line 3' : offenseDetailsData[2],
        'Explain what happened line 4' : offenseDetailsData[3],
        'Explain what happened line 5' : offenseDetailsData[4],
        'Explain what happened line 6' : offenseDetailsData[5],
        'Explain what happened line 7' : offenseDetailsData[6],
        'Explain what happened line 8' : offenseDetailsData[7],
        'Explain what happened line 9' : offenseDetailsData[8],
        'Explain what happened line 10' : offenseDetailsData[9],
        'Explain what happened line 11' : offenseDetailsData[10],
        'Explain what happened line 12' : offenseDetailsData[11],
        'Explain what happened line 13' : offenseDetailsData[12],
        'If Yes Line 1' : otherStatesOfLiving ? otherStatesOfLiving[0] : '',
        'If Yes Line 2' : otherStatesOfLiving && otherStatesOfLiving.size() > 1 ? otherStatesOfLiving[1..-1].join(', ') : '',
        'SOCIAL SECURITY NUMBER SEE PRIVACY STATEMENT' : jsonMap.ssn,
        'Date Of Birth' : dateIsoToUs(jsonMap.date_of_birth),
        'Drivers License Number/State' : jsonMap.driver_license +'/'+ jsonMap.driver_license_state?.id,
        'Have you lived in a state other than California within the last five years?  yes' : yesValue(jsonMap.lived_in_other_state),
        'Have you lived in a state other than California within the last five years?  no' : noValue(jsonMap.lived_in_other_state),
        'Have you ever been convicted of a crime in California? Yes' : yesValue(jsonMap.convicted_in_california),
        'Have you ever been convicted of a crime in California check no' : noValue(jsonMap.convicted_in_california),
        'Have you ever been convicted of a crime in California check yesHave you ever been arrested for a crime against a child or for spousal/cohabitant abuse? yes' : yesValue(jsonMap.arrested_for_crime),
        'Have you ever been convicted of a crime in California check yesHave you ever been convicted of a crime in California check yesHave you ever been arrested for a crime against a child or for spousal/cohabitant abuse? no' : noValue(jsonMap.arrested_for_crime),
        'What was the offense line 1' : offenseData[0],
        'What was the offense line 2' : offenseData[1],
        'What was the offense line 3' : offenseData[2],

        'Have you ever been convicted of a crime in another state, federal court, military, or a jurisdiction outside of the U.S. ?  yes' : yesValue(jsonMap.convicted_in_another_state),
        'Have you ever been convicted of a crime in California check yesHave you ever been convicted of a crime in another state, federal court, military, or a jurisdiction outside of the U.S. ?  no' : noValue(jsonMap.convicted_in_another_state)
]

