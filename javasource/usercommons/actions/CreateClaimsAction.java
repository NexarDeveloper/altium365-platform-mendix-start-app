// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package usercommons.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import java.util.ArrayList;
import java.util.List;
import com.mendix.core.Core;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IMendixObjectMember;

public class CreateClaimsAction extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private final java.lang.String EntityName;
	/** @deprecated use UserProvisioning.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __UserProvisioning;
	private final usercommons.proxies.UserProvisioning UserProvisioning;

	public CreateClaimsAction(
		IContext context,
		java.lang.String _entityName,
		IMendixObject _userProvisioning
	)
	{
		super(context);
		this.EntityName = _entityName;
		this.__UserProvisioning = _userProvisioning;
		this.UserProvisioning = _userProvisioning == null ? null : usercommons.proxies.UserProvisioning.initialize(getContext(), _userProvisioning);
	}

	@java.lang.Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		// BEGIN USER CODE
		IMendixObject  ClaimList = Core.instantiate(getContext(), EntityName);
	      // BEGIN USER CODE
	      List<IMendixObject> claims = new ArrayList();

	      java.util.List<? extends IMendixObjectMember<?>> members = ClaimList.getPrimitives(getContext());
	      for (IMendixObjectMember<?> m : members) {
	         String Name = m.getName();
	         IMendixObject claim = com.mendix.core.Core.microflowCall("UserCommons.CreateClaim")
	               .inTransaction(true)
	               .withParam("ClaimName", Name)
				   .withParam("UserProvisioning", this.__UserProvisioning)
	               .execute(this.getContext());
	         claims.add(claim);
	      }
	      return claims;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "CreateClaimsAction";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
