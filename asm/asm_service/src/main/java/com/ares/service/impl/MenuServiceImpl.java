package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.Menu;
import com.ares.mapper.MenuMapper;
import com.ares.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yao
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> queryMenuTree() {
        return menuMapper.loadMenuTree();
    }
}
