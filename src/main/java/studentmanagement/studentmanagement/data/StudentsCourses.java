package studentmanagement.studentmanagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourses {

  private String id;
  private String memberId;
  private String course;
  private String courseStartDate;
  private String courseEndDate;

}
