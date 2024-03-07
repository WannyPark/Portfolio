package com.boot.server.service;

import com.boot.server.dto.Board;
import com.boot.server.mapper.HomeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public List<Board> allLocTop() { return homeMapper.allLocTop(); }

}
