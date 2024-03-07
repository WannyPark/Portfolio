package com.boot.server.controller;

import com.boot.server.dto.Board;
import com.boot.server.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    // 전국 맛집추천 게시글 top5
    @GetMapping(value = "/all")
    public ResponseEntity<?> allLocTop() {
        log.info("HomeController - allLocTop()");
        List<Board> result = null;

        result = homeService.allLocTop();

        if (result != null) {
            log.info("게시글 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("게시글 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

}
