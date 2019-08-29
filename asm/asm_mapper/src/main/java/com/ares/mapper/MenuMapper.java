package com.ares.mapper;

import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.Menu;

import java.util.List;

/**
 * @author Yao
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> loadMenuTree();
}
