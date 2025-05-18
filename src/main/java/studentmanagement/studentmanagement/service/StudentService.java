package studentmanagement.studentmanagement.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentmanagement.studentmanagement.Student;
import studentmanagement.studentmanagement.Student;
import studentmanagement.studentmanagement.StudentsCourses;
import studentmanagement.studentmanagement.StudentsCourses;
import studentmanagement.studentmanagement.StudentRepository;

@Slf4j
@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentAgeList(){
    return repository.search().stream()
        .filter(student ->student.getAge() >= 30 && student.getAge() <= 39)
        .collect(Collectors.toList());
  }

  public List<StudentsCourses> searchStudentsKirieCourses(){
    return repository.searchStudentsCourses().stream()
        .filter(sc -> "切り絵コース".equals(sc.getCourse()))
        .collect(Collectors.toList());
  }
}

