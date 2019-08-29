package com.ares.lucene.index.impl;

import com.ares.basic.PageList;
import com.ares.basic.query.BaseQuery;
import com.ares.domain.DictionaryDetail;
import com.ares.domain.ProblematicDoc;
import com.ares.domain.Record;
import com.ares.domain.User;
import com.ares.lucene.index.IProblematicDocIndexHelper;
import com.ares.lucene.util.LuceneUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProblematicDocIndexHelper implements IProblematicDocIndexHelper {
    @Override
    public void save(ProblematicDoc problematicDoc) {
        try {
            Document document = problematicDoc2doc(problematicDoc);
            LuceneUtil.addIndex(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Serializable id) {
        try {
            // id字段中存在id就删除
            LuceneUtil.deleteIndex(new String[]{"id"},id+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProblematicDoc problematicDoc) {
        try{
            Term term = new Term("id", problematicDoc.getId() + "");
            LuceneUtil.updateIndex(term,problematicDoc2doc(problematicDoc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageList<ProblematicDoc> list(BaseQuery query) {
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
                List<ProblematicDoc> problematicDocs = new ArrayList<>();
                for(int i=start;i<end;i++){
                    ScoreDoc scoreDoc = scoreDocs[i];
                    Document document = indexSearcher.doc(scoreDoc.doc);
                    problematicDocs.add(document2ProblematicDoc(document));
                }
                return new PageList<>(Long.parseLong(totalHits+""),problematicDocs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ProblematicDoc document2ProblematicDoc(Document document) {
        ProblematicDoc problematicDoc = new ProblematicDoc();
        problematicDoc.setId(Long.parseLong(document.get("id")));
        problematicDoc.setAccidentdate(new Date(document.get("accidentdate")));
        problematicDoc.setRemark(document.get("remark"));
        if (!StringUtils.isEmpty(document.get("rcId"))){
            Record record = new Record();
            record.setId(Long.parseLong(document.get("rcId")));
            record.setNum(document.get("rcNum"));
            problematicDoc.setDoc(record);
        }

        if (!StringUtils.isEmpty(document.get("usId"))){
            User user = new User();
            user.setId(Long.parseLong(document.get("usId")));
            user.setUsername(document.get("usName"));
            problematicDoc.setUser(user);
        }

        if (!StringUtils.isEmpty(document.get("dcId"))){
            DictionaryDetail dictionaryDetail = new DictionaryDetail();                              // 部门经理 员工对象
            dictionaryDetail.setId(Long.parseLong(document.get("dcId")));
            dictionaryDetail.setName(document.get("dcName"));
            problematicDoc.setDictionarydetail(dictionaryDetail);
        }
        return problematicDoc;
    }

    private Document problematicDoc2doc(ProblematicDoc problematicDoc) {
        Document document = new Document();
        document.add(new LongField("id",problematicDoc.getId(), Field.Store.YES));
        if (problematicDoc.getDoc()!=null){
            document.add(new LongField("rcId",problematicDoc.getDoc().getId(), Field.Store.YES));
            document.add(new TextField("rcNum",problematicDoc.getDoc().getNum(), Field.Store.YES));
        }
        if (problematicDoc.getUser()!=null){
            document.add(new LongField("usId",problematicDoc.getUser().getId(), Field.Store.YES));
            document.add(new TextField("usName",problematicDoc.getUser().getUsername(), Field.Store.YES));
        }
        if (problematicDoc.getDictionarydetail()!=null){
            document.add(new LongField("dcId",problematicDoc.getDictionarydetail().getId(), Field.Store.YES));
            document.add(new StringField("dcName",problematicDoc.getDictionarydetail().getName(), Field.Store.YES));
        }
        if (problematicDoc.getAccidentdate()!=null) {
            document.add(new StringField("accidentdate", problematicDoc.getAccidentdate().toString(), Field.Store.YES));
        }
        if (problematicDoc.getRemark()!=null) {
            document.add(new StringField("remark", problematicDoc.getRemark(), Field.Store.YES));
        }
        return document;
    }
}
