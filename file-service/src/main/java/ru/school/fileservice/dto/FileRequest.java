package ru.school.fileservice.dto;

import lombok.Data;

@Data
public class FileRequest {
    private String base64;
    private String name;
    private String contentType;
}
