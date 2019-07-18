package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.mapper.mysql.CommonMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonService {

    private final CommonMapper commonMapper;

    public long getServerTime() {
        return commonMapper.selectServerTime();
    }
}
