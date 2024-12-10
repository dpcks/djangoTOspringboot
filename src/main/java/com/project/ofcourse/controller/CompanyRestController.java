package com.project.ofcourse.controller;

import com.project.ofcourse.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;

    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autocomplete(@RequestParam String keyword) {
        List<String> companyNames = companyService.autoKeywordCompany(keyword);
        return ResponseEntity.ok(companyNames);
    }
}
