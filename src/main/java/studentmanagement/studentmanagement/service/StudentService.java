package studentmanagement.studentmanagement.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.data.StudentsCourses;
import studentmanagement.studentmanagement.repository.StudentRepository;

@Slf4j
@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }


  public List<Student> searchStudentList(){
    return repository.search();
  }


  public List<StudentsCourses> searchStudentsCoursesList(){
    return repository.searchStudentsCourses();
  }

  public void insertStudent(Student student) {
    repository.insertStudent(student);
  }

  public String generateNextStudentId() {
    Integer maxId = repository.getMaxStudentId();
    int nextId = (maxId == null) ? 1 : maxId + 1;
    return String.format("%03d",nextId);
  }

  public void insertStudentsCourses(StudentsCourses studentsCourses) {
    repository.insertStudentsCourses(studentsCourses);
  }
}

