package hyun6ik.gridgetest.interfaces.common.dto;


import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import lombok.Getter;
import lombok.Setter;

public class ReportDto {

    @Getter
    @Setter
    public static class Request {

        private ReportReason reportReason;
    }

    @Getter
    @Setter
    public static class Response {

        private Integer reportCount;

        public Response(Integer reportCount) {
            this.reportCount = reportCount;
        }
    }

}
