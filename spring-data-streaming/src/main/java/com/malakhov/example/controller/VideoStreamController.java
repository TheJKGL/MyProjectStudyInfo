package com.malakhov.example.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoStreamController {

    private final ResourceLoader resourceLoader;

    public VideoStreamController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping
    public ResponseEntity<byte[]> streamVideo(@RequestHeader HttpHeaders headers) throws IOException {
        Path videoPath = Paths.get("/Users/yema/Documents/Тур №1 Малахова І.В./video1.mp4");
        Resource videoResource = resourceLoader.getResource("file:" + videoPath.toString());

        long fileSize = Files.size(videoPath);
        List<HttpRange> ranges = headers.getRange();
        System.out.println(ranges);

        if (ranges.isEmpty()) {
            byte[] fullContent = Files.readAllBytes(videoPath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, URLConnection.guessContentTypeFromName(videoResource.getFilename()))
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize))
                    .body(fullContent);
        } else {
            HttpRange range = ranges.get(0);
            long start = range.getRangeStart(fileSize);
            long end = range.getRangeEnd(fileSize);
            long rangeLength = end - start + 1;

            try (InputStream inputStream = Files.newInputStream(videoPath)) {
                inputStream.skip(start);
                byte[] partialContent = StreamUtils.copyToByteArray(inputStream);
                
                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                        .header(HttpHeaders.CONTENT_TYPE, URLConnection.guessContentTypeFromName(videoResource.getFilename()))
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(rangeLength))
                        .header(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + fileSize)
                        .body(partialContent);
            }
        }
    }
}