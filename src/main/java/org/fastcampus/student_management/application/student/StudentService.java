package org.fastcampus.student_management.application.student;

import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.StudentRepository;

public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void saveStudent(StudentInfoDto studentInfoDto) {
    Student student = new Student(studentInfoDto.getName(), studentInfoDto.getAge(), studentInfoDto.getAddress());
    studentRepository.save(student);
  }

  public Student getStudent(String name) {
    return studentRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));
  }

  //활동 상태에서 활동 상태로, 비활성 상태에서 비활성 상태로 변경이 되어서는 안 됨
  public void activateStudent(String name) {
    // TODO: 과제 구현 부분
    Student student = getStudent(name);
    student.activate();
  }

  public void deactivateStudent(String name) {
    // TODO: 과제 구현 부분
    Student student = getStudent(name);
    student.deactivate();
  }
}
