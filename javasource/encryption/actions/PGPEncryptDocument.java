// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package encryption.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import encryption.pgp.PGPFileProcessor;

/**
 * Encrypt the FileDocument using PGP encryption.
 * This is allowed to be the same FileDocument instance and the action will just store the encrypted file in the provided entity.
 * 
 * The certificate must be a valid public PGP key provided by the external party.
 * 
 * This action will either return true or an exception
 */
public class PGPEncryptDocument extends CustomJavaAction<java.lang.Boolean>
{
	/** @deprecated use ExternalPublicKey.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __ExternalPublicKey;
	private final system.proxies.FileDocument ExternalPublicKey;
	/** @deprecated use DocumentToEncrypt.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __DocumentToEncrypt;
	private final system.proxies.FileDocument DocumentToEncrypt;
	/** @deprecated use OutputDocument.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __OutputDocument;
	private final system.proxies.FileDocument OutputDocument;

	public PGPEncryptDocument(
		IContext context,
		IMendixObject _externalPublicKey,
		IMendixObject _documentToEncrypt,
		IMendixObject _outputDocument
	)
	{
		super(context);
		this.__ExternalPublicKey = _externalPublicKey;
		this.ExternalPublicKey = _externalPublicKey == null ? null : system.proxies.FileDocument.initialize(getContext(), _externalPublicKey);
		this.__DocumentToEncrypt = _documentToEncrypt;
		this.DocumentToEncrypt = _documentToEncrypt == null ? null : system.proxies.FileDocument.initialize(getContext(), _documentToEncrypt);
		this.__OutputDocument = _outputDocument;
		this.OutputDocument = _outputDocument == null ? null : system.proxies.FileDocument.initialize(getContext(), _outputDocument);
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE

		PGPFileProcessor p = new PGPFileProcessor();
		p.setInputFileDocument(this.DocumentToEncrypt.getMendixObject());
		p.setOutputFileDocument(this.OutputDocument.getMendixObject());
		p.setAsciiArmored(true);
		p.setPublicKeyFileName(this.ExternalPublicKey.getMendixObject());

		return p.encrypt(getContext());
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "PGPEncryptDocument";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
