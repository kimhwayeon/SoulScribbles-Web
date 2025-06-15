package com.soulscribble.soulscribbles_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/emotion-diary/upload")
public class AudioController {

    private static final String AUDIO_SAVE_DIR = System.getProperty("user.dir") + "/audio"; // (실행 경로 기반 폴더 생성
    ;

    @PostMapping("/audio")
    public ResponseEntity<Map<String, String>> uploadAudio(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + ".webm";
            Path savePath = Paths.get(AUDIO_SAVE_DIR, fileName);

            Files.createDirectories(savePath.getParent());
            Files.write(savePath, file.getBytes());

            String fileUrl = "/audio/" + fileName;
            Map<String, String> result = new HashMap<>();
            result.put("audioUrl", fileUrl);
            return ResponseEntity.ok(result);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "오디오 저장 실패: " + e.getMessage()));
        }
    }

}
