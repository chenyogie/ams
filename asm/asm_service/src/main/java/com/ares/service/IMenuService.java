package com.ares.service;

import com.ares.basic.service.IBaseService;
import com.ares.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
    List<Menu> queryMenuTree();
}
