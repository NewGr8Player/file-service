package com.xavier.auth.jwt;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xavier.auth.bean.Auth;
import org.springframework.stereotype.Component;

/**
 * JWT校验
 *
 * @author NewGr8Player
 */
@Component
public class JWTGen {

	/**
	 * 获得token中的信息，无需secret解密也能获得
	 *
	 * @return token中包含的用户名
	 */
	public Auth getAuth(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return JSON.parseObject(jwt.getClaim("auth").asString(), Auth.class);
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 校验 token 是否正确
	 *
	 * @param token  密钥
	 * @param auth   鉴权信息
	 * @param secret 秘钥
	 * @return 是否正确
	 */
	public boolean verify(String token, Auth auth, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			/* 在token中附带了auth信息 */
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("auth", JSON.toJSONString(auth))
					.build();
			/* 验证 token */
			verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
}
