package com.boot.server.mapper;

import com.boot.server.dto.Board;
import com.boot.server.dto.BoardImg;
import com.boot.server.dto.UserLikeBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    public List<Board> getBoard(Long page);

    public List<Board> getBoardAllOption(Long page, String title, int userNo, String loc1, String loc2);

    public List<Board> getBoardTitleLoc(Long page, String title, String loc1, String loc2);

    public List<Board> getBoardNameLoc(Long page, int userNo, String loc1, String loc2);

    public List<Board> getBoardTitleLoc1(Long page, String title, String loc1);

    public List<Board> getBoardNameLoc1(Long page, int userNo, String loc1);

    public List<Board> getBoardTitle(Long page, String title);

    public List<Board> getBoardName(Long page, int userNo);

    public List<Board> getBoardLoc(Long page, String loc1, String loc2);

    public List<Board> getBoardLoc1(Long page, String loc1);

    public Board getBoardInfo(String boardNo);

    public List<BoardImg> getBoardImages(String boardNo);

    public int like(String boardNo);

    public int userLike(String boardNo, String userNo);

    public List<UserLikeBoard> likeBoard(String userId);

    public int write(Board board);

    public int imgWrite(String boardNo, String originName, String saveName, String imgExt, long fileSize);

    public int logoWrite(String boardNo, String originName, String saveName, String imgExt, long fileSize);

    public int modifyBoard(Board board);

    public int deleteBoard(String boardNo);

    public int deleteBoardImg(String boardNo);

    public int deleteBoardLike(String boardNo);

}
