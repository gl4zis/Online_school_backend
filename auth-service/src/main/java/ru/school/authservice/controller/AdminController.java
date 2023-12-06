package ru.school.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.school.authservice.dto.AuthWithRoleRequest;
import ru.school.authservice.service.AuthService;

@RestController
@RequestMapping("/admin")
@ResponseBody
@RequiredArgsConstructor
@Tag(name = "Admin-auth controller")
public class AdminController {
    private final AuthService authService;

    @Operation(summary = "Registration by admins", description = "Register admins and teachers throw this. " +
            "Access only for admins. Throws 400 (Validation, UsernameTaken) or 403 (No access)")
    @PostMapping("/signup")
    public void signup(@RequestBody AuthWithRoleRequest request) {
        authService.adminSignUp(request);
    }

}