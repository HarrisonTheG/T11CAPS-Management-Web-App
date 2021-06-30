package sg.edu.iss.caps.utility;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CapRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String code=(String) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());

        if(!username.equals("PanCX")){
            throw new UnknownAccountException();
        }
        if(!password.equals("student")){
            throw new IncorrectCredentialsException();
        }

        AuthenticationInfo info=new SimpleAuthenticationInfo(username,password,getName());

        return info;
    }
}
