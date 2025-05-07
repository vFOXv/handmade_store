package com.ua.teamchallenge.handmadestore.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController("/upload")
public class FileUploadController {
    // Папка для сохранения файлов
    private static final String UPLOAD_DIR = "C:/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Проверка, что файл не пустой
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Файл не выбран");
            }

            // Создание директории, если она не существует
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            // Формирование пути для сохранения файла
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Сохранение файла
            Files.write(filePath, file.getBytes());

            return ResponseEntity.ok("Файл успешно загружен: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}
