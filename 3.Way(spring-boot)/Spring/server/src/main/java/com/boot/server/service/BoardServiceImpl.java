package com.boot.server.service;

import com.boot.server.dto.Board;
import com.boot.server.dto.BoardImg;
import com.boot.server.dto.UserLikeBoard;
import com.boot.server.dto.Users;
import com.boot.server.mapper.BoardMapper;
import com.boot.server.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Board> getBoard(Long page) { return boardMapper.getBoard(page); }

    @Override
    public List<Board> getSearch(Long page, String title, int userNo, String loc1, String loc2) throws Exception {
        List<Board> result = null;

        if (!title.equals("null") && userNo != 0 && !loc1.equals("null") && !loc2.equals("null")) {
            result = boardMapper.getBoardAllOption(page, title, userNo, loc1, loc2);
        } else if (!title.equals("null") && !loc1.equals("null") && !loc2.equals("null")) {
            result = boardMapper.getBoardTitleLoc(page, title, loc1, loc2);
        } else if (userNo != 0 && !loc1.equals("null") && !loc2.equals("null")) {
            result = boardMapper.getBoardNameLoc(page, userNo, loc1, loc2);
        } else if (!title.equals("null") && !loc1.equals("null")) {
            result = boardMapper.getBoardTitleLoc1(page, title, loc1);
        } else if (userNo != 0 && !loc1.equals("null")) {
            result = boardMapper.getBoardNameLoc1(page, userNo, loc1);
        } else if (!title.equals("null")) {
            result = boardMapper.getBoardTitle(page, title);
        } else if (userNo != 0) {
            result = boardMapper.getBoardName(page, userNo);
        } else if (!loc1.equals("null") && !loc2.equals("null")) {
            result = boardMapper.getBoardLoc(page, loc1, loc2);
        } else if (!loc1.equals("null")) {
            result = boardMapper.getBoardLoc1(page, loc1);
        }

        return result;
    }

    @Override
    public Board getBoardInfo(String boardNo) { return boardMapper.getBoardInfo(boardNo); }

    @Override
    public List<BoardImg> getBoardImages(String boardNo) { return boardMapper.getBoardImages(boardNo); }

    @Override
    public int like(String boardNo, String userNo) {
        int result = 0;

        result = boardMapper.like(boardNo);
        if (result == 1) {
            result = boardMapper.userLike(boardNo, userNo);
        }

        return result;
    }

    @Override
    public List<UserLikeBoard> likeBoard(String userId) { return boardMapper.likeBoard(userId); }

    @Override
    public int write(Board board) {
        return boardMapper.write(board);
    }

    @Override
    public int imgWrite(String boardNo, String originName, String saveName, String imgExt, long fileSize) { return boardMapper.imgWrite(boardNo, originName, saveName, imgExt, fileSize); }

    @Override
    public int logoWrite(String boardNo, String originName, String saveName, String imgExt, long fileSize) { return boardMapper.logoWrite(boardNo, originName, saveName, imgExt, fileSize); }

    @Override
    public int modifyBoard(Board board) { return boardMapper.modifyBoard(board); }

    @Override
    public int deleteBoard(String boardNo) {
        int result = 0;

        result = boardMapper.deleteBoardImg(boardNo);
        result = boardMapper.deleteBoardLike(boardNo);
        result = boardMapper.deleteBoard(boardNo);

        return result;
    }

}
