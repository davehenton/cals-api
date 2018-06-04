import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate

Map<String, TrackingTemplate> templatesMap = templates.collectEntries { [it.templateType, it] }

def trackingDocuments = [
        facility_documents: templatesMap["family_documents"].templateJson,
        people_documents  : rfa1a.applicants.collect {
            ["person_id"       : it.id,
             "person_name"     : "$it.applicant.firstName $it.applicant.lastName" as String,
             "person_type"     : "Applicant",
             "person_documents": templatesMap["applicant_documents"].templateJson
            ]
        } + rfa1a.otherAdults.collect {
            ["person_id"       : it.id,
             "person_name"     : "$it.otherAdult.firstName $it.otherAdult.lastName" as String,
             "person_type"     : "Applicant",
             "person_documents": templatesMap["other_adult_documents"].templateJson
            ]
        }
]

new ObjectMapper().convertValue(trackingDocuments, JsonNode.class)
