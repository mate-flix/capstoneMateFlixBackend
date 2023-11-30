package com.mteflix.capstonemateflixbackend.config.security.utils;

import java.util.List;

public class SecurityUtils {


    public static List<String> getPublicEndpoints(){
        return List.of(
                "/login",
                "/api/v1/auth/register"
        );

    }
}
