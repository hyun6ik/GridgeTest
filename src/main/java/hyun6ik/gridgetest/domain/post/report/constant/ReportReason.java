package hyun6ik.gridgetest.domain.post.report.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportReason {

    SPAM("스팸"),SEXUAL("나체 이미지 또는 성적 행위"),DONT_LIKE("마음에 들지 않습니다."),
    SCAM("사기 또는 거짓"), HATE_SPEECH("혐오 발언 또는 상징"), FALSE_INFORMATION("거짓 정보"),
    BULLYING("따돌림 또는 괴롭힘"), VIOLENCE("폭력 또는 위험한 단체"),
    INTELLECTUAL_PROPERTY_INFRINGEMENT("지식 재산권 침해");

    private final String description;
}
