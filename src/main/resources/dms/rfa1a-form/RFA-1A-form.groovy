import org.apache.commons.lang3.StringUtils

String getSafeJoinWith(String separator, String... values) {
    String result = "";
    for(value in values) {
        if (!value) {
            continue;
        }
        if (!result) {
            result = value;
        } else {
            result = StringUtils.joinWith(separator, result, value);
        }
    }
    return result;
}

def getFullName = {
    getSafeJoinWith(" ", it?.name_prefix?.value, it?.first_name, it?.middle_name, it?.last_name, it?.name_suffix?.value);
}

def getFullNames = {
    String result = "";
    for(name in it) {
        if (!result) {
            result = getFullName(name);
        } else {
            result = StringUtils.joinWith(", ", [result, getFullName(name)]);
        }
    }
    result;
}

def getPhoneNumber = {applicant, phoneType ->
/*
    String[] phoneNumbers = applicant?.phones?.find {it?.value == phoneType}?.number;
    return StringUtils.join(", ", phoneNumbers);
*/

/*
    String result = "";
    for(phone in applicant?.phones) {
        if (phone?.phone_type?.value != phoneType) {
            continue;
        }
        if (!result) {
            result = phone.number;
        } else {
            result = StringUtils.joinWith(", ", result, phone.number);
        }
    }
    result;
*/
    String[] phoneNumbers = applicant?.phones?.find {it?.value == phoneType}?.number;
    if (phoneNumbers?.size() > 0) {
        return sprintf("(123) 456-7890");
    }

}

def getAddress = {
    String result = "";
    if (it) {
        result = sprintf("%s, %s, %s %s", [it.street_address, it.city, it.state?.id, it.zip]);
    }
    result;
}

def getNameAndAddressOfEmployer = {
    String result = "";
    String name = it?.employment?.employer_name;
    String address = getAddress(it?.employment?.physical_address);
    getSafeJoinWith(" / ", name, address)
/*
    if (name) {
        result = name;
    }
    if (address) {
        StringUtils.joinWith(" / ", result, address);
    }
    result;
*/
}

def getResidenceChoice = {
    String value = it?.residence_ownership?.value;
    switch(value) {
        case "Own":
            return "1";
        case "Rent":
            return "2";
        case "Lease":
            return "3";
        default:
            return "Off";
    }
}

def getTrueFalseChoice = {
    it == true ? "1" : it == false ? "2" : "Off"
}

def getJoinedValues = {
    if (!it) {
        return "";
    }
    String[] values = it*.value;
    return StringUtils.joinWith(", ", values);
}

def getIncome = {
    String income = it?.employment?.income;
    if (income) {
        return sprintf("\$ %s", income);
    }
}

def getApplicantsRelationshipChoice = {
    String value = it?.relationship_type?.value;
    switch(value) {
        case "Married":
            return "1";
        case "Domestic Partnership":
            return "2";
        case "Related (Family Member)":
            return "3";
        case "Cohabitants":
            return "4";
        case "Other":
            return "5";
        default:
            return "Off";
    }
}

