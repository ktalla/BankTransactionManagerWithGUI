package com.example.javafxproject;

public enum Campus {
    NEW_BRUNSWICK(0),
    NEWARK(2),
    CAMDEN(1),
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
            if(campusName.equals("New Brunswick"))
                return NEW_BRUNSWICK;
            if(campusName.equals("Camden"))
                return CAMDEN;
            if(campusName.equals("Newark"))
                return NEWARK;
            return UNKNOWN;
    }
}