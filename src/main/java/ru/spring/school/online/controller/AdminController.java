package ru.spring.school.online.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.school.online.dto.request.AdminOrTeacherRegDto;
import ru.spring.school.online.dto.response.MessageResponse;
import ru.spring.school.online.service.AuthService;
import ru.spring.school.online.utils.DtoMappingUtils;

@RestController
@Tag(name = "Controller for admins tools", description = "All admins functionality works through it")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final DtoMappingUtils dtoMappingUtils;
    private final AuthService authService;

    @Operation(summary = "Register any user",
            description = "Admin can register new user, set any roles for this account through it")
    @PostMapping("/register")
    public ResponseEntity<?> regAdmin(@RequestBody AdminOrTeacherRegDto regDto) {
        authService.registerUtil(dtoMappingUtils.newUser(regDto));
        return ResponseEntity.ok(new MessageResponse("User was successfully registered"));
    }
}
