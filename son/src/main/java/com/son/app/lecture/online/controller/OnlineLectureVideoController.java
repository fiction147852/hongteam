package com.son.app.lecture.online.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class OnlineLectureVideoController {

    @GetMapping("/video/{fileName}")
    public ResponseEntity<ResourceRegion> getVideo(@PathVariable String fileName, @RequestHeader HttpHeaders headers)
            throws IOException {
        ClassPathResource video = new ClassPathResource("static/video/" + fileName);
        ResourceRegion region = resourceRegion(video, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

    private ResourceRegion resourceRegion(ClassPathResource video, HttpHeaders headers) throws IOException {
        long contentLength = video.contentLength();
        long rangeStart = 0;
        long rangeEnd = Math.min(1024 * 1024, contentLength - 1);
        if (headers.getRange() != null && !headers.getRange().isEmpty()) {
            HttpRange range = headers.getRange().get(0);
            rangeStart = range.getRangeStart(contentLength);
            rangeEnd = range.getRangeEnd(contentLength);
        }
        return new ResourceRegion(video, rangeStart, rangeEnd - rangeStart + 1);
    }
}