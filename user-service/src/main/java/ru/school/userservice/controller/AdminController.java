package ru.school.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.school.userservice.dto.ProfileData;
import ru.school.userservice.dto.request.RegWithRoleRequest;
import ru.school.userservice.service.AuthService;
import ru.school.userservice.service.ProfileService;
import ru.school.userservice.service.UserService;

@RestController
@RequestMapping("/admin")
@ResponseBody
@RequiredArgsConstructor
@Tag(name = "Admin-auth controller")
public class AdminController {
    private final AuthService authService;
    private final ProfileService profileService;
    private final UserService userService;

    @Operation(summary = "Registration by admins", description = "Register admins and teachers throw this. " +
            "Access only for admins. Throws 400 (Validation, UsernameTaken) or 403 (No access)")
    @PostMapping("/signup")
    public void signup(@RequestBody RegWithRoleRequest request) {
        authService.adminSignUp(request);
    }

    @Operation(summary = "Returns all registered user profiles", description = "Admin access")
    @GetMapping("/users")
    public Iterable<ProfileData> allUsers() {
        return profileService.getAllProfiles();
    }

    @Operation(summary = "Block or unblock user account", description = "Admin access")
    @PutMapping("/block/{user}")
    public void lockOrUnlockUser(@PathVariable Long user, @RequestParam boolean lock) {
        userService.setLock(user, lock);
    }

    @PutMapping("/confirm/{user}")
    public void updateTeacherConfirm(@PathVariable Long user, @RequestParam boolean confirm) {
        userService.setConfirm(user, confirm);
    }
}