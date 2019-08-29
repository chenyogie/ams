package com.ares.lucene.index;

import com.ares.basic.PageList;
import com.ares.basic.query.BaseQuery;
import com.ares.domain.ProblematicDoc;

import java.io.Serializable;

public interface IProblematicDocIndexHelper{
    void save(ProblematicDoc problematicDoc);
    void remove(Serializable id);
    void update(ProblematicDoc problematicDoc);
    PageList<ProblematicDoc> list(BaseQuery query);
}
