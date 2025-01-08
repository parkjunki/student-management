package org.fastcampus.student_management.application.course;
import java.util.List;
import org.fastcampus.student_management.domain.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;

public class CourseService {
  private final CourseCommandRepository courseCommandRepository;
  private final CourseQueryRepository courseQueryRepository;
  private final StudentService studentService;

  public CourseService(CourseCommandRepository courseCommandRepository,
          CourseQueryRepository courseQueryRepository, StudentService studentService) {
    this.courseCommandRepository = courseCommandRepository;
    this.courseQueryRepository = courseQueryRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto);
    courseCommandRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {

    List<Course> courseList = courseQueryRepository.getCourseDayOfWeek(dayOfWeek);

    return courseList.stream().map(CourseInfoDto::new).toList();

  }

  //특정 학생의 수강료를 변경 시키면 특정 학생 수업에 전체에 적용이 되어야 함
  public void changeFee(String studentName, int fee) {

    List<Course> courses = courseQueryRepository.getCourseListByStudent(studentName);
    CourseList courseList = new CourseList(courses);
    courseList.changeAllCoursesFee(fee);

  }
}
