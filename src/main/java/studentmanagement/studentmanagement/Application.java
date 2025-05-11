package studentmanagement.studentmanagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studentmanagement.studentmanagement.service.StudentService;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  private StudentService studentService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/studentAgeList")
  public List<Student> getStudentAgeList() {
    return studentService.searchStudentAgeList();
  }

  @GetMapping("/studentsKirieCourses")
  public List<StudentsCourses> searchStudentsKirieCourses() {
    return studentService.searchStudentsKirieCourses();
  }
}