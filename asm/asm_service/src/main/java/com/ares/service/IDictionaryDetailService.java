package com.ares.service;

import com.ares.basic.service.IBaseService;
import com.ares.domain.DictionaryDetail;

import java.util.List;

public interface IDictionaryDetailService extends IBaseService<DictionaryDetail> {

    /**
     * 根据字典类型查询对应的字典数据
     * @param dicType
     * @return
     */
    List<DictionaryDetail> loadByType(String dicType);
}
