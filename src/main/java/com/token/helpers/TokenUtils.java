package com.token.helpers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtils {
    private static final String MAIGC_KEY = "they_are_everywhere!";

    public String createToken(UserDetails userDetails) {
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;
        return userDetails.getUsername() + ":" + expires + ":" + computeSignature(userDetails, expires);
    }

    private String computeSignature(UserDetails userDetails, long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername()).append(":");
        signatureBuilder.append(expires).append(":");
        signatureBuilder.append(userDetails.getPassword()).append(":");
        signatureBuilder.append(TokenUtils.MAIGC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch(NoSuchAlgorithmException e){
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    public String getUserNameFromToken(String authToken) {
        if(null == authToken) {
            return null;
        }
        return authToken.split(":")[0];
    }

    public boolean validateToken(String authToken, UserDetails details) {
        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];
        String signatureToMatch = computeSignature(details, expires);
        return expires >= System.currentTimeMillis() && signature.equals(signatureToMatch);
    }
}
