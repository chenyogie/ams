package com.ares.mapper;

import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.DictionaryDetail;

import java.util.List;

public interface DictionaryDetailMapper extends BaseMapper<DictionaryDetail> {
    List<DictionaryDetail> loadByType(String dicType);
}
