package utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import models.entities.User;

/*
 * //https://docs.oracle.com/javase/tutorial/jndi/ldap/exceptions.html
 */

public class Ldap {

	private String server = "ldap://lv-dc1.isd.dp.ua:389";
	private String domain = "isd.dp.ua";
	private String searchBase = "dc=isd,dc=dp,dc=ua";
	private LdapContext ctx;

	public Ldap(String login, String passw) throws NamingException {

		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, login + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, passw);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, server);
		env.put("java.naming.ldap.attributes.binary", "objectSID");
		// env.put("com.sun.jndi.ldap.trace.ber", System.err); //for debug

		ctx = new InitialLdapContext(env, null);
	}

	public User search(String login) throws NamingException {

		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + login + "))";

		NamingEnumeration<SearchResult> results = ctx.search(searchBase, searchFilter, searchControls);

		if (results.hasMoreElements()) {
			Attributes attrs = ((SearchResult) results.next()).getAttributes();
			User user = new User();
			user.setId(attrs.get("sAMAccountName").get().toString());
			user.setEmail(attrs.get("mail").get().toString());
			user.setFullname(attrs.get("CN").get().toString());
			user.setDepartment(attrs.get("department").get().toString());
			user.setActive(1);
			return user;
		}

		return null;
	}

}
