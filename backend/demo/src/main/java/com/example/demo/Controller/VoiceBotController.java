package com.example.demo.Controller;

import com.example.demo.Service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class VoiceBotController {

    @Autowired
    private VoiceService voiceService;

    @PostMapping("/chat")
    public ResponseEntity<?> handleWebhook(@RequestBody Map<String, Object> request) {
        String response = voiceService.processUserInput(request);
        return ResponseEntity.ok(Map.of("response", response));
    }
}

