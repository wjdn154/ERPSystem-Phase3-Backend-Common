package com.megazone.ERPSystem_phase3_Common.common.config.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatabaseCredentials {
    private final String url;
    private final String username;
    private final String password;
}