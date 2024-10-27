package com.malakhov.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/sse")
public class EventController {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GetMapping("/events")
    public SseEmitter handleSSE() {
        SseEmitter emitter = new SseEmitter();

        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data(String.format("Event %d", i));
                    emitter.send(event);
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    emitter.completeWithError(ex);
                    return;
                }
            }
            emitter.complete();
        });

        return emitter;
    }

    @GetMapping("/rbe")
    public ResponseEntity<ResponseBodyEmitter> handleRbe() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executorService.execute(() -> {
            try {
                emitter.send(
                        "/rbe" + " @ " + new Date(), MediaType.TEXT_PLAIN);
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return new ResponseEntity<>(emitter, HttpStatus.OK);
    }
}