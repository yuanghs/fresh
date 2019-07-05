package com.fresh.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 简易token生成器
 * @author 74123
 */
public class JwtNut {
    private Map<String, Object> header;
    private Map<String, String> claim;
    private String secret;
    private String issuer;
    private String token;


    public JwtNut() {
        claim = new HashMap<>();
        header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
    }

    public void init(String issuer, String secret) {
        this.issuer = issuer;
        this.secret = secret;
    }

    public void setClaim(String name, String value) {
        claim.put(name, value);
    }

    public String getToken() {
        String token;
        JWTCreator.Builder builder = JWT.create().withHeader(header).withIssuer(issuer);

        for (String key : claim.keySet()) {
            builder.withClaim(key, claim.get(key));
        }

        try {
            token = builder.sign(Algorithm.HMAC256(secret));
            this.token = token;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * tools.token 验证
     * @param token tools.token
     * @return 验证成功true，否则false
     */
    public boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build(); //Reusable verifier instance
            verifier.verify(token);

            return true;
        } catch (UnsupportedEncodingException | JWTVerificationException exception){
            return  false;
        }
    }

    /**
     * 获取token中的所有信息的键值对，生成Map传回
     * @param token tools.token
     * @return 键值对 Map
     */
    public static HashMap<String, String> getMessMap(String token) {
        //获取token中的参数
        DecodedJWT decode = JWT.decode(token);
        Map<String, Claim> claims = decode.getClaims();

        //转化为<String, String>
        HashMap<String, String > map = new HashMap<>();
        for (Map.Entry<String, Claim> entry : claims.entrySet()) {
            map.put(entry.getKey(), entry.getValue().asString());
        }

        return map;
    }

    /**
     * 获取token中信息
     * 通过键获取对应的值
     * @param key 键
     * @param token tools.token
     * @return 值
     */
    public static String getMes( String token, String key) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(key).asString();
    }

    public static void main(String[] args) {
        JwtNut nut = new JwtNut();
        //issuer:作者名（可修改）；secret: 用户签名（可修改）
        nut.init("heeeyou", "chestnut&youyinnn");
        //设置token中的参数
        nut.setClaim("id", "id");
        nut.setClaim("per", "account");
        String token = nut.getToken();
        System.out.println(token);

        //获取token中携带的信息
        //获取所有信息HashMap<String, String>
        HashMap messMap = getMessMap("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ" +
                "oZWVleW91IiwiaWQiOiJpZCIsInBlciI6ImFjY291bnQifQ.qdCYEOEfl9nWmEKxiU7CIDd4_14ZTJX4khGrurG5VYg");
        System.out.println(messMap);
        //通过键获取某一个值
        System.out.println(getMes("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJoZWVleW91IiwiaWQiOi" +
                "JpZCIsInBlciI6ImFjY291bnQifQ.qdCYEOEfl9nWmEKxiU7CIDd4_14ZTJX4khGrurG5VYg", "id"));
    }

}
