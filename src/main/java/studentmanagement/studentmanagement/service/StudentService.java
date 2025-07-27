package studentmanagement.studentmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.data.StudentsCourses;
import studentmanagement.studentmanagement.domain.StudentDetail;
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

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      studentsCourse.setMemberId(studentDetail.getStudent().getId());
      studentsCourse.setCourseStartDate(LocalDateTime.now());
      studentsCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourse);
    }
  }

  public String generateNextStudentId() {
    Integer maxId = repository.getMaxStudentId();
    int nextId = (maxId == null) ? 1 : maxId + 1;
    return String.format("%03d",nextId);
  }

  public void registerStudentsCourses(StudentsCourses studentsCourses){
      repository.registerStudentsCourses(studentsCourses);
  }
}

