package com.megazone.ERPSystem_phase3_Common.common.user.service.basic_information_management.Users;

import com.megazone.ERPSystem_phase3_Common.config.security.AuthRequest;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.PermissionDTO;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.UsersShowDTO;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UsersService {
    List<UsersShowDTO> findAllUsers();

    ResponseEntity<String> registerUser(AuthRequest authRequest) throws SQLException;

    UsersShowDTO findUserById(Long id);

    UsersShowDTO updateUser(Long id, UsersShowDTO usersDTO);

    void deleteUsers(Long usersId);

    ResponseEntity<Object> getPermissionByUsername(String username);

    //UsersResponseDTO assignRoleToUser(Long userId, Long roleId);

    ResponseEntity<Object> assignPermissionToUser(String username, PermissionDTO permissionDTO);

    //Role assignPermissionToRole(Long roleId, Long permissionId);

    ResponseEntity<Object> createAuthenticationToken(AuthRequest authRequest, String tenantId);

    ResponseEntity<Object> createRefreshToken(Map<String, String> refreshTokenRequest);

}
