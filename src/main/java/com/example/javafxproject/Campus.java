package com.example.javafxproject;

public enum Campus {
    NEW_BRUNSWICK(0),
    NEWARK(1),
    CAMDEN(2),
    UNKNOWN(-1); // Default for invalid codes

    private final int code;

    Campus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Campus fromCode(int code) {
        for (Campus campus : values()) {
            if (campus.code == code) {
                return campus;
            }
        }
        return UNKNOWN; //invalid codes
    }

    public static Campus fromString(String campusName) {
        for (Campus campus : values()) {
            if (campus.name().equals(campusName)) {
                return campus;
            }
        }
        return UNKNOWN;
    }
}