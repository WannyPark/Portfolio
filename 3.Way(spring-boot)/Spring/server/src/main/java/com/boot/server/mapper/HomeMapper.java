package com.boot.server.mapper;

import com.boot.server.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    public List<Board> allLocTop();

}
