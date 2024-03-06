package com.boot.server.service;

import com.boot.server.dto.Board;
import com.boot.server.dto.BoardImg;
import com.boot.server.dto.UserLikeBoard;

import java.util.List;

public interface BoardService {

    public List<Board> getBoard(Long page);

    public List<Board> getSearch(Long page, String title, int userNo, String loc1, String loc2) throws Exception;

    public Board getBoardInfo(String boardNo);

    public List<BoardImg> getBoardImages(String boardNo);

    public int like(String boardNo, String userNo);

    public List<UserLikeBoard> likeBoard(String userId);

    public int write(Board board);

    public int imgWrite(String boardNo, String originName, String saveName, String imgExt, long fileSize);

    public int logoWrite(String boardNo, String originName, String saveName, String imgExt, long fileSize);

    public int modifyBoard(Board board);

    public int deleteBoard(String boardNo);

}
