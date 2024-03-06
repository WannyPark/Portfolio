package com.boot.server.dto;

import lombok.Data;

@Data
public class BoardImg {

    private int boardNo;
    private String boardOriginImageName;
    private String boardSaveImageName;
    private String boardSaveImageExt;
    private int boardSaveImageSize;

}
