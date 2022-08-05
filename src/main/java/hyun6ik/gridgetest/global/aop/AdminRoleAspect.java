package hyun6ik.gridgetest.global.aop;

import hyun6ik.gridgetest.domain.member.constant.MemberRole;
import hyun6ik.gridgetest.global.constant.AuthConstraints;
import hyun6ik.gridgetest.global.error.exception.AuthenticationException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class AdminRoleAspect {

    @Before("@annotation(hyun6ik.gridgetest.global.annotation.AdminUser)")
    public void checkAdminRole() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String role = (String) request.getAttribute(AuthConstraints.MEMBER_ROLE);
        log.info("role = {}", role);
        if (!MemberRole.ADMIN.equals(MemberRole.valueOf(role))) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_ADMIN_ROLE);
        }
    }
}
