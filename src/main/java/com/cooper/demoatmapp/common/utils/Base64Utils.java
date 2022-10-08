package com.cooper.demoatmapp.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Base64Utils {

    public static String encode(String value) {
        Base64.Encoder base64Encoder = Base64.getEncoder();
        byte[] encodedBytes = base64Encoder.encode(value.getBytes());
        return new String(encodedBytes);
    }

    public static String decode(String encodedValue) {
        Base64.Decoder base64Decoder = Base64.getDecoder();
        byte[] decodedBytes = base64Decoder.decode(encodedValue);
        return new String(decodedBytes);
    }

}
