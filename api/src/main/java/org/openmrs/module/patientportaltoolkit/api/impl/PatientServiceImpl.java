/**
 * The contents of this file are subject to the Regenstrief Public License
 * Version 1.0 (the "License"); you may not use this file except in compliance with the License.
 * Please contact Regenstrief Institute if you would like to obtain a copy of the license.
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) Regenstrief Institute.  All Rights Reserved.
 */

package org.openmrs.module.patientportaltoolkit.api.impl;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportaltoolkit.api.PatientService;
import org.openmrs.module.patientportaltoolkit.api.util.ToolkitResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurya.
 */
public class PatientServiceImpl extends BaseOpenmrsService implements PatientService {

    @Override
    public  Object getPatient(String patientId){
        Patient patient= Context.getPatientService().getPatientByUuid(patientId);
        if (patient != null)
            return ToolkitResourceUtil.generatePerson(patient);

        return null;
    }

    @Override
    public List<Object> getAllPatients() {
        List<Object> patientPortalPatients = new ArrayList<Object>();
        List<Patient> omrsPatients = Context.getPatientService().getAllPatients();
        for(Patient p: omrsPatients){
            patientPortalPatients.add(getPatient(p.getUuid()));
        }
        return patientPortalPatients;
    }

    @Override
    public Object updatePatient(String patientJson) {
        return ToolkitResourceUtil.updatePatient(patientJson);
    }
}

