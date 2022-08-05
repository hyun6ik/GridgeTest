package hyun6ik.gridgetest.global.interceptor;

import hyun6ik.gridgetest.domain.jwt.constant.GrantType;
import hyun6ik.gridgetest.domain.jwt.service.TokenManager;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.constant.AuthConstraints;
import hyun6ik.gridgetest.global.error.exception.AuthenticationException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isNotRequestMapping(handler)){
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (hasNotAuthAnnotation(handlerMethod)) {
            return true;
        }

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        hasAuthorizationRequestHeader(authorizationHeader);

        isBearerAccessToken(authorizationHeader);

        final String accessToken = removeBearer(authorizationHeader);

        validateAccessToken(accessToken);

        validateTokenExpired(accessToken);

        final Long memberId = tokenManager.getMemberId(accessToken);
        final String memberRole = tokenManager.getRole(accessToken);
        request.setAttribute(AuthConstraints.MEMBER_ID, memberId);
        request.setAttribute(AuthConstraints.MEMBER_ROLE, memberRole);
        return true;

    }

    private void validateTokenExpired(String accessToken) {
        final Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        if (tokenManager.isTokenExpired(tokenClaims.getExpiration())) {
            throw new AuthenticationException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        }
    }

    private void validateAccessToken(String accessToken) {
        if (!tokenManager.validateToken(accessToken)) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    private void hasAuthorizationRequestHeader(String authorizationHeader) {
        if (!(StringUtils.hasText(authorizationHeader))) {
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }
    }

    private boolean isNotRequestMapping(Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        return false;
    }

    private void isBearerAccessToken(String authorizationHeader) {
        final String[] authorizations = authorizationHeader.split(" ");
        if (authorizations.length < 2 || !GrantType.BEARRER.getType().equals(authorizations[0])) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }

    private boolean hasNotAuthAnnotation(HandlerMethod handlerMethod) {
        final LoginUser loginUser = handlerMethod.getMethodAnnotation(LoginUser.class);
        if (loginUser == null) {
            return true;
        }
        return false;
    }

    private String removeBearer(String token) {
        return token.split(GrantType.BEARRER.getType())[1];
    }
}