package com.boot.server.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board {

    private int boardNo;
    private int userNo;
    private String boardTitle;
    private String boardDesc;
    private String boardLoc1;
    private String boardLoc2;
    private int boardLike;
    private String boardOriginImageName;
    private String boardSaveImageName;
    private String boardSaveImageExt;
    private int boardSaveImageSize;
    private Timestamp boardRegDate;
    private Timestamp boardUpdDate;

}
