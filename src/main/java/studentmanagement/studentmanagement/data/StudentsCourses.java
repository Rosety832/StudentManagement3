package studentmanagement.studentmanagement.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourses {

  private String id;
  private String memberId;
  private String course;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

}
