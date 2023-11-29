package ru.school.fileservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.school.fileservice.exception.InvalidFileException;
import ru.school.fileservice.exception.InvalidTokenException;
import ru.school.fileservice.service.FileService;

import java.io.FileNotFoundException;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/{id}")
    public String getFile(@PathVariable("id") Long id) throws FileNotFoundException {
        return fileService.getFileBase64(id);
    }

    @PostMapping
    public Long createFile(@RequestBody MultipartFile file, HttpServletRequest request)
            throws InvalidFileException {
        return fileService.saveNewFile(file, request.getHeader("Authorization"));
    }

    @DeleteMapping("/{id}")
    public void removeFile(@PathVariable("id") Long id, HttpServletRequest request)
        throws InvalidTokenException {
        fileService.removeFile(id, request.getHeader("Authorization"));
    }
}