package com.bridgeit.token;

import java.sql.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Tokens {
	private static String KEY="ARefbnionUIO";
	public static String generateToken(int id)
	{
		long currentTime=System.currentTimeMillis();
		Date currentDate=new Date(currentTime);
		Date expireDate=new Date(currentTime+ 24*60*60*1000);
		
		
		String getToken=Jwts.builder()
				.setId(Integer.toString(id))
				.setIssuedAt(currentDate)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS256,KEY)
				.compact().toString();
		
		return getToken;
	}
	 public static int getId(String token)
	 {
		 int id=0;
		 
		 Claims claim = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
		 id=Integer.parseInt(claim.getId());
		 return id;
	 }
}
