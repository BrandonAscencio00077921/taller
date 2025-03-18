package com.andra.proyecto.Utils;

import io.jsonwebtoken.Jwts;

public class SecurityConstants {
    public static final long JWT_EXPIRATION_TOKEN = 300000; // 5 MINUTOS
    public static final String JWT_SIGN = String.valueOf(Jwts.SIG.HS256.key().build());
}
