package com.abstractrestapplication.utils;

import com.abstractrestapplication.models.Token;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.time.LocalDateTime;

/**
 * Created by alex on 1/13/16.
 */
public final class AuthUtils {
    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
    private static final String TOKEN_SECRET = "aliceinwonderland";
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final int FOURTEEN = 14;

    /**
     * A constructor, what else.
     */
    private AuthUtils() {
        // now you see me, now you don't
    }

    /**
     * Creates a token.
     *
     * @param remoteHost the remote host address, i.e. 127.0.0.1
     * @param userId     the user id.
     * @return a token.
     * @throws JOSEException an exception.
     */
    public static Token createToken(final String remoteHost, final String userId) throws JOSEException {
        final JWTClaimsSet claim = new JWTClaimsSet();
        claim.setSubject(userId);
        claim.setIssuer(remoteHost);
        claim.setIssueTime(DateUtil.toDate(LocalDateTime.now()));
        claim.setExpirationTime(DateUtil.toDate(LocalDateTime.now().plusDays(FOURTEEN)));

        final JWSSigner signer = new MACSigner(TOKEN_SECRET);
        SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
        jwt.sign(signer);

        return new Token(jwt.serialize());
    }

    /**
     * Returns a serialized token.
     *
     * @param authHeader an authorization header.
     * @return a serialized token.
     */
    public static String getSerializedToken(final String authHeader) {
        return authHeader.split(" ")[1];
    }
}
