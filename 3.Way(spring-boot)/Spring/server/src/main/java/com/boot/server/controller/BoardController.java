package com.boot.server.controller;

import com.boot.server.dto.Board;
import com.boot.server.dto.BoardImg;
import com.boot.server.dto.UserLikeBoard;
import com.boot.server.dto.Users;
import com.boot.server.service.BoardService;
import com.boot.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping(value = "/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    // 게시판 페이징
    @GetMapping(value = "/get")
    public ResponseEntity<?> getBoard(@RequestParam(value = "page") Long page) throws Exception {
        log.info("BoardController - getBoard()");
        List<Board> result = null;

        result = boardService.getBoard(page*6);

        if (result != null) {
            log.info("게시글 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("게시글 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 검색 결과
    @GetMapping(value = "/getSearch")
    public ResponseEntity<?> getSearch(@RequestParam(value = "page") Long page, @RequestParam(value = "title") String title, @RequestParam(value = "name") String name,
                                            @RequestParam(value = "loc1") String loc1, @RequestParam(value = "loc2") String loc2) throws Exception {
        log.info("BoardController - getSearch()");
        List<Board> result = null;
        Users user = null;
        int userNo = 0;
        page *= 6;

        try {
            if (!name.equals("null")) {
                user = userService.selectByName(name);
                userNo = user.getNo();
            }
            result = boardService.getSearch(page, title, userNo, loc1, loc2);
        } catch(Exception e) {
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }

        if (result != null) {
            log.info("검색 게시글 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("검색 게시글 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시판 정보 요청
    @GetMapping(value = "/getInfo")
    public ResponseEntity<?> getBoardInfo(@RequestParam(value = "boardNo") String boardNo) throws Exception {
        log.info("BoardController - getBoardInfo()");
        Board result = null;

        result = boardService.getBoardInfo(boardNo);

        System.out.println(result);

        if (result != null) {
            log.info("게시글 정보 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("게시글 정보 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시판 이미지 요청
    @GetMapping(value = "/getImages")
    public ResponseEntity<?> getBoardImages(@RequestParam(value = "boardNo") String boardNo) throws Exception {
        log.info("BoardController - getBoardImages()");
        List<BoardImg> result = null;

        result = boardService.getBoardImages(boardNo);

        if (result != null) {
            log.info("게시글 이미지 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("게시글 이미지 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시물 좋아요 클릭
    @GetMapping(value = "/like")
    public ResponseEntity<?> like(@RequestParam(value = "boardNo") String boardNo, @RequestParam(value = "userNo") String userNo) throws Exception {
        log.info("BoardController - like()");
        int result = 0;

        result = boardService.like(boardNo, userNo);

        if (result != 0) {
            log.info("게시글 이미지 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("게시글 이미지 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 좋아요 누른 게시물
    @Secured("ROLE_USER")
    @GetMapping(value = "/likeBoard/{id}")
    public ResponseEntity<?> likeBoard(@PathVariable("id") String userId) throws Exception {
        log.info("BoardController - likeBoard()");
        List<UserLikeBoard> result = null;

        result = boardService.likeBoard(userId);

        if (result != null) {
            log.info("유저 좋아요 목록 리턴 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("유저 좋아요 목록 리턴 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.OK);
        }
    }

    // 게시물 등록
    @Secured("ROLE_USER")
    @PostMapping(value = "/write")
    public ResponseEntity<?> write(@RequestBody Board board) throws Exception {
        int result = 0;
        log.info("BoardController - write()");

        result = boardService.write(board);

        if (result > 0) {
            log.info("게시글 작성 성공! - SUCCESS");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("게시글 작성 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시물 이미지 등록
    @Secured("ROLE_USER")
    @PostMapping(value = "/imgWrite")
    public ResponseEntity<?> imgWrite(@RequestParam(value = "file") MultipartFile[] files, @RequestParam(value = "boardNo") String boardNo, @RequestParam(value = "logoInd") String logoInd) throws Exception {
        log.info("BoardController - imgWrite()");
        int result = 0;
        String UPLOAD_PATH = "D:\\jiwhy\\dev\\project_where\\React\\way\\public\\images"; // 업로드 할 위치

        try {
            for(int i=0; i < files.length; i++) {
                MultipartFile file = files[i];

                String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
                String originName = file.getOriginalFilename(); // ex) 파일.jpg
                String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
                originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
                long fileSize = file.getSize(); // 파일 사이즈

                File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
                if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                    fileSave.mkdirs();
                }

                file.transferTo(fileSave); // fileSave의 형태로 파일 저장

                System.out.println("fileId= " + fileId);
                System.out.println("originName= " + originName);
                System.out.println("fileExtension= " + fileExtension);
                System.out.println("fileSize= " + fileSize);

                if (i == Integer.parseInt(logoInd)) {
                    boardService.logoWrite(boardNo, originName, fileId, fileExtension, fileSize);
                }
                result = boardService.imgWrite(boardNo, originName, fileId, fileExtension, fileSize);
            }
        } catch(IOException e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }

        if (result > 0) {
            log.info("게시글 이미지 등록 성공! - SUCCESS");
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            log.info("게시글 이미지 등록 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시물 수정
    @Secured("ROLE_USER")
    @PostMapping(value = "/modify")
    public ResponseEntity<?> modifyBoard(@RequestBody Board board) {
        log.info("BoardController - modifyBoard()");
        int result = 0;

        log.info(board.toString());

        result = boardService.modifyBoard(board);

        if (result > 0) {
            log.info("게시글 수정 성공! - SUCCESS");
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            log.info("게시글 수정 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

    // 게시물 삭제
    @Secured("ROLE_USER")
    @DeleteMapping(value = "/{boardNo}")
    public ResponseEntity<?> deleteBoard(@PathVariable("boardNo") String boardNo) throws Exception {
        log.info("BoardController - deleteBoard()");
        int result = 0;

        result = boardService.deleteBoard(boardNo);

        if (result > 0) {
            log.info("게시글 삭제 성공! - SUCCESS");
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            log.info("게시글 삭제 실패! - FAIL");
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

}
