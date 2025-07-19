package studentmanagement.studentmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
    //import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import studentmanagement.studentmanagement.controller.converter.StudentConverter;
import studentmanagement.studentmanagement.data.Student;
import studentmanagement.studentmanagement.data.StudentsCourses;
import studentmanagement.studentmanagement.domain.StudentDetail;
import studentmanagement.studentmanagement.service.StudentService;
import org.springframework.ui.Model;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCoursesList();

    model.addAttribute("studentList", converter.convertStudentDetails(students,studentsCourses));
    return "studentList";
  }

  @GetMapping("/studentsCoursesList")
  public String showStudentsCoursesList(Model model){
    List<StudentsCourses> courses = service.searchStudentsCoursesList();
    model.addAttribute("studentsCoursesList",courses);
    return "studentsCoursesList";
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model){
    model.addAttribute("studentDetail",new StudentDetail());
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
    if(result.hasErrors()){
      return "registerStudent";
    }
    //新規受講生情報を登録する処理を実装する。
    //コース情報も一緒に登録できるように実装する。コースは単体で良い。

    // Student の登録
    Student student = studentDetail.getStudent();
    student.setId(service.generateNextStudentId());

    service.insertStudent(student);

    // StudentsCourses の登録（1件）
    List<StudentsCourses> scList = studentDetail.getStudentsCourses();
    if (scList != null && !scList.isEmpty()) {
      StudentsCourses sc = scList.get(0);
      sc.setMemberId(student.getId());
      service.insertStudentsCourses(sc);
    }

    return "redirect:/studentList";
  }

}
