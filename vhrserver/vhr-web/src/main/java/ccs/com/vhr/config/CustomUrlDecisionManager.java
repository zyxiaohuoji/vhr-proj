package ccs.com.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute configAttribute : collection) {
            String  needRole = configAttribute.getAttribute();
            if("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请登录!");
                } else {
//                  该方法不抛任何异常表示 ok
                    return;
                }
            }
//          获取当前用户的角色
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

            for (GrantedAuthority authority : authorities){
                if (authority.getAuthority().equals(needRole)){
                     return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
