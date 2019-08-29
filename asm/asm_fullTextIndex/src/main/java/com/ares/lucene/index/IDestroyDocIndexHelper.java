package com.ares.lucene.index;

import com.ares.basic.PageList;
import com.ares.domain.DestroyDoc;
import com.ares.query.DestroyDocQuery;

public interface IDestroyDocIndexHelper{
    void save(DestroyDoc destroyDoc);
    void remove(Long id);
    void update(DestroyDoc destroyDoc);
    PageList<DestroyDoc> list(DestroyDocQuery query);
}