[
    'OTHER (SPECIFY)_pg 1' : jsonMap.is_other_type ? jsonMap.other_type_description : "",
    'APPLICANT ONE:  PREVIOUS NAMES USED: *including maiden name_pg 1' : getFullNames(jsonMap.applicants?.getAt(0)?.other_names),
    'APPLICANT ONE:  HIGHEST LEVEL OF EDUCATION COMPLETED_ pg 1' : jsonMap.applicants[0]?.highest_education_level?.value,
    'APPLICANT ONE: DATE OF BIRTH_pg 1' : jsonMap.applicants[0]?.date_of_birth,
    'APPLICANT ONE:  GENDER_pg 1' : jsonMap.applicants[0]?.gender?.value,
    'APPLICANT ONE:  RACEETHNICITY_pg 1' : getSafeJoinWith("/", jsonMap.applicants[0]?.race?.value, jsonMap.applicants[0]?.ethnicity?.value),
    'APPLICANT ONE: DRIVERS LICENSE NUMBER_pg 1' : jsonMap.applicants[0]?.driver_license_number,
    'APPLICANT ONE: WORK PHONE NUMBER_pg 1' : getPhoneNumber(jsonMap.applicants[0], "Work"),
    'APPLICANT ONE:  EMAIL ADDRESS OPTIONAL_pg 1' : jsonMap.applicants[0]?.email,
    'APPLICANT ONE:  CELL PHONE NUMBERR_pg 1' : getPhoneNumber(jsonMap.applicants[0], "Cell"),
    'APPLICANT ONE:  HOME PHONE NUMBER_pg 1' : getPhoneNumber(jsonMap.applicants[0], "Home"),
    'APPLICANT ONE:  NAMEADDRESS OF EMPLOYER_pg 1' : getNameAndAddressOfEmployer(jsonMap.applicants[0]),
    'APPLICANT ONE_First: _pg 1' : jsonMap.applicants[0]?.first_name,
    'APPLICANT ONE_Middle: _pg 1' : jsonMap.applicants[0]?.middle_name,
    'APPLICANT ONE_LAST: _pg 1' : jsonMap.applicants[0]?.last_name,
    'APPLICANT TWO:_First: _pg 1' : jsonMap.applicants[1]?.first_name,
    'APPLICANT TWO:_Middle: _pg 1' : jsonMap.applicants[1]?.middle_name,
    'APPLICANT TWO:_LAST: _pg 1' : jsonMap.applicants[1]?.last_name,
    'APPLICANT TWO:  PREVIOUS NAMES USED including maiden name_pg 1' : getFullNames(jsonMap.applicants?.getAt(1)?.other_names),
    'APPLICANT TWO:  HIGHEST LEVEL OF EDUCATION COMPLETED_pg 1' : jsonMap.applicants[1]?.highest_education_level?.value,
    'APPLICANT TWO:  DATE OF BIRTH_pg 1' : jsonMap.applicants[1]?.date_of_birth,
    'APPLICANT TWO:  GENDER_pg 1' : jsonMap.applicants[1]?.gender?.value,
    'APPLICANT TWO:  RACEETHNICITY_pg 1' : getSafeJoinWith("/", jsonMap.applicants[1]?.race?.value, jsonMap.applicants[1]?.ethnicity?.value),
    'APPLICANT TWO:  DRIVERS LICENSE NUMBER_pg 1' : jsonMap.applicants[1]?.driver_license_number,
    'APPLICANT TWO:  NAMEADDRESS OF EMPLOYER_pg 1' : getNameAndAddressOfEmployer(jsonMap.applicants[1]),
    'APPLICANT TWO:  CELL PHONE NUMBER_pg 1' : getPhoneNumber(jsonMap.applicants[1], "Cell"),
    'APPLICANT TWO:  HOME PHONE NUMBER_pg 1' : getPhoneNumber(jsonMap.applicants[1], "Home"),
    'APPLICANT TWO:  WORK PHONE NUMBER_pg 1' : getPhoneNumber(jsonMap.applicants[1], "Work"),
    'APPLICANT TWO:  ANNUAL INCOME_pg 1' : getIncome(jsonMap.applicants[1]),
    'APPLICANT TWO:  OCCUPATION_pg 1' : jsonMap.applicants[1]?.employment?.occupation,
    'APPLICANT TWO:  EMAIL ADDRESS (OPTIONAL)_pg 1' : jsonMap.applicants[1]?.email,
    'APPLICANT ONE:  OCCUPATION_pg 1' : jsonMap.applicants[0]?.employment?.occupation,
    'APPLICANT ONE:  ANNUAL INCOME_pg 1' : getIncome(jsonMap.applicants[0]),
    'APPLICANT(S): PHYSICAL ADDRESS_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.street_address,
    'APPLICANT(S):  STATE_1_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.state?.id,
    'APPLICANT(S):  ZIP_1_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.zip,
    'APPLICANT(S): MAILING ADDRESS IF DIFFERENT_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.street_address,
    'APPLICANT(S): CITY_1_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.city,
    'APPLICANT(S): ZIP_2_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.zip,
    'APPLICANT(S): STATE_2_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.state?.id,
    'APPLICANT(S): CITY_2_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.city,
    'REFERENCES:  FULL NAME_ROW 1_pg 4' : '',
    'REFERENCES:  TELEPHONE NUMBER(S)_ROW 1_pg 4' : '',
    'DATE_1_pg 4' : '',
    'If more that one applicant, what is your relationship? OTHER (Explain)_pg 2' : jsonMap.other_relationship,
    'Place of Current Marriage/Domestic PartnerShip_pg 2' : getSafeJoinWith(", ", jsonMap.applicants_relationship?.place_of_relationship_city, jsonMap.applicants_relationship?.place_of_relationship_state?.id),
    'Date of Current Marriage/Domestic Partnership_pg 2' : jsonMap.applicants_relationship?.date_of_relationship,
    'Minor Children Residing in the Home:  Relationship to Applicants_ROW 1_pg 2' : getJoinedValues(jsonMap.minor_children?.getAt(0)?.relationship_to_applicants),
    'Minor Children Residing in the Home:  Relationship to Applicants_ROW 2_pg 2' : getJoinedValues(jsonMap.minor_children?.getAt(1)?.relationship_to_applicants),
    'Minor Children Residing in the Home:  Relationship to Applicants_ROW 3_pg 2' : getJoinedValues(jsonMap.minor_children?.getAt(2)?.relationship_to_applicants),
    'Minor Children Residing in the Home:  Relationship to Applicants_ROW 4_pg 2' : getJoinedValues(jsonMap.minor_children?.getAt(3)?.relationship_to_applicants),
    'Minor Children Residing in the Home: Date of Birth_ROW 1_pg 2' : jsonMap.minor_children[0]?.date_of_birth,
    'Minor Children Residing in the Home: Date of Birth_ROW 2_pg 2' : jsonMap.minor_children[1]?.date_of_birth,
    'Minor Children Residing in the Home: Date of Birth_ROW 3_pg 2' : jsonMap.minor_children[2]?.date_of_birth,
    'Minor Children Residing in the Home: Date of Birth_ROW 4_pg 2' : jsonMap.minor_children[3]?.date_of_birth,
    'Minor Children Residing in the Home: Gender_ROW 1_pg 2' : jsonMap.minor_children[0]?.gender?.value,
    'Minor Children Residing in the Home: Gender_ROW 2_pg 2' : jsonMap.minor_children[1]?.gender?.value,
    'Minor Children Residing in the Home: Gender_ROW 3_pg 2' : jsonMap.minor_children[2]?.gender?.value,
    'Minor Children Residing in the Home: Gender_ROW 4_pg 2' : jsonMap.minor_children[3]?.gender?.value,
    'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 1_pg 2' : getFullName(jsonMap.other_adults[0]),
    'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 2_pg 2' : getFullName(jsonMap.other_adults[1]),
    'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 3_pg 2' : getFullName(jsonMap.other_adults[2]),
    'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 4_pg 2' : getFullName(jsonMap.other_adults[3]),
    'Other Adult Residing In The Home: Date Of Birth_ROW 1_pg 2' : jsonMap.other_adults[0]?.date_of_birth,
    'Other Adult Residing In The Home: Date Of Birth_ROW 2_pg 2' : jsonMap.other_adults[1]?.date_of_birth,
    'Other Adult Residing In The Home: Date Of Birth_ROW 3_pg 2' : jsonMap.other_adults[2]?.date_of_birth,
    'Other Adult Residing In The Home: Date Of Birth_ROW 4_pg 2' : jsonMap.other_adults[3]?.date_of_birth,
    'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 1_pg 2' : getJoinedValues(jsonMap.other_adults?.getAt(0)?.relationship_to_applicants),
    'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 2_pg 2' : getJoinedValues(jsonMap.other_adults?.getAt(1)?.relationship_to_applicants),
    'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 3_pg 2' : getJoinedValues(jsonMap.other_adults?.getAt(2)?.relationship_to_applicants),
    'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 4_pg 2' : getJoinedValues(jsonMap.other_adults?.getAt(3)?.relationship_to_applicants),
/*
    'Applicant(s) History: Appliciant One, Name of Former Spouse_ROW 2_pg 2' : getFullName(jsonMap.applicants_history?.former_spouses?.find {it?.applicant_id == jsonMap.applicants[0]?.id}[1]),
    'Applicant(s) History: Appliciant One, Name of Former Spouse_ROW 1_pg 2' : getFullName(jsonMap.applicants_history?.former_spouses?.find {it?.applicant_id == jsonMap.applicants[0]?.id}[0]),
    'Applicant(s) History: Appliciant Two, Name of Former Spouse_ROW 1_pg 2' : getFullName(jsonMap.applicants_history?.former_spouses?.find {it?.applicant_id == jsonMap.applicants[1]?.id}[0]),
    'Applicant(s) History: Appliciant Two, Name of Former Spouse_ROW 2_pg 2' : getFullName(jsonMap.applicants_history?.former_spouses?.find {it?.applicant_id == jsonMap.applicants[1]?.id}[1]),
*/
    'Applicant(s) History: Applicant One: Marriage Date and Place City and State_ROW 1_pg 2' : '',
    'Applicant(s) History: Applicant One: Marriage Date and Place City and State_ROW 2_pg 2' : '',
    'Applicant(s) History: Applicant Two: Marriage Date and Place City and State_ROW 1_pg 2' : '',
    'Applicant(s) History: Applicant Two: Marriage Date and Place City and State_ROW 2_pg 2' : '',
    'Applicant(s) History: Applicant One: Divorce Date and Place _ROW 1_pg 2' : '',
    'Applicant(s) History: Applicant Two: Divorce Date and Place _ROW 1_pg 2' : '',
    'Applicant(s) History: Applicant One: Divorce Date and Place _ROW 2_pg 2' : '',
    'Applicant(s) History: Applicant Two: Divorce Date and Place _ROW 2_pg 2' : '',
    'Applicant(s) History: Applicant One: Death Date and Place _ROW 1_pg 2' : '',
    'Applicant(s) History: Applicant One: Death Date and Place _ROW 2_pg 2' : '',
    'Applicant(s) History: Applicant Two: Death Date and Place _ROW 1_pg 2' : '',
    'Applicant(s) History: Applicant Two: Death Date and Place _ROW 2_pg 2' : '',
    'Adult Children of Applicant(s):  Full Name_ROW 1_pg 2' : '',
    'Adult Children of Applicant(s):  Full Name_ROW 2_pg 2' : '',
    'Adult Children of Applicant(s):  Full Name_ROW 3_pg 2' : '',
    'Adult Children of Applicant(s):  Full Name_ROW 4_pg 2' : '',
    'Adult Children of Applicant(s):  Address & Phone Number_ROW 1_pg 2' : '',
    'Adult Children of Applicant(s):  Address & Phone Number_ROW 2_pg 2' : '',
    'Adult Children of Applicant(s):  Address & Phone Number_ROW 3_pg 2' : '',
    'Adult Children of Applicant(s):  Address & Phone Number_ROW 4_pg 2' : '',
    'Adult Children of Applicant(s):  Relationship_ROW 1_pg 2' : '',
    'Adult Children of Applicant(s):  Relationship_ROW 2_pg 2' : '',
    'Adult Children of Applicant(s):  Relationship_ROW 3_pg 2' : '',
    'Adult Children of Applicant(s):  Relationship_ROW 4_pg 2' : '',
    'Adult Children of Applicant(s):  Lives in Home_ROW 1_pg 2' : '',
    'Adult Children of Applicant(s):  Lives in Home_ROW 2_pg 2' : '',
    'Adult Children of Applicant(s):  Lives in Home_ROW 3_pg 2' : '',
    'Adult Children of Applicant(s):  Lives in Home_ROW 4_pg 2' : '',
    'If yes name of Agencys_pg 3' : '',
    'Type of license/certification/approval_pg 3' : '',
    'If yes name of Agencys_2_pg 3' : '',
    'If yes type of license_pg 3' : '',
    'If yes name the facilitys_ pg 3' : '',
    'REFERENCES:  FULL NAME_ROW 2_pg 4' : '',
    'REFERENCES:  FULL NAME_ROW 3_pg 4' : '',
    'REFERENCES:  TELEPHONE NUMBER(S)_ROW 2_pg 4' : '',
    'REFERENCES:  TELEPHONE NUMBER(S)_ROW 3_pg 4' : '',
    'MAILING ADDRESSCITYSTATEZIP Row 1_pg 4' : '',
    'MAILING ADDRESSCITYSTATEZIP Row 2_pg 4' : '',
    'MAILING ADDRESSCITYSTATEZIP Row 3_pg 4' : '',
    'EMAIL ADDRESS Row 1_pg 4' : '',
    'EMAIL ADDRESS Row 2_pg 4' : '',
    'EMAIL ADDRESS Row 3_pg 4' : '',
    'DATE_2_pg 4' : '',
    'CITY AND COUNTY WHERE SIGNED Row1_pg 4' : '',
    'CITY AND COUNTY WHERE SIGNED Row2_pg 4' : '',
    'AGE(S): 0-3 yrs Check Box_pg 3' : 'Off',
    'AGE(S): 4-8 yrs Check Box_pg 3' : 'Off',
    'AGE(S): 9-12 yrs Check Box_pg 3' : 'Off',
    'AGE(S): 13-15 yrs Check Box_pg 3' : 'Off',
    'AGE(S): 16-18 yrs Check Box_pg 3' : 'Off',
    'AGE(S): 18-21 yrs Check Box_pg 3' : 'Off',
    'AGE(S): No preference Check Box_pg 3' : 'Off',
    'Sibling (Group Of): 0 Check Box_pg 3' : 'Off',
    'Sibling (Group Of): 2 Check Box_pg 3' : 'Off',
    'Sibling (Group Of): 3 Check Box_pg 3' : 'Off',
    'Sibling (Group Of): 4 Check Box_pg 3' : 'Off',
    'Sibling (Group Of): 5 or more Check Box_pg 3' : 'Off',
    'County_pg 1' : jsonMap.application_county?.value,
    'Check Box_pg 1' : getTrueFalseChoice(jsonMap.is_initial_application),
    'Do you own, rent or lease the residence? Check Box_pg 1' : getResidenceChoice(jsonMap.residence),
    'Weapons in the home?  Check Box_pg 1' : getTrueFalseChoice(jsonMap.residence?.weapon_in_home),
    'If more that one applicant, what is your relationship? check box_pg 2' : getApplicantsRelationshipChoice(jsonMap.applicants_relationship),
    'Do You Financially Support This Child? _Check Box_Row 1_pg 2' : getTrueFalseChoice(jsonMap.minor_children[0]?.child_financially_supported),
    'Adopted? Check Box_ROW 1_pg 2' : getTrueFalseChoice(jsonMap.minor_children[0]?.child_adopted),
    'Do You Financially Support This Child? _Check Box_Row 2_pg 2' : getTrueFalseChoice(jsonMap.minor_children[1]?.child_financially_supported),
    'Adopted? _Check Box_ROW 2_pg 2' : getTrueFalseChoice(jsonMap.minor_children[1]?.child_adopted),
    'Do You Financially Support This Child? _Check Box_Row 3_pg 2' : getTrueFalseChoice(jsonMap.minor_children[2]?.child_financially_supported),
    'Adopted? _Check Box_ROW 3_pg 2' : getTrueFalseChoice(jsonMap.minor_children[2]?.child_adopted),
    'Do You Financially Support This Child? _Check Box_Row 4_pg 2' : getTrueFalseChoice(jsonMap.minor_children[3]?.child_financially_supported),
    'Adopted? _Check Box_ROW 4_pg 2' : getTrueFalseChoice(jsonMap.minor_children[3]?.child_adopted),
    'Has a Child been identified?- Check Box_pg 3Has a Child been identified?- Check Box_pg 3' : 'Off',
    'Is the child currently in your home?  - Check Box_pg 3' : 'Off',
    'Have you had a license, certification, or approval suspended, revoked, or rescinded?  Check Box_pg 3' : 'Off',
    'Have you been subject to an exclusion order?   Check Box_pg 3' : 'Off',
    'Have you had a previous license, certification, relative or non relative extended family member, or resource family approval application denial?  Check Box_pg 3' : 'Off',
    'If Yes ,please discribe the location of the body of water and its size_pg 1' : jsonMap.residence?.body_of_water_exist ? jsonMap.residence?.body_of_water_description : "",
    'If Yes, who_pg 1' : getFullNames(jsonMap.residence?.other_people_using_residence_as_mailing),
    'Languages Spoken in the Home_pg 2' : getJoinedValues(jsonMap.residence?.home_languages),
    'Please provide direction, including major cross-street information, to your residence_pg 2' : jsonMap.residence?.directions_to_home,
    'Body of Water' : getTrueFalseChoice(jsonMap.residence?.body_of_water_exist),
    'Does any person not listed in this document use the residence as their mailing address? Check Box_pg 1' : getTrueFalseChoice(jsonMap.residence?.others_using_residence_as_mailing),
    'If yes, nam of agency(s)_3_pg 3' : '',
    'If yes, nam of agency(s)_4_pg 3' : '',
]
