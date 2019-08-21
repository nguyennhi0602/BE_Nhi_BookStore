package com.nhi.bookstore.configurations;

public class Constants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 30*24*60*60;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final String ROLE_MEMBER="ROLE_MEMBER";
    public static final String ROLE_ADMIN="ROLE_ADMIN";
}
