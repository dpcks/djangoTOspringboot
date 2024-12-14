package com.project.ofcourse.controller.stack;

import com.project.ofcourse.service.StackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stack")
@RequiredArgsConstructor
public class StackRestController {

    private final StackService stackService;

    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autocomplete(@RequestParam String keyword) {
        List<String> stackNames = stackService.autoKeywordStack(keyword);
        return ResponseEntity.ok(stackNames);
    }
}
