package com.sparta.academy.enumerator;

public enum UserRoleEnum {
    STAFF(Authority.STAFF),
    MANAGER(Authority.MANAGER);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String STAFF = "STAFF";
        public static final String MANAGER = "MANAGER";
    }
}
