// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package oidc.actions;

import java.util.LinkedList;
import java.util.List;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;

public class CreatePublicJWKKeySet extends CustomJavaAction<java.lang.String>
{
	private final java.lang.String signJWK;
	private final java.lang.String encryptJWK;

	public CreatePublicJWKKeySet(
		IContext context,
		java.lang.String _signJWK,
		java.lang.String _encryptJWK
	)
	{
		super(context);
		this.signJWK = _signJWK;
		this.encryptJWK = _encryptJWK;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		List<JWK> jwks = new LinkedList<>();
		if (signJWK != null && !signJWK.isBlank())
			jwks.add(JWK.parse(signJWK));
		if (encryptJWK != null && !encryptJWK.isBlank())
			jwks.add(JWK.parse(encryptJWK));
		
		JWKSet set = new JWKSet(jwks);
		return set.toPublicJWKSet().toString();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "CreatePublicJWKKeySet";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
