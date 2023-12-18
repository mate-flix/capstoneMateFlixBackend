package com.mteflix.capstonemateflixbackend.security.utils;

import java.util.List;

public class SecurityUtils {


    public static List<String> getPublicEndpoints(){
        return List.of(
         "/login",
               "/api/v1/auth/register",
                "/api/v1/auth/verifyEmail"


        );

    }
}
