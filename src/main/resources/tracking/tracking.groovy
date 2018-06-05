import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate

Map<String, TrackingTemplate> templatesMap = templates.collectEntries { [it.templateType, it] }
Map<String, TrackingTemplate> defaultTemplatesMap = defaultTemplates.collectEntries { [it.templateType, it] }

def getTemplateJson = { name ->
    def template = templatesMap[name]
    return template ? template.templateJson :  defaultTemplatesMap[name].templateJson
}

def trackingDocuments = [
        facility_documents: getTemplateJson("family_documents"),
        people_documents  : rfa1a.applicants.collect {
            ["person_id"       : it.id,
             "person_name"     : "$it.applicant.firstName $it.applicant.lastName" as String,
             "person_type"     : "Applicant",
             "person_documents": getTemplateJson("applicant_documents")
            ]
        } + rfa1a.otherAdults.collect {
            ["person_id"       : it.id,
             "person_name"     : "$it.otherAdult.firstName $it.otherAdult.lastName" as String,
             "person_type"     : "Other_Adult",
             "person_documents": getTemplateJson("other_adult_documents")
            ]
        }
]

new ObjectMapper().convertValue(trackingDocuments, JsonNode.class)
