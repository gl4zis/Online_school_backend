package ru.spring.school.online.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.spring.school.online.model.security.Student;
import ru.spring.school.online.model.security.User;

import java.util.Date;

@Data
public class StudentRegister {
    @NotEmpty(message = "{user.username.empty}")
    @Size(message = "{user.username.wrongSize}", max = 20, min = 3)
    @Pattern(message = "{user.username.wrongPattern}", regexp = "^[\\w\\d]+$")
    private final String username;

    @NotEmpty(message = "{user.password.empty}")
    @Size(message = "{user.password.wrongSize}", max = 50, min = 6)
    @Pattern(message = "{user.password.wrongPattern}", regexp = "^\\S+$")
    private final String password;

    @NotEmpty(message = "{user.firstname.empty}")
    @Size(message = "{user.firstname.wrongSize}", max = 20, min = 2)
    @Pattern(message = "{user.firstname.wrongPattern}", regexp = "^[\\wа-яА-Я]+$")
    private String firstname;

    @NotEmpty(message = "{user.lastname.empty}")
    @Size(message = "{user.lastname.wrongSize}", max = 20, min = 2)
    @Pattern(message = "{user.lastname.wrongPattern}", regexp = "^[\\wа-яА-Я]+$")
    private String lastname;

    @Past(message = "{user.birthdate.inFuture}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final Date dateOfBirth;

    @NotNull(message = "{user.grade.empty}")
    @Min(message = "{user.grade.smaller}", value = 1)
    @Max(message = "{user.grade.bigger}", value = 11)
    private final Byte grade;

    public Student toStudent(PasswordEncoder passwordEncoder) {
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setDateOfBirth(dateOfBirth);
        student.setGrade(grade);
        student.setLocked(false);
        student.setRole(User.Role.STUDENT);
        return student;
    }

}
