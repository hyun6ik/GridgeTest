package hyun6ik.gridgetest.domain.login.service.kakao;

import hyun6ik.gridgetest.domain.login.feign.kakao.KakaoClient;
import hyun6ik.gridgetest.domain.login.service.SocialLoginService;
import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.constant.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoLoginService implements SocialLoginService {

    private final KakaoClient kakaoClient;

    @Override
    public boolean support(String memberType) {
        return MemberType.KAKAO.equals(MemberType.from(memberType));
    }

    @Override
    public SocialUserInfo getUserInfo(String accessToken) {
        final KakaoUserInfo kakaoUserInfo = kakaoClient.requestKakaoUserInfo(accessToken);

        final String nickname = kakaoUserInfo.getKakaoAccount().getProfile().getNickname();

        return SocialUserInfo.builder()
                .name(nickname)
                .memberType(MemberType.KAKAO)
                .build();
    }
}
