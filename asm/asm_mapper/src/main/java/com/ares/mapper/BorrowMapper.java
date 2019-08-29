package com.ares.mapper;

import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.Borrow;
import com.ares.domain.vo.BorrowVo;
import com.ares.query.BorrowQuery;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/3 15:14
 * @Author: Chenyogie
 * @Description:
 */
public interface BorrowMapper extends BaseMapper<Borrow> {
    Long queryCount(BorrowQuery query);
    List<BorrowVo> query(BorrowQuery query);
}
