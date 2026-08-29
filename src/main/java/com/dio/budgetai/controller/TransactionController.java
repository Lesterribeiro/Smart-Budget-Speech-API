package com.dio.budgetai.controller;

import com.dio.budgetai.service.SpeechService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final SpeechService speechService;

    public TransactionController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> handleAudioTransaction(@RequestParam("file") MultipartFile file) {
        byte[] audioResponse = speechService.processAudioCommand(file.getResource());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"response.mp3\"")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audioResponse);
    }
}
