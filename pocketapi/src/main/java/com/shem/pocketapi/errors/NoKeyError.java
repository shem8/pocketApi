package com.shem.pocketapi.errors;

/**
 * Created by shem on 27/01/2017.
 */

public class NoKeyError extends Error {
    public String key;

    public NoKeyError(String key) {
        super("Missing key: " + key);
        this.key = key;
    }
}
