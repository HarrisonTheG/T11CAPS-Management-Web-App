package sg.edu.iss.caps.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sg.edu.iss.caps.utility.CapRealm;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "CapRealm")
    public CapRealm capRealm(){
        CapRealm capRealm = new CapRealm();
        capRealm.setAuthorizationCachingEnabled(false);
        return capRealm;
    }

    @Bean(name = "CapSecurityManager")
    public DefaultWebSecurityManager capSecurityManager(@Qualifier("CapRealm") CapRealm capRealm){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(capRealm);

        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean capFilterFactoryBean(@Qualifier("CapSecurityManager") DefaultWebSecurityManager capSecurityManager ){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(capSecurityManager);
        //权限
        //声明没有权限的情况下走的接口名称
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        shiroFilterFactoryBean.setLoginUrl("/account/authenticate");
//        //告诉shiro有用什么权限可以访问什么接口
        //如果不加注解不加map，默认没有权限控制
        Map map = new HashMap<>();
        map.put("/account/login","anon");
//         map.put("/deleteById","perms[user_delete]");
//        //将访问接口的权限放置到shiroFileter中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

}
