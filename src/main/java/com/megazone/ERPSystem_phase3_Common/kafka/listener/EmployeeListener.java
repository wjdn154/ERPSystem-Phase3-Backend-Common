package com.megazone.ERPSystem_phase3_Common.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_Common.user.service.basic_information_management.Users.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmployeeListener {
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "employee-update", groupId = "common-service-group")
    public void handleEmployeeUpdateResponse(Map<String, Object> response) {
//        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("data")) {
                EmployeeShowToDTO employeeShowToDTO = objectMapper.convertValue(response.get("data"), EmployeeShowToDTO.class);
                employeeService.updateEmployee(employeeShowToDTO);
            } else if (response.containsKey("error")) {
//                String error = (String) response.get("error");
                // 에러로직
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }

    @KafkaListener(topics = "employee-save", groupId = "common-service-group")
    public void handleEmployeeSaveResponse(Map<String, Object> response) {
        //        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("data")) {
                EmployeeShowToDTO employeeShowToDTO = objectMapper.convertValue(response.get("data"), EmployeeShowToDTO.class);
                employeeService.saveEmployee(employeeShowToDTO);
            } else if (response.containsKey("error")) {
//                String error = (String) response.get("error");
                // 에러로직
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }
}