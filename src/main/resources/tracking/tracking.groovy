import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate

//code
Map<String, TrackingTemplate> templatesMap = templates.collectEntries { [it.templateType, it] }

def trackingDocuments = [
        facility_documents       : templatesMap["family_documents"],
        person_tracking_documents: rfa1a.applicants.collect {
            ["person_id"  : it.id,
             "person_name": it.applicantFullName,
             "person_type": "Applicant",
             "docs"       : templatesMap["applicants"].templateJson
            ]
        } + rfa1a.otherAdults.collect {
            ["person_id"  : it.id,
             "person_name": "$it.firstName $it.lastName",
             "person_type": "Applicant",
             "docs"       : templatesMap["other_adults"].templateJson
            ]
        }
]

new ObjectMapper().convertValue(trackingDocuments, JsonNode.class)
