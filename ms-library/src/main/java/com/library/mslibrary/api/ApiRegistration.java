package com.library.mslibrary.api;

public interface ApiRegistration {

    String SERVICE_ID = "LIBRARY";

    String REST_PREFIX = "/ms-library";

    String REST_USERS = "/users";

    String REST_GET_USER_BY_EMAIL = "/findUserByEmail";

    String REST_SAVE_USER = "/saveUser";
}
