package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.DictionaryDetail;
import com.ares.mapper.DictionaryDetailMapper;
import com.ares.service.IDictionaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryDetailServiceImpl extends BaseServiceImpl<DictionaryDetail> implements IDictionaryDetailService {
    @Autowired
    private DictionaryDetailMapper dictionaryDetailMapper;

    @Override
    public List<DictionaryDetail> loadByType(String dicType){
        return dictionaryDetailMapper.loadByType(dicType);
    }
}
