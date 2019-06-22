package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.model.Condition;
import org.godseop.apple.repository.mapper.DummyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DummyService {

    private final DummyMapper dummyMapper;

    public int getDummyListCount(Condition condition) {
        return dummyMapper.selectDummyListCount(condition);
    }

    public List<Dummy> getDummyList(Condition condition) {
        return dummyMapper.selectDummyList(condition);
    }

    public Dummy getDummy(Long id) {
        return dummyMapper.selectDummy(id);
    }
}
