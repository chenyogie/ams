package com.ares.service;

import com.ares.basic.PageList;
import com.ares.basic.service.IBaseService;
import com.ares.domain.Borrow;
import com.ares.domain.vo.BorrowVo;
import com.ares.query.BorrowQuery;

/**
 * @program: asm
 * @Date: 2019/8/3 16:31
 * @Author: Chenyogie
 * @Description:
 */
public interface IBorrowService extends IBaseService<Borrow> {
    PageList<BorrowVo> queryPage(BorrowQuery query);
}
