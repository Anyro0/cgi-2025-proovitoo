package com.Anyro0.flight.response;

import lombok.Data;

@Data
public class UserRequest {
    private UserData data;

    @Data
    public static class UserData {
        private String username;
        private String password;
    }
}
