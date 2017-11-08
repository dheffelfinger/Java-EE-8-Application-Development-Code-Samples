package net.ensode.javaee8book.security.basicauthexample;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class DummyIdentityStore implements IdentityStore {

  Set<String> adminRoleSet;
  Set userRoleSet;
  Set userAdminRoleSet;

  @PostConstruct
  public void init() {
    adminRoleSet = new HashSet<>(Arrays.asList("admin"));
    userRoleSet = new HashSet<>(Arrays.asList("user"));
    userAdminRoleSet = new HashSet<>(Arrays.asList("user", "admin"));
  }

  @Override
  public CredentialValidationResult validate(Credential credential) {
    UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;

    CredentialValidationResult credentialValidationResult;

    if (usernamePasswordCredential.compareTo(
            "david", "secret")) {
      credentialValidationResult = new CredentialValidationResult("david", adminRoleSet);
    }
    else if (usernamePasswordCredential.compareTo("alan", "iforgot")) {
      credentialValidationResult = new CredentialValidationResult("alan", userAdminRoleSet);
    }
    else {
      credentialValidationResult = CredentialValidationResult.INVALID_RESULT;
    }

    return credentialValidationResult;
  }

}
