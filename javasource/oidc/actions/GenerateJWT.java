// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package oidc.actions;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import oidc.proxies.Audience;

public class GenerateJWT extends CustomJavaAction<java.lang.String>
{
	private final java.lang.String jwk;
	private final oidc.proxies.ENU_SigningAlgorithm algorithm;
	/** @deprecated use jwt.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __jwt;
	private final oidc.proxies.JWT jwt;
	private final java.lang.String secret;

	public GenerateJWT(
		IContext context,
		java.lang.String _jwk,
		java.lang.String _algorithm,
		IMendixObject _jwt,
		java.lang.String _secret
	)
	{
		super(context);
		this.jwk = _jwk;
		this.algorithm = _algorithm == null ? null : oidc.proxies.ENU_SigningAlgorithm.valueOf(_algorithm);
		this.__jwt = _jwt;
		this.jwt = _jwt == null ? null : oidc.proxies.JWT.initialize(getContext(), _jwt);
		this.secret = _secret;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		JWK jwkObj = JWK.parse(jwk);
		Algorithm alg;
		
		RSAPublicKey pubKey = null;
		RSAPrivateKey privKey = null;
		
		if (jwkObj instanceof RSAKey) {
			pubKey = ((RSAKey) jwkObj).toRSAPublicKey();
			privKey = ((RSAKey) jwkObj).toRSAPrivateKey();
		}
		
		switch (algorithm) {
		case HS256:
			alg = Algorithm.HMAC256(secret);
			break;
		case HS384:
			alg = Algorithm.HMAC384(secret);
			break;
		case HS512:
			alg = Algorithm.HMAC512(secret);
			break;
		case RS256:
			alg = Algorithm.RSA256(pubKey, privKey);
			break;
		case RS384:
			alg = Algorithm.RSA384(pubKey, privKey);
			break;
		case RS512:
			alg = Algorithm.RSA512(pubKey, privKey);
			break;
		default:
			throw new CoreException("Unknown algorithm: " + algorithm);
		}
		
		List<IMendixObject> audiences = Core.retrieveByPath(context(), 
				jwt.getMendixObject(), oidc.proxies.Audience.MemberNames.Audience_JWT.toString());
		
		String[] auds = new String[audiences.size()];
		{
			int i = 0;
			for (IMendixObject audienceObj : audiences) {
				Audience aud = Audience.initialize(getContext(), audienceObj);
				auds[i] = aud.getaud();
				i++;
			}
		}
		
		Builder builder = JWT.create();
		if (jwt.getexp() != null) builder.withExpiresAt(jwt.getexp());
		if (jwt.getiss() != null && !jwt.getiss().isBlank()) builder.withIssuer(jwt.getiss());
		if (jwt.getjti() != null && !jwt.getjti().isBlank()) builder.withJWTId(jwt.getjti());
		if (jwt.getkid() != null && !jwt.getkid().isBlank()) builder.withKeyId(jwt.getkid());
		if (jwt.getsub() != null && !jwt.getsub().isBlank()) builder.withSubject(jwt.getsub());
		if (auds.length > 0) builder.withAudience(auds); 
		return builder.sign(alg);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "GenerateJWT";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
