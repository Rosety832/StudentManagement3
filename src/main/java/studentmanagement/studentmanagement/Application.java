package studentmanagement.studentmanagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.data.StudentsCourses;
import studentmanagement.studentmanagement.service.StudentService;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


}