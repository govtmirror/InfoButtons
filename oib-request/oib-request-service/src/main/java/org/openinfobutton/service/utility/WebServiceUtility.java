/**
 * ...
 * <p>
 * -----------------------------------------------------------------------------------<br>
 * (c) 2010-2014 OpenInfobutton Project, Biomedical Informatics, University of Utah<br>
 * Contact: {@code <andrew.iskander@utah.edu>}<br>
 * Biomedical Informatics<br>
 * 421 Wakara Way, Ste 140
 * Salt Lake City, UT 84108-3514<br>
 * Day Phone: 1-801-581-4080<br>
 * -----------------------------------------------------------------------------------
 *
 * @author Andrew Iskander {@code <andrew.iskander@utah.edu>}
 * @version May 5, 2014
 */
package org.openinfobutton.service.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.v3.CategoryType;
import org.openinfobutton.schema.CodeConstants;
import org.openinfobutton.schema.CodeUtility;
import org.openinfobutton.schema.Encounter;
import org.openinfobutton.schema.Holder;
import org.openinfobutton.schema.IDLite;
import org.openinfobutton.schema.InformationRecipient;
import org.openinfobutton.schema.KnowledgeRequest;
import org.openinfobutton.schema.MainSearchCriteria;
import org.openinfobutton.schema.Patient;
import org.openinfobutton.schema.PatientContext;
import org.openinfobutton.schema.Performer;
import org.openinfobutton.schema.SeverityObservation;
import org.openinfobutton.schema.TaskContext;
import org.openinfobutton.schemas.kb.Code;

/**
 * ...
 * <p>
 * -----------------------------------------------------------------------------------<br>
 * (c) 2008-2014 Open Infobutton Project, Health Sciences IT, University of Utah<br>
 * Contact: {@code <andrew.iskander@utah.edu>}<br>
 * Biomedical Informatics, 26 South 2000 East<br>
 * Room 5775 HSEB, Salt Lake City, UT 84112<br>
 * Day Phone: 1-801-581-4080<br>
 * -----------------------------------------------------------------------------------
 *
 * @author Andrew Iskander {@code <andrew.iskander@utah.edu>}
 * @version Mar 11, 2014
 */
public class WebServiceUtility
{
	/**
	 * 
	 * @param requestParameters The parameters that were passed in the infobutton request
	 * @return KnowledgeRequest which holds the passed request parameters in a well defined format of CodedContextElements
	 */
	public static KnowledgeRequest getServiceRequest(Map<String, String[]> requestParameters) {
		PatientContext patientContext = new PatientContext();
		Patient patient = new Patient();
		Holder holder = new Holder();
		Performer performer = new Performer();
		InformationRecipient informationRecipient = new InformationRecipient();
		TaskContext taskContext = new TaskContext();
		MainSearchCriteria mainSearchCriteria = new MainSearchCriteria();
		SeverityObservation severityObservation = new SeverityObservation();
		Encounter encounter = new Encounter();
		Date effectiveTime = new Date();
		String executionMode = new String();
		Map<String,List<CategoryType>> categoryHashMap = new HashMap<String, List<CategoryType>>();
		//Set time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyymmddhhmmss");
		if (requestParameters.containsKey(CodeConstants.EFFECTIVE_TIME)) {
				
			try {
				effectiveTime = formatter.parse(requestParameters.get(CodeConstants.EFFECTIVE_TIME)[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Set Holder
		if (requestParameters.containsKey(CodeConstants.HOLDER_NAME)) {
			holder.setName(requestParameters.get(CodeConstants.HOLDER_NAME)[0]);
		}
		if (requestParameters.containsKey(CodeConstants.HOLDER_CERTIFICATETEXT)) {
			holder.setCertificateText(requestParameters.get(CodeConstants.HOLDER_CERTIFICATETEXT)[0]);
		}
		IDLite authorizedperson = new IDLite();
		if (requestParameters.containsKey(CodeConstants.HOLDER_AUTHORIZEDPERSON)) {
			authorizedperson.setRoot(requestParameters.get(CodeConstants.HOLDER_AUTHORIZEDPERSON)[0]);
		}
		holder.setAssignedAuthorizedPerson(authorizedperson);
		IDLite representedorganization = new IDLite();
		if (requestParameters.containsKey(CodeConstants.HOLDER_ORGANIZATION)) {
			representedorganization.setRoot(requestParameters.get(CodeConstants.HOLDER_ORGANIZATION)[0]);
		}
		holder.setRepresentedOrganization(representedorganization);
		
		//Set PatientContext
		List<CategoryType> category = new ArrayList<CategoryType>();
		Code gender = CodeUtility.getCode(CodeConstants.GENDER);
		if (requestParameters.containsKey(CodeConstants.GENDER_CODE)) {
		   	gender.setCode(requestParameters.get(CodeConstants.GENDER_CODE)[0]);
		   	CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.GENDER_CODE)[0]);
			c.setScheme(CodeConstants.GENDER_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.GENDER_DISPLAYNAME)) {
		   	gender.setDisplayName(requestParameters.get(CodeConstants.GENDER_DISPLAYNAME)[0]);
		   	CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.GENDER_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.GENDER_DISPLAYNAME);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.GENDER_CODESYSTEM)) {
		   	gender.setCodeSystem(requestParameters.get(CodeConstants.GENDER_CODESYSTEM)[0]);
		   	CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.GENDER_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.GENDER_CODESYSTEM);
			category.add(c);
		}
		
		patient.setGender(gender);
		categoryHashMap.put(CodeConstants.PATIENT_GENDER_KEY,category);
		category = new ArrayList<CategoryType>();
		
		if (requestParameters.containsKey(CodeConstants.PATIENT_AGE)) {
		   	patient.setAge(Float.valueOf(requestParameters.get(CodeConstants.PATIENT_AGE)[0]));
		}
		Code ageGroup = CodeUtility.getCode(CodeConstants.AGEGROUP);
		if (requestParameters.containsKey(CodeConstants.AGEGROUP_CODE)) {
		   	ageGroup.setCode(requestParameters.get(CodeConstants.AGEGROUP_CODE)[0]);
		}
		if (requestParameters.containsKey(CodeConstants.AGEGROUP_DISPLAYNAME)) {
		   	ageGroup.setDisplayName(requestParameters.get(CodeConstants.AGEGROUP_DISPLAYNAME)[0]);
		}
		patient.setAgeGroup(ageGroup);
		patientContext.setPatient(patient);

		//Set task context
		Code task = CodeUtility.getCode(CodeConstants.TASKCONTEXT);
		if (requestParameters.containsKey(CodeConstants.TASKCONTEXT_CODE)) {
			task.setCode(requestParameters.get(CodeConstants.TASKCONTEXT_CODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.TASKCONTEXT_CODE)[0]);
			c.setScheme(CodeConstants.TASKCONTEXT_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.TASKCONTEXT_DISPLAYNAME)) {
			task.setDisplayName(requestParameters.get(CodeConstants.TASKCONTEXT_DISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.TASKCONTEXT_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.TASKCONTEXT_DISPLAYNAME);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.TASKCONTEXT_CODESYSTEM)) {
			task.setCodeSystem(requestParameters.get(CodeConstants.TASKCONTEXT_CODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.TASKCONTEXT_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.TASKCONTEXT_CODESYSTEM);
			category.add(c);
		}
		taskContext.setCode(task);
		categoryHashMap.put(CodeConstants.TASK_KEY, category);
		category = new ArrayList<CategoryType>();
				
		//set mainsearch criteria
		Code mainsearchCode = CodeUtility.getCode(CodeConstants.LANGUAGE);
		if (requestParameters.containsKey(CodeConstants.MAINSEARCH_CODE)) {
			mainsearchCode.setCode(requestParameters.get(CodeConstants.MAINSEARCH_CODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.MAINSEARCH_CODE)[0]);
			c.setScheme(CodeConstants.MAINSEARCH_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.MAINSEARCH_CODESYSTEM)) {
			mainsearchCode.setCodeSystem(requestParameters.get(CodeConstants.MAINSEARCH_CODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.MAINSEARCH_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.MAINSEARCH_CODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.MAINSEARCH_DISPLAYNAME)) {
			mainsearchCode.setDisplayName(requestParameters.get(CodeConstants.MAINSEARCH_DISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.MAINSEARCH_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.MAINSEARCH_DISPLAYNAME);
			category.add(c);
		}
		mainSearchCriteria.setCode(mainsearchCode);		
		
		//Set severity observation
		Code severityobservation = CodeUtility.getCode();
		if (requestParameters.containsKey(CodeConstants.SEVERITYOBSERVATION_CODE)) {
			severityobservation.setCode(requestParameters.get(CodeConstants.SEVERITYOBSERVATION_CODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.SEVERITYOBSERVATION_CODE)[0]);
			c.setScheme(CodeConstants.SEVERITYOBSERVATION_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.SEVERITYOBSERVATION_CODESYSTEM)) {
			severityobservation.setCodeSystemName(requestParameters.get(CodeConstants.SEVERITYOBSERVATION_CODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.SEVERITYOBSERVATION_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.SEVERITYOBSERVATION_CODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.SEVERITYOBSERVATION_DISPLAYNAME)) {
			severityobservation.setDisplayName(requestParameters.get(CodeConstants.SEVERITYOBSERVATION_DISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.SEVERITYOBSERVATION_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.SEVERITYOBSERVATION_DISPLAYNAME);
			category.add(c);
		}
		severityObservation = new SeverityObservation(severityobservation);
		mainSearchCriteria.setSeverityObservation(severityObservation);
		categoryHashMap.put(CodeConstants.CONCEPT_OF_INTEREST_KEY, category);
		category = new ArrayList<CategoryType>();
		
		//Set performer
		Code performerLanguage = CodeUtility.getCode(CodeConstants.LANGUAGE);
		Code performerHealthCareProvider = CodeUtility.getCode();
		Code performerProviderOrPatient = CodeUtility.getCode();
		if (requestParameters.containsKey(CodeConstants.PERFORMER)) {
			String performerCode = requestParameters.get(CodeConstants.PERFORMER)[0];
			if (performerCode.equals("PROV")) {
				performerProviderOrPatient = CodeUtility.getCode(CodeConstants.PROVIDER);
			} else if (performerCode.equals("PAT")) {
				performerProviderOrPatient = CodeUtility.getCode(CodeConstants.PATIENT);
			}
			performer.setProviderOrPatient(performerProviderOrPatient);
			CategoryType c = new CategoryType();
			c.setTerm(performerCode);
			c.setScheme(CodeConstants.PERFORMER);
			category.add(c);
			categoryHashMap.put(CodeConstants.PERFORMER_KNOWLEDGE_USERTYPE_KEY, category);
			category = new ArrayList<CategoryType>();
		}
		if (requestParameters.containsKey(CodeConstants.PERFORMER_CODE)) {
			performerHealthCareProvider.setCode(requestParameters.get(CodeConstants.PERFORMER_CODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.PERFORMER_CODE)[0]);
			c.setScheme(CodeConstants.PERFORMER_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.PERFORMER_CODESYSTEM)) {
			performerHealthCareProvider.setCodeSystem(requestParameters.get(CodeConstants.PERFORMER_CODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.PERFORMER_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.PERFORMER_CODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.PERFORMER_DISPLAYNAME)) {
			performerHealthCareProvider.setDisplayName(requestParameters.get(CodeConstants.PERFORMER_DISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.PERFORMER_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.PERFORMER_DISPLAYNAME);
			category.add(c);
		}
		categoryHashMap.put(CodeConstants.PERFORMER_DISCIPLINE_KEY, category);
		category = new ArrayList<CategoryType>();
		
		performer.setHealthCareProvider(performerHealthCareProvider);
		if (requestParameters.containsKey(CodeConstants.PERFORMER_LANGUAGECODE)) {
			performerLanguage.setCode(requestParameters.get(CodeConstants.PERFORMER_LANGUAGECODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.PERFORMER_LANGUAGECODE)[0]);
			c.setScheme(CodeConstants.PERFORMER_LANGUAGECODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.PERFORMER_LANGUAGECODESYSTEM)) {
			performerLanguage.setCodeSystem(requestParameters.get(CodeConstants.PERFORMER_LANGUAGECODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.PERFORMER_LANGUAGECODESYSTEM)[0]);
			c.setScheme(CodeConstants.PERFORMER_LANGUAGECODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.PERFORMER_LANGUAGEDISPLAYNAME)) {
			performerLanguage.setDisplayName(requestParameters.get(CodeConstants.PERFORMER_LANGUAGEDISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.PERFORMER_LANGUAGEDISPLAYNAME)[0]);
			c.setScheme(CodeConstants.PERFORMER_LANGUAGEDISPLAYNAME);
			category.add(c);
		}
		performer.setLanguage(performerLanguage);
		categoryHashMap.put(CodeConstants.PERFORMER_LANGUAGE_KEY, category);
		category = new ArrayList<CategoryType>();
		
		//set information recipient
		Code language = CodeUtility.getCode(CodeConstants.LANGUAGE);
		Code healthCareProvider = CodeUtility.getCode();
		Code providerOrPatient = CodeUtility.getCode();
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT)) {
			String informationRecipientCode = requestParameters.get(CodeConstants.INFORMATIONRECIPIENT)[0];
			if (informationRecipientCode.equals("PROV")) {
				providerOrPatient = CodeUtility.getCode(CodeConstants.PROVIDER);
			} else if (informationRecipientCode.equals("PAT")) {
				providerOrPatient = CodeUtility.getCode(CodeConstants.PATIENT);
			}
			informationRecipient.setProviderOrPatient(providerOrPatient);
			CategoryType c = new CategoryType();
			c.setTerm(informationRecipientCode);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT);
			category.add(c);
			categoryHashMap.put(CodeConstants.INFORMATION_RECIPIENT_USERTYPE_KEY, category);
			category = new ArrayList<CategoryType>();
		}
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT_CODE)) {
			healthCareProvider.setCode(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_CODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_CODE)[0]);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT_CODESYSTEM)) {
			healthCareProvider.setCodeSystem(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_CODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT_CODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT_DISPLAYNAME)) {
			healthCareProvider.setDisplayName(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_DISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT_DISPLAYNAME);
			category.add(c);
		}
		informationRecipient.setHealthCareProvider(healthCareProvider);
		categoryHashMap.put(CodeConstants.INFORMATION_RECIPIENT_DISCIPLINE_KEY, category);
		category = new ArrayList<CategoryType>();
		
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODE)) {
			language.setCode(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODE)[0]);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODESYSTEM)) {
			language.setCodeSystem(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODESYSTEM)[0]);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT_LANGUAGECODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.INFORMATIONRECIPIENT_LANGUAGEDISPLAYNAME)) {
			language.setDisplayName(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_LANGUAGEDISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.INFORMATIONRECIPIENT_LANGUAGEDISPLAYNAME)[0]);
			c.setScheme(CodeConstants.INFORMATIONRECIPIENT_LANGUAGEDISPLAYNAME);
			category.add(c);
		}
		informationRecipient.setLanguage(language);	
		categoryHashMap.put(CodeConstants.INFORMATION_RECIPIENT_LANGUAGE_KEY, category);
		category = new ArrayList<CategoryType>();
		//set Encounter
		Code encounterCode =CodeUtility.getCode(CodeConstants.ENCOUNTER);
		IDLite serviceDeliveryLocation = new IDLite();
		if (requestParameters.containsKey(CodeConstants.ENCOUNTER_CODE)) {
			encounterCode.setCode(requestParameters.get(CodeConstants.ENCOUNTER_CODE)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.ENCOUNTER_CODE)[0]);
			c.setScheme(CodeConstants.ENCOUNTER_CODE);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.ENCOUNTER_CODESYSTEM)) {
			encounterCode.setCodeSystemName(requestParameters.get(CodeConstants.ENCOUNTER_CODESYSTEM)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.ENCOUNTER_CODESYSTEM)[0]);
			c.setScheme(CodeConstants.ENCOUNTER_CODESYSTEM);
			category.add(c);
		}
		if (requestParameters.containsKey(CodeConstants.ENCOUNTER_DISPLAYNAME)) {
			encounterCode.setDisplayName(requestParameters.get(CodeConstants.ENCOUNTER_DISPLAYNAME)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.ENCOUNTER_DISPLAYNAME)[0]);
			c.setScheme(CodeConstants.ENCOUNTER_DISPLAYNAME);
			category.add(c);
		}
		encounter.setCode(encounterCode);
		if (requestParameters.containsKey(CodeConstants.ENCOUNTER_SERVICEDELIVERYLOCATION)) {
			serviceDeliveryLocation.setRoot(requestParameters.get(CodeConstants.ENCOUNTER_SERVICEDELIVERYLOCATION)[0]);
			CategoryType c = new CategoryType();
			c.setTerm(requestParameters.get(CodeConstants.ENCOUNTER_SERVICEDELIVERYLOCATION)[0]);
			c.setScheme(CodeConstants.ENCOUNTER_SERVICEDELIVERYLOCATION);
			category.add(c);
		}
		encounter.setServiceDeliveryLocation(serviceDeliveryLocation);
		categoryHashMap.put(CodeConstants.ENCOUNTER_KEY, category);
		if (requestParameters.containsKey(CodeConstants.EXECUTION_MODE)) {
			executionMode = requestParameters.get(CodeConstants.EXECUTION_MODE)[0];
		}
		KnowledgeRequest knowledgeRequest = new KnowledgeRequest(patientContext, holder, performer, informationRecipient,
				taskContext,mainSearchCriteria, encounter, effectiveTime, executionMode);
		ageGroup = knowledgeRequest.getPatientContext().getPatient().getAgeGroup();
		if(!ageGroup.getCode().equals(""))
		{
			category = new ArrayList<CategoryType>();
			CategoryType c = new CategoryType();
			c.setScheme(CodeConstants.AGEGROUP_CODE);
			c.setTerm(ageGroup.getCode());
			category.add(c);
			c = new CategoryType();
			c.setScheme(CodeConstants.AGEGROUP_CODESYSTEM);
			c.setTerm(ageGroup.getCodeSystem());
			category.add(c);
			categoryHashMap.put(CodeConstants.PATIENT_AGEGROUP_KEY,category);
		}
		knowledgeRequest.setCategoryHashMap(categoryHashMap);
		return knowledgeRequest;
	}
}
