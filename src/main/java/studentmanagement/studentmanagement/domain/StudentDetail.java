package studentmanagement.studentmanagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

}
