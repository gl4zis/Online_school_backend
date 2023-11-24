package ru.spring.school.online.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.spring.school.online.dto.request.ProfileUpdateDto;
import ru.spring.school.online.dto.response.ProfileInfo;
import ru.spring.school.online.exception.EmailIsTakenException;
import ru.spring.school.online.exception.UsernameIsTakenException;
import ru.spring.school.online.model.security.User;
import ru.spring.school.online.utils.DtoMappingUtils;
import ru.spring.school.online.utils.ValidationUtils;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ValidationUtils validationUtils;
    private final UserService userService;
    private final DtoMappingUtils dtoMappingUtils;

    public ProfileInfo getProfile(String username) throws UsernameNotFoundException {
        User user = userService.getOnlyByUsername(username);
        return dtoMappingUtils.profileFromUser(user);
    }

    public void updateWholeProfile(ProfileUpdateDto dto, String oldUsername)
            throws UsernameNotFoundException, ValidationException, UsernameIsTakenException, EmailIsTakenException {
        validationUtils.validateOrThrowException(dto);
        userService.updateProfile(oldUsername, dto);
    }
}