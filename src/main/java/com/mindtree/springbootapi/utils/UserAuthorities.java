package com.mindtree.springbootapi.utils;

public enum UserAuthorities {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_UPDATE("user:update"),
    TOPIC_READ("topic:read"),
    TOPIC_WRITE("topic:write"),
    TOPIC_UPDATE("topic:update"),
    CHAPTER_READ("chapter:read"),
    CHAPTER_WRITE("chapter:write"),
    CHAPTER_UPDATE("chapter:update");

    private String authority;

    UserAuthorities(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
