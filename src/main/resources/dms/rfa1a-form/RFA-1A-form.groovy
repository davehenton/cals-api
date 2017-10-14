import org.apache.commons.lang3.StringUtils

def dateIsoToUs = {
    it ? [it[5..6], it[8..9], it[0..3]].join('/') : ''
}

def formatPhoneNumber = {
    it ? sprintf("(%s) %s-%s", it[0..2], it[3..5], it[6..9]) : ''
}

def  getSafeJoinWith = {String separator, String... values ->
    String result = "";
    for(value in values) {
        if (!value) {
            continue;
        }
        if (result) {
            result = StringUtils.joinWith(separator, result, value);
        } else {
            result = value;
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
        if (result) {
            result = StringUtils.joinWith(", ", [result, getFullName(name)]);
        } else {
            result = getFullName(name);
        }
    }
    result;
}

def getAddress = {
    String result = "";
    if (it) {
        result = sprintf("%s, %s, %s %s", [it.street_address, it.city, it.state?.id, it.zip]);
    }
    result;
}

def getNameAndAddressOfEmployer = {
    jsonMap.applicants[it]?.employment?.with {
        String address = getAddress(physical_address);
        getSafeJoinWith(" / ", employer_name, address)
    }
}

def getApplicantRaceAndEthnicity = {
    jsonMap.applicants[it].with{getSafeJoinWith("/", race?.value, ethnicity?.value)}
}

def getPhoneNumbers = {it, type ->
    getSafeJoinWith(", ",(it.findAll {it?.phone_type?.value == type}?.number as String[]).collect{formatPhoneNumber(it)} as String[])
}

def getRelationshipsToApplicant = {
    getSafeJoinWith(", ", it?.relationship_to_applicants.collect{it.relationship_to_applicant.value} as String[])
}

def getApplicantFormerSpouses = {index ->
    jsonMap.applicants_history?.former_spouses.findAll{it?.applicant_id == jsonMap.applicants[index]?.id}
}

def getApplicantFormerSpouseMarriageDateAndPlace = {
    it?.with{getSafeJoinWith(" ", dateIsoToUs(it?.date_of_marriage), getSafeJoinWith(", ", it?.place_of_marriage_city, it?.place_of_marriage_state?.id))}
}

def getMarriageEndDateAndPlace = {
    it?.with{getSafeJoinWith(" ", dateIsoToUs(date_of_marriage_end), getSafeJoinWith(", ", place_of_marriage_end_city, place_of_marriage_end_state?.id))}
}

def getApplicantFormerSpouseDivorceDateAndPlace = {
    it?.with{marriage_termination_reason?.id == 2 ? "" : getMarriageEndDateAndPlace(it)}
}

def getApplicantFormerSpouseDeathDateAndPlace = {
    it?.with{marriage_termination_reason?.id == 2 ? getMarriageEndDateAndPlace(it) : ""}
}

def getAddressAndPhoneNumber = {
    it?.with{getSafeJoinWith.call(", ph: ", getAddress.call(address), phone?.with{formatPhoneNumber(number)})}
}

[
        'OTHER (SPECIFY)_pg 1' : jsonMap.is_other_type ? jsonMap.other_type_description : "",
        'APPLICANT ONE:  PREVIOUS NAMES USED: *including maiden name_pg 1' : getFullNames.call(jsonMap.applicants?.getAt(0)?.other_names),
        'APPLICANT ONE:  HIGHEST LEVEL OF EDUCATION COMPLETED_ pg 1' : jsonMap.applicants[0]?.highest_education_level?.value,
        'APPLICANT ONE: DATE OF BIRTH_pg 1' : dateIsoToUs(jsonMap.applicants[0]?.date_of_birth),
        'APPLICANT ONE:  GENDER_pg 1' : jsonMap.applicants[0]?.gender?.value,
        'APPLICANT ONE:  RACEETHNICITY_pg 1' : getApplicantRaceAndEthnicity(0),
        'APPLICANT ONE: DRIVERS LICENSE NUMBER_pg 1' : jsonMap.applicants[0]?.driver_license_number,
        'APPLICANT ONE: WORK PHONE NUMBER_pg 1' : getPhoneNumbers(jsonMap.applicants[0]?.phones, "Work"),
        'APPLICANT ONE:  EMAIL ADDRESS OPTIONAL_pg 1' : jsonMap.applicants[0]?.email,
        'APPLICANT ONE:  CELL PHONE NUMBERR_pg 1' : getPhoneNumbers(jsonMap.applicants[0]?.phones, "Cell"),
        'APPLICANT ONE:  HOME PHONE NUMBER_pg 1' : getPhoneNumbers(jsonMap.applicants[0]?.phones, "Home"),
        'APPLICANT ONE:  NAMEADDRESS OF EMPLOYER_pg 1' : getNameAndAddressOfEmployer(0),
        'APPLICANT ONE_First: _pg 1' : jsonMap.applicants[0]?.first_name,
        'APPLICANT ONE_Middle: _pg 1' : jsonMap.applicants[0]?.middle_name,
        'APPLICANT ONE_LAST: _pg 1' : jsonMap.applicants[0]?.last_name,
        'APPLICANT TWO:_First: _pg 1' : jsonMap.applicants[1]?.first_name,
        'APPLICANT TWO:_Middle: _pg 1' : jsonMap.applicants[1]?.middle_name,
        'APPLICANT TWO:_LAST: _pg 1' : jsonMap.applicants[1]?.last_name,
        'APPLICANT TWO:  PREVIOUS NAMES USED including maiden name_pg 1' : getFullNames.call(jsonMap.applicants?.getAt(1)?.other_names),
        'APPLICANT TWO:  HIGHEST LEVEL OF EDUCATION COMPLETED_pg 1' : jsonMap.applicants[1]?.highest_education_level?.value,
        'APPLICANT TWO:  DATE OF BIRTH_pg 1' : dateIsoToUs(jsonMap.applicants[1]?.date_of_birth),
        'APPLICANT TWO:  GENDER_pg 1' : jsonMap.applicants[1]?.gender?.value,
        'APPLICANT TWO:  RACEETHNICITY_pg 1' : getApplicantRaceAndEthnicity(1),
        'APPLICANT TWO:  DRIVERS LICENSE NUMBER_pg 1' : jsonMap.applicants[1]?.driver_license_number,
        'APPLICANT TWO:  NAMEADDRESS OF EMPLOYER_pg 1' : getNameAndAddressOfEmployer(1),
        'APPLICANT TWO:  CELL PHONE NUMBER_pg 1' : getPhoneNumbers(jsonMap.applicants[1]?.phones, "Cell"),
        'APPLICANT TWO:  HOME PHONE NUMBER_pg 1' : getPhoneNumbers(jsonMap.applicants[1]?.phones, "Home"),
        'APPLICANT TWO:  WORK PHONE NUMBER_pg 1' : getPhoneNumbers(jsonMap.applicants[1]?.phones, "Work"),
        'APPLICANT TWO:  ANNUAL INCOME_pg 1' : jsonMap.applicants[1]?.employment?.income as String,
        'APPLICANT TWO:  OCCUPATION_pg 1' : jsonMap.applicants[1]?.employment?.occupation,
        'APPLICANT TWO:  EMAIL ADDRESS (OPTIONAL)_pg 1' : jsonMap.applicants[1]?.email,
        'APPLICANT ONE:  OCCUPATION_pg 1' : jsonMap.applicants[0]?.employment?.occupation,
        'APPLICANT ONE:  ANNUAL INCOME_pg 1' : jsonMap.applicants[0]?.employment?.income as String,
        'APPLICANT(S): PHYSICAL ADDRESS_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.street_address,
        'APPLICANT(S):  STATE_1_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.state?.id,
        'APPLICANT(S):  ZIP_1_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.zip,
        'APPLICANT(S): MAILING ADDRESS IF DIFFERENT_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.street_address,
        'APPLICANT(S): CITY_1_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Residential"})?.city,
        'APPLICANT(S): ZIP_2_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.zip,
        'APPLICANT(S): STATE_2_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.state?.id,
        'APPLICANT(S): CITY_2_pg 1' : (jsonMap.residence?.addresses?.find {it?.type?.value == "Mailing"})?.city,
        'REFERENCES:  FULL NAME_ROW 1_pg 4' : getFullName.call(jsonMap.references?.items[0]),
        'REFERENCES:  TELEPHONE NUMBER(S)_ROW 1_pg 4' : formatPhoneNumber(jsonMap.references?.items[0]?.phone_number),
        'DATE_1_pg 4' : dateIsoToUs(jsonMap.applicants_declaration?.applicant_signatures[0].signature_date),
        'If more that one applicant, what is your relationship? OTHER (Explain)_pg 2' : jsonMap.other_relationship,
        'Place of Current Marriage/Domestic PartnerShip_pg 2' : getSafeJoinWith(", ", jsonMap.applicants_relationship?.place_of_relationship_city, jsonMap.applicants_relationship?.place_of_relationship_state?.id),
        'Date of Current Marriage/Domestic Partnership_pg 2' : dateIsoToUs(jsonMap.applicants_relationship?.date_of_relationship),
        'Minor Children Residing in the Home:  Relationship to Applicants_ROW 1_pg 2' : getRelationshipsToApplicant(jsonMap.minor_children[0]),
        'Minor Children Residing in the Home:  Relationship to Applicants_ROW 2_pg 2' : getRelationshipsToApplicant(jsonMap.minor_children[1]),
        'Minor Children Residing in the Home:  Relationship to Applicants_ROW 3_pg 2' : getRelationshipsToApplicant(jsonMap.minor_children[2]),
        'Minor Children Residing in the Home:  Relationship to Applicants_ROW 4_pg 2' : getRelationshipsToApplicant(jsonMap.minor_children[3]),
        'Minor Children Residing in the Home: Date of Birth_ROW 1_pg 2' : dateIsoToUs(jsonMap.minor_children[0]?.date_of_birth),
        'Minor Children Residing in the Home: Date of Birth_ROW 2_pg 2' : dateIsoToUs(jsonMap.minor_children[1]?.date_of_birth),
        'Minor Children Residing in the Home: Date of Birth_ROW 3_pg 2' : dateIsoToUs(jsonMap.minor_children[2]?.date_of_birth),
        'Minor Children Residing in the Home: Date of Birth_ROW 4_pg 2' : dateIsoToUs(jsonMap.minor_children[3]?.date_of_birth),
        'Minor Children Residing in the Home: Gender_ROW 1_pg 2' : jsonMap.minor_children[0]?.gender?.value,
        'Minor Children Residing in the Home: Gender_ROW 2_pg 2' : jsonMap.minor_children[1]?.gender?.value,
        'Minor Children Residing in the Home: Gender_ROW 3_pg 2' : jsonMap.minor_children[2]?.gender?.value,
        'Minor Children Residing in the Home: Gender_ROW 4_pg 2' : jsonMap.minor_children[3]?.gender?.value,
        'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 1_pg 2' : getFullName(jsonMap.other_adults[0]),
        'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 2_pg 2' : getFullName(jsonMap.other_adults[1]),
        'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 3_pg 2' : getFullName(jsonMap.other_adults[2]),
        'Other Adult Residing In The Home:  Full Name (First, Middle, Initial & Last)_ROW 4_pg 2' : getFullName(jsonMap.other_adults[3]),
        'Other Adult Residing In The Home: Date Of Birth_ROW 1_pg 2' : dateIsoToUs(jsonMap.other_adults[0]?.date_of_birth),
        'Other Adult Residing In The Home: Date Of Birth_ROW 2_pg 2' : dateIsoToUs(jsonMap.other_adults[1]?.date_of_birth),
        'Other Adult Residing In The Home: Date Of Birth_ROW 3_pg 2' : dateIsoToUs(jsonMap.other_adults[2]?.date_of_birth),
        'Other Adult Residing In The Home: Date Of Birth_ROW 4_pg 2' : dateIsoToUs(jsonMap.other_adults[3]?.date_of_birth),
        'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 1_pg 2' : getRelationshipsToApplicant(jsonMap.other_adults[0]),
        'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 2_pg 2' : getRelationshipsToApplicant(jsonMap.other_adults[1]),
        'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 3_pg 2' : getRelationshipsToApplicant(jsonMap.other_adults[2]),
        'Other Adult Residing In The Home: Relationship To Applicant(s)_ROW 4_pg 2' : getRelationshipsToApplicant(jsonMap.other_adults[3]),
        'Applicant(s) History: Appliciant One, Name of Former Spouse_ROW 2_pg 2' : getFullName(getApplicantFormerSpouses(0)?.getAt(1)),
        'Applicant(s) History: Appliciant One, Name of Former Spouse_ROW 1_pg 2' : getFullName(getApplicantFormerSpouses(0)?.getAt(0)),
        'Applicant(s) History: Appliciant Two, Name of Former Spouse_ROW 1_pg 2' : getFullName(getApplicantFormerSpouses(1)?.getAt(0)),
        'Applicant(s) History: Appliciant Two, Name of Former Spouse_ROW 2_pg 2' : getFullName(getApplicantFormerSpouses(1)?.getAt(1)),
        'Applicant(s) History: Applicant One: Marriage Date and Place City and State_ROW 1_pg 2' : getApplicantFormerSpouseMarriageDateAndPlace(getApplicantFormerSpouses(0)?.getAt(0)),
        'Applicant(s) History: Applicant One: Marriage Date and Place City and State_ROW 2_pg 2' : getApplicantFormerSpouseMarriageDateAndPlace(getApplicantFormerSpouses(0)?.getAt(1)),
        'Applicant(s) History: Applicant Two: Marriage Date and Place City and State_ROW 1_pg 2' : getApplicantFormerSpouseMarriageDateAndPlace(getApplicantFormerSpouses(1)?.getAt(0)),
        'Applicant(s) History: Applicant Two: Marriage Date and Place City and State_ROW 2_pg 2' : getApplicantFormerSpouseMarriageDateAndPlace(getApplicantFormerSpouses(1)?.getAt(1)),
        'Applicant(s) History: Applicant One: Divorce Date and Place _ROW 1_pg 2' : getApplicantFormerSpouseDivorceDateAndPlace(getApplicantFormerSpouses(0)?.getAt(0)),
        'Applicant(s) History: Applicant Two: Divorce Date and Place _ROW 1_pg 2' : getApplicantFormerSpouseDivorceDateAndPlace(getApplicantFormerSpouses(0)?.getAt(1)),
        'Applicant(s) History: Applicant One: Divorce Date and Place _ROW 2_pg 2' : getApplicantFormerSpouseDivorceDateAndPlace(getApplicantFormerSpouses(1)?.getAt(0)),
        'Applicant(s) History: Applicant Two: Divorce Date and Place _ROW 2_pg 2' : getApplicantFormerSpouseDivorceDateAndPlace(getApplicantFormerSpouses(1)?.getAt(1)),
        'Applicant(s) History: Applicant One: Death Date and Place _ROW 1_pg 2' : getApplicantFormerSpouseDeathDateAndPlace(getApplicantFormerSpouses(0)?.getAt(0)),
        'Applicant(s) History: Applicant One: Death Date and Place _ROW 2_pg 2' : getApplicantFormerSpouseDeathDateAndPlace(getApplicantFormerSpouses(0)?.getAt(1)),
        'Applicant(s) History: Applicant Two: Death Date and Place _ROW 1_pg 2' : getApplicantFormerSpouseDeathDateAndPlace(getApplicantFormerSpouses(1)?.getAt(0)),
        'Applicant(s) History: Applicant Two: Death Date and Place _ROW 2_pg 2' : getApplicantFormerSpouseDeathDateAndPlace(getApplicantFormerSpouses(1)?.getAt(1)),
        'Adult Children of Applicant(s):  Full Name_ROW 1_pg 2' : getFullName.call(jsonMap.applicants_history?.adult_children[0]),
        'Adult Children of Applicant(s):  Full Name_ROW 2_pg 2' : getFullName.call(jsonMap.applicants_history?.adult_children[1]),
        'Adult Children of Applicant(s):  Full Name_ROW 3_pg 2' : getFullName.call(jsonMap.applicants_history?.adult_children[2]),
        'Adult Children of Applicant(s):  Full Name_ROW 4_pg 2' : getFullName.call(jsonMap.applicants_history?.adult_children[3]),
        'Adult Children of Applicant(s):  Address & Phone Number_ROW 1_pg 2' : getAddressAndPhoneNumber(jsonMap.applicants_history.adult_children[0]),
        'Adult Children of Applicant(s):  Address & Phone Number_ROW 2_pg 2' : getAddressAndPhoneNumber(jsonMap.applicants_history.adult_children[1]),
        'Adult Children of Applicant(s):  Address & Phone Number_ROW 3_pg 2' : getAddressAndPhoneNumber(jsonMap.applicants_history.adult_children[2]),
        'Adult Children of Applicant(s):  Address & Phone Number_ROW 4_pg 2' : getAddressAndPhoneNumber(jsonMap.applicants_history.adult_children[3]),
        'Adult Children of Applicant(s):  Relationship_ROW 1_pg 2' : getRelationshipsToApplicant(jsonMap.applicants_history.adult_children[0]),
        'Adult Children of Applicant(s):  Relationship_ROW 2_pg 2' : getRelationshipsToApplicant(jsonMap.applicants_history.adult_children[1]),
        'Adult Children of Applicant(s):  Relationship_ROW 3_pg 2' : getRelationshipsToApplicant(jsonMap.applicants_history.adult_children[2]),
        'Adult Children of Applicant(s):  Relationship_ROW 4_pg 2' : getRelationshipsToApplicant(jsonMap.applicants_history.adult_children[3]),
        'Adult Children of Applicant(s):  Lives in Home_ROW 1_pg 2' : [true: "Yes", false: "No"].getAt(jsonMap.applicants_history.adult_children[0]?.lives_in_home as String)?:"",
        'Adult Children of Applicant(s):  Lives in Home_ROW 2_pg 2' : [true: "Yes", false: "No"].getAt(jsonMap.applicants_history.adult_children[1]?.lives_in_home as String)?:"",
        'Adult Children of Applicant(s):  Lives in Home_ROW 3_pg 2' : [true: "Yes", false: "No"].getAt(jsonMap.applicants_history.adult_children[2]?.lives_in_home as String)?:"",
        'Adult Children of Applicant(s):  Lives in Home_ROW 4_pg 2' : [true: "Yes", false: "No"].getAt(jsonMap.applicants_history.adult_children[3]?.lives_in_home as String)?:"",
        'If yes name of Agencys_pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.foster_care_licenses_q1?.agencies.collect{it.name} as String[]),
        'Type of license/certification/approval_pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.foster_care_licenses_q1?.agencies.collect{it.type?.value} as String[]),
        'If yes name of Agencys_2_pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.applications_for_adoption_q2?.facilities as String[]),
        'If yes type of license_pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.facility_operation_licenses_q3?.agencies.collect{it.type?.value} as String[]),
        'If yes name the facilitys_ pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.employment_in_facilities_q4?.facilities as String[]),
        'REFERENCES:  FULL NAME_ROW 2_pg 4' : getFullName.call(jsonMap.references?.items[1]),
        'REFERENCES:  FULL NAME_ROW 3_pg 4' : getFullName.call(jsonMap.references?.items[2]),
        'REFERENCES:  TELEPHONE NUMBER(S)_ROW 2_pg 4' : formatPhoneNumber(jsonMap.references?.items[1]?.phone_number),
        'REFERENCES:  TELEPHONE NUMBER(S)_ROW 3_pg 4' : formatPhoneNumber(jsonMap.references?.items[2]?.phone_number),
        'MAILING ADDRESSCITYSTATEZIP Row 1_pg 4' : getAddress(jsonMap.references?.items[0]?.mailing_address),
        'MAILING ADDRESSCITYSTATEZIP Row 2_pg 4' : getAddress(jsonMap.references?.items[1]?.mailing_address),
        'MAILING ADDRESSCITYSTATEZIP Row 3_pg 4' : getAddress(jsonMap.references?.items[2]?.mailing_address),
        'EMAIL ADDRESS Row 1_pg 4' : jsonMap.references?.items[0]?.email,
        'EMAIL ADDRESS Row 2_pg 4' : jsonMap.references?.items[1]?.email,
        'EMAIL ADDRESS Row 3_pg 4' : jsonMap.references?.items[2]?.email,
        'DATE_2_pg 4' : dateIsoToUs(jsonMap.applicants_declaration?.applicant_signatures[1].signature_date),
        'CITY AND COUNTY WHERE SIGNED Row1_pg 4' : jsonMap.applicants_declaration?.applicant_signatures[0].with{return getSafeJoinWith.call(", ", signature_city, signature_county?.value)},
        'CITY AND COUNTY WHERE SIGNED Row2_pg 4' : jsonMap.applicants_declaration?.applicant_signatures[1].with{return getSafeJoinWith.call(", ", signature_city, signature_county?.value)},
        'AGE(S): 0-3 yrs Check Box_pg 3' : [true: "Yes"].getAt(jsonMap.child_desired?.preferred_ages.any{it.id == 1} as String)?:"Off",
        'AGE(S): 4-8 yrs Check Box_pg 3' : [true: "Yes"].getAt(jsonMap.child_desired?.preferred_ages.any{it.id == 2} as String)?:"Off",
        'AGE(S): 9-12 yrs Check Box_pg 3' : [true: "Yes"].getAt(jsonMap.child_desired?.preferred_ages.any{it.id == 3} as String)?:"Off",
        'AGE(S): 13-15 yrs Check Box_pg 3' : [true: "Yes"].getAt(jsonMap.child_desired?.preferred_ages.any{it.id == 4} as String)?:"Off",
        'AGE(S): 16-18 yrs Check Box_pg 3' : [true: "Yes"].getAt(jsonMap.child_desired?.preferred_ages.any{it.id == 5} as String)?:"Off",
        'AGE(S): 18-21 yrs Check Box_pg 3' : [true: "Yes"].getAt(jsonMap.child_desired?.preferred_ages.any{it.id == 6} as String)?:"Off",
        'AGE(S): No preference Check Box_pg 3' : [true: "Yes"].getAt((jsonMap.child_desired?.preferred_ages?.size == 0) as String)?:"Off",
        'Sibling (Group Of): 0 Check Box_pg 3' : [1: "Yes"].getAt(jsonMap.child_desired?.preferred_sibling_group_up_to?.id)?:"Off",
        'Sibling (Group Of): 2 Check Box_pg 3' : [2: "Yes"].getAt(jsonMap.child_desired?.preferred_sibling_group_up_to?.id)?:"Off",
        'Sibling (Group Of): 3 Check Box_pg 3' : [3: "Yes"].getAt(jsonMap.child_desired?.preferred_sibling_group_up_to?.id)?:"Off",
        'Sibling (Group Of): 4 Check Box_pg 3' : [4: "Yes"].getAt(jsonMap.child_desired?.preferred_sibling_group_up_to?.id)?:"Off",
        'Sibling (Group Of): 5 or more Check Box_pg 3' : [5: "Yes"].getAt(jsonMap.child_desired?.preferred_sibling_group_up_to?.id)?:"Off",
        'County_pg 1' : jsonMap.application_county?.value,
        'Check Box_pg 1' : [true: "1", false: "2"].getAt(jsonMap.is_initial_application as String)?:"Off",
        'Do you own, rent or lease the residence? Check Box_pg 1' : ["Own": "1", "Rent": "2", "Lease": "3"].getAt(jsonMap.residence?.residence_ownership?.value)?:"Off",
        'Weapons in the home?  Check Box_pg 1' : [true: "1", false: "2"].getAt(jsonMap.residence?.weapon_in_home as String)?:"Off",
        'If more that one applicant, what is your relationship? check box_pg 2' : ["Married": "1", "Domestic Partnership": "2", "Related (Family Member)": "3", "Cohabitants": "1", "Other": "1"].getAt(jsonMap.applicants_relationship?.relationship_type?.value)?:"Off",
        'Do You Financially Support This Child? _Check Box_Row 1_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[0]?.child_financially_supported as String)?:"Off",
        'Adopted? Check Box_ROW 1_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[0]?.child_adopted as String)?:"Off",
        'Do You Financially Support This Child? _Check Box_Row 2_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[1]?.child_financially_supported as String)?:"Off",
        'Adopted? _Check Box_ROW 2_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[1]?.child_adopted as String)?:"Off",
        'Do You Financially Support This Child? _Check Box_Row 3_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[2]?.child_financially_supported as String)?:"Off",
        'Adopted? _Check Box_ROW 3_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[2]?.child_adopted as String)?:"Off",
        'Do You Financially Support This Child? _Check Box_Row 4_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[3]?.child_financially_supported as String)?:"Off",
        'Adopted? _Check Box_ROW 4_pg 2' : [true: "1", false: "2"].getAt(jsonMap.minor_children[3]?.child_adopted as String)?:"Off",
        'Has a Child been identified?- Check Box_pg 3Has a Child been identified?- Check Box_pg 3' :  [true: "1", false: "2"].getAt(jsonMap?.child_desired?.child_identified as String)?:"Off",
        'Is the child currently in your home?  - Check Box_pg 3' :[true: "1", false: "2"].getAt(jsonMap.child_desired?.child_in_home as String)?:"Off",
        'Have you had a license, certification, or approval suspended, revoked, or rescinded?  Check Box_pg 3' : [true: "1", false: "2"].getAt(jsonMap.adoption_history?.suspension_revocation_history_q6?.had_suspensions_revocations as String)?:"Off",
        'Have you been subject to an exclusion order?   Check Box_pg 3' : [true: "1", false: "2"].getAt(jsonMap.adoption_history?.was_subject_for_exclusion_order_q7 as String)?:"Off",
        'Have you had a previous license, certification, relative or non relative extended family member, or resource family approval application denial?  Check Box_pg 3' : [true: "1", false: "2"].getAt(jsonMap.adoption_history?.denial_history_q5?.had_denials as String)?:"Off",
        'If Yes ,please discribe the location of the body of water and its size_pg 1' : jsonMap.residence?.body_of_water_exist ? jsonMap.residence?.body_of_water_description : "",
        'If Yes, who_pg 1' : getFullNames.call(jsonMap.residence?.other_people_using_residence_as_mailing),
        'Languages Spoken in the Home_pg 2' : getSafeJoinWith.call(", ", jsonMap.residence?.home_languages.collect{return it.value} as String[]),
        'Please provide direction, including major cross-street information, to your residence_pg 2' : jsonMap.residence?.directions_to_home,
        'Body of Water' : [true: "1", false: "2"].getAt(jsonMap.residence?.body_of_water_exist as String)?:"Off",
        'Does any person not listed in this document use the residence as their mailing address? Check Box_pg 1' : [true: "1", false: "2"].getAt(jsonMap.residence?.others_using_residence_as_mailing as String)?:"Off",
        'If yes, nam of agency(s)_3_pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.denial_history_q5?.agencies.collect{it.name} as String[]),
        'If yes, nam of agency(s)_4_pg 3' : getSafeJoinWith.call(", ", jsonMap.adoption_history?.suspension_revocation_history_q6?.agencies.collect{it.name} as String[]),
]

