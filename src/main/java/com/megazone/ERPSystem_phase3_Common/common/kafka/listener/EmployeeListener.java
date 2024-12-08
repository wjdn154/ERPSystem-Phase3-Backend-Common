package com.megazone.ERPSystem_phase3_Common.common.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.EmployeeDataDTO;
import com.megazone.ERPSystem_phase3_Common.config.KafkaProducerHelper;
import com.megazone.ERPSystem_phase3_Common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_Common.common.user.service.basic_information_management.Users.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmployeeListener {
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;
    private final KafkaProducerHelper kafkaProducerHelper;

    @KafkaListener(topics = "employee-update", groupId = "common-service-group")
    public void handleEmployeeUpdateResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("updateData")) {
                EmployeeDataDTO employeeDataDTO = objectMapper.convertValue(response.get("updateData"), EmployeeDataDTO.class);
                if(employeeDataDTO.getLastName().contains("홍")) {
                    throw new RuntimeException("에러테스트");
                };
                employeeService.updateEmployee(employeeDataDTO);
                kafkaProducerHelper.sagaSendSuccessResponse(requestId,"common-service-group");
            } else if (response.containsKey("error")) {
                kafkaProducerHelper.sagaSendErrorResponse(requestId,"common-service-group",response.get("error").toString());
            }
        } catch (Exception e) {
            kafkaProducerHelper.sagaSendErrorResponse(requestId,"common-service-group",e.getMessage());
        } finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }

    @KafkaListener(topics = "employee-update-compensation", groupId = "common-service-group")
    public void handleEmployeeUpdateCompensation(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("originData")) {
                EmployeeDataDTO employeeDataDTO = objectMapper.convertValue(response.get("originData"), EmployeeDataDTO.class);
                System.out.println(employeeDataDTO.getLastName().concat(employeeDataDTO.getFirstName()) + " 사원 보상트랜잭션 실행");
                employeeService.updateEmployee(employeeDataDTO);
                System.out.println("보상트렌젝션 완료");
//                kafkaProducerHelper.sagaSendSuccessResponse(requestId,"common-service-group");
            } else if (response.containsKey("error")) {
//                kafkaProducerHelper.sagaSendErrorResponse(requestId,"logistics-service-group",response.get("error").toString());
            }
        } catch (Exception e) {
//            kafkaProducerHelper.sagaSendErrorResponse(requestId,"logistics-service-group",e.getMessage());
            e.printStackTrace();
        } finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }

    @KafkaListener(topics = "employee-save", groupId = "common-service-group", autoStartup = "true")
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