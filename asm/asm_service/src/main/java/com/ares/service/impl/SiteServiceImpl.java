package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.Site;
import com.ares.mapper.SiteMapper;
import com.ares.query.SiteQuery;
import com.ares.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl extends BaseServiceImpl<Site> implements ISiteService {
    @Autowired
    private SiteMapper siteMapper;


}
