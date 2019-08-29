package com.ares.lucene.index.impl;

import com.ares.basic.PageList;
import com.ares.domain.DestroyDoc;
import com.ares.domain.DictionaryDetail;
import com.ares.domain.Record;
import com.ares.domain.User;
import com.ares.lucene.index.IDestroyDocIndexHelper;
import com.ares.lucene.util.LuceneUtil;
import com.ares.query.DestroyDocQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DestroyDocIndexHelper implements IDestroyDocIndexHelper {
    @Override
    public void save(DestroyDoc destroyDoc) {
        try {
            Document document = destroyDoc2doc(destroyDoc);
            LuceneUtil.addIndex(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void remove(Long id) {
        try {
            // id字段中存在id就删除
            LuceneUtil.deleteIndex(new String[]{"id"},id+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(DestroyDoc destroyDoc) {
        try{
            Term term = new Term("id", destroyDoc.getId() + "");
            LuceneUtil.updateIndex(term,destroyDoc2doc(destroyDoc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public PageList<DestroyDoc> list(DestroyDocQuery query) {
        String keywords = query.getKeyWords();
        Query indexQuery = null;
        if (StringUtils.isEmpty(keywords)){
            //rNum中包含*
            indexQuery =  new WildcardQuery(new Term("id","*"));
        }else{
            indexQuery = LuceneUtil.createQuery(new String[]{"recNum","username","dicName"}, keywords);
        }
        int pageNum = query.getCurrentPage().intValue();
        int pageSize = query.getPageSize().intValue();
        try {
            IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
            //pageNum 1  pageSize 10   10(pageNum * pageSize)   1-10 (page-1)*pageSize,pageSize
            //pageNum 2  pageSize 10   20   10-20
            //pageNum 3  pageSize 10   30    20-30
            // 查询数据， 结束页面自前的数据都会查询到，但是只取本页的数据
            TopDocs topDocs = indexSearcher.search(indexQuery, pageNum * pageSize);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            //总条目数
            int totalHits = topDocs.totalHits;
            if (totalHits<1){
                return  new PageList<>();
            }else{
                int start = (pageNum-1)*pageSize;
                int end = (pageNum*pageSize)>totalHits?totalHits:(pageNum*pageSize);
                List<DestroyDoc> destroyDocs = new ArrayList<>();
                for(int i=start;i<end;i++){
                    ScoreDoc scoreDoc = scoreDocs[i];
                    Document document = indexSearcher.doc(scoreDoc.doc);
                    destroyDocs.add(document2DestroyDoc(document));
                }
                return new PageList<>(Long.parseLong(totalHits+""),destroyDocs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private DestroyDoc document2DestroyDoc(Document document) {
        DestroyDoc destroyDoc = new DestroyDoc();
        destroyDoc.setId(Long.parseLong(document.get("id")));
        destroyDoc.setDestroydate(new Date(document.get("destroydate")));
        destroyDoc.setRemark(document.get("remark"));
        if (!StringUtils.isEmpty(document.get("recId"))){
            Record record = new Record();
            record.setId(Long.parseLong(document.get("recId")));
            record.setNum(document.get("recNum"));
            destroyDoc.setDoc(record);
        }

        if (!StringUtils.isEmpty(document.get("uId"))){
            User user = new User();
            user.setId(Long.parseLong(document.get("uId")));
            user.setUsername(document.get("username"));
            destroyDoc.setUser(user);
        }

        if (!StringUtils.isEmpty(document.get("dicId"))){
            DictionaryDetail dictionaryDetail = new DictionaryDetail();                              // 部门经理 员工对象
            dictionaryDetail.setId(Long.parseLong(document.get("dicId")));
            dictionaryDetail.setName(document.get("dicName"));
            destroyDoc.setDictionarydetail(dictionaryDetail);
        }
        return destroyDoc;
    }
    private Document destroyDoc2doc(DestroyDoc destroyDoc) {
        Document document = new Document();
        document.add(new LongField("id",destroyDoc.getId(), Field.Store.YES));
        if (destroyDoc.getDoc()!=null){
            document.add(new LongField("recId",destroyDoc.getDoc().getId(), Field.Store.YES));
            document.add(new TextField("recNum",destroyDoc.getDoc().getNum(), Field.Store.YES));
        }
        if (destroyDoc.getUser()!=null){
            document.add(new LongField("uId",destroyDoc.getUser().getId(), Field.Store.YES));
            document.add(new TextField("username",destroyDoc.getUser().getUsername(), Field.Store.YES));
        }
        if (destroyDoc.getDictionarydetail()!=null){
            document.add(new LongField("dicId",destroyDoc.getDictionarydetail().getId(), Field.Store.YES));
            document.add(new StringField("dicName",destroyDoc.getDictionarydetail().getName(), Field.Store.YES));
        }
        if (destroyDoc.getDestroydate()!=null) {
            document.add(new StringField("destroydate", destroyDoc.getDestroydate().toString(), Field.Store.YES));
        }
        if (destroyDoc.getRemark()!=null) {
            document.add(new StringField("remark", destroyDoc.getRemark(), Field.Store.YES));
        }
        return document;
    }
}
