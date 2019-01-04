package com.yyy.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.BooleanClause.Occur;

public class SearchMethod extends IndexUtils{

	Map<List<Document>,Integer> docMap = null;
	/**
	 * 通过lucene最小单元term进行查询
	 * @return
	 * @throws IOException 
	 */
	public Map<List<Document>,Integer> searchByTermQuery(int pageIndex,int pageSize,Term term) throws IOException{
		
		searcher = getIndexSearcher();
		Query query = new TermQuery(term);
		docMap = searchUtil(pageIndex,pageSize,query,searcher);
		return docMap;
	} 
	
	
	/**
	 * 前缀查询
	 * @param term
	 * @return
	 * @throws IOException
	 */
	public Map<List<Document>,Integer> searchByPrefixQuery(int pageIndex,int pageSize,Term term) throws IOException{
		
		searcher = getIndexSearcher();
		Query query = new PrefixQuery(term);
		docMap = searchUtil(pageIndex,pageSize,query,searcher);
		return docMap;
	} 

	/**
	 * 模糊查询
	 * @param term
	 * @return
	 * @throws IOException
	 */
	public  Map<List<Document>,Integer> searchByFuzzyQuery(int pageIndex,int pageSize,Term term) throws IOException{
		
		searcher = getIndexSearcher();
		Query query = new FuzzyQuery(term,2);
		
		docMap = searchUtil(pageIndex,pageSize,query,searcher);
		
		return docMap;
	}
	/**
	 * 通配符查询
	 * 通配符：*和？
	 * *表示任意字符
	 * ？表示一个字符
	 * @param term
	 * @return
	 * @throws IOException
	 */
	public Map<List<Document>,Integer> searchByWildcardQuery(int pageIndex,int pageSize,Term term) throws IOException{
		searcher = getIndexSearcher();
		Query query = new WildcardQuery(term);
		
		docMap = searchUtil(pageIndex,pageSize,query,searcher);
		
		return docMap;
	}
	/**
     * 调用分词器解析输入内容，将每个分词加入到List，然后返回此List
     * @param content
     * @return
     * @throws Exception
     */
   /* public  List<String> getAnalyzedStr( String content) throws Exception {
    	
    	
    	TokenStream stream = analyzer.tokenStream(null, new StringReader(content));
        CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
        
        List<String> result = new ArrayList<String>();
        stream.reset();
        while(stream.incrementToken()) {
            result.add(term.toString());
            System.out.println(term.toString());
        }
        
        return result;
    }
    public TokenStream getTokenStream( String content) throws IOException{
    	TokenStream stream = analyzer.tokenStream(null, new StringReader(content));
    	return stream;
    }*/
    /**多条件查询BooleanQuery
     * 
     * @param Field 域
     * @param content 内容 
     * @param str1 域  str2 查询内容 str3 多条件查询的条件
     * @return
     * @throws IOException
     * @throws ParseException 
     * 测试结果：当某个条件中部分达标即通过分词有一部分在索引库中能找到则这项满足条件若没有一个达标则不满足该条件
     */
    public Map<Map<List<Document>,Integer>,Query> searcherByBooleanQuery(int pageIndex,int pageSize,String[] str1,String[] str2,String[] str3) throws IOException, ParseException{
    	
    	Map<Map<List<Document>,Integer>,Query> map = new HashMap<Map<List<Document>,Integer>,Query>();
    	//不能使用多态形式因为Query中没有add方法
    	BooleanQuery bQuery = new BooleanQuery();
    	Query query = null;
    	for(int i = 0;i<str2.length;i++){
    		QueryParser qp = new QueryParser(str1[i], analyzer);
    		query = qp.parse(str2[i]);
    		switch(str3[i]){
    		case "MUST":	
    			bQuery.add(query,Occur.MUST);
    			break;
    		case "NOT":
    			bQuery.add(query,Occur.MUST_NOT);
    			break;
    		case "SHOULD":
    			bQuery.add(query,Occur.SHOULD);
    			break;
    		}
    		
    	}
    	searcher = getIndexSearcher();
    	map.put(searchUtil(pageIndex,pageSize,bQuery,  searcher),query) ;
    	return map;
    }
    /**
     * 智能提示
     * @param pageIndex
     * @param pageSize
     * @param field
     * @param content
     * @return
     * @throws ParseException 
     * @throws IOException 
     */
    public List<String> suggestion(String field,String content) throws ParseException, IOException{
    	
    	List<String> strList = new ArrayList<String>();
    	QueryParser qp = new QueryParser(field, analyzer);
		Query query =  qp.parse(content);
		searcher = getIndexSearcher();
		TopDocs topDoc = searcher.search(query, 3);
		ScoreDoc[] sd = topDoc.scoreDocs;
		for (ScoreDoc score : sd) {
			int documentId = score.doc;
			Document doc = searcher.doc(documentId);
			String str = doc.get(field);
			strList.add(str);
		}
    	return strList;
    }
    
    /**
     * QueryParser 会对输入的语句进行分词然后查询
     * @return 
     * @throws ParseException
     * @throws IOException
     */
	public Map<Map<List<Document>,Integer>,Query> searchByQueryParser(int pageIndex,int pageSize,String field,String content) throws ParseException, IOException{
		Map<Map<List<Document>,Integer>,Query> map = new HashMap<Map<List<Document>,Integer>,Query>();
		QueryParser qp = new QueryParser(field, analyzer);
		Query query =  qp.parse(content);
		searcher = getIndexSearcher();
		map.put(searchUtil(pageIndex,pageSize,query,searcher), query);
		/*for(Document doc:searchUtil(query,searcher)){
			try {
				System.out.println(displayHtmlHighlight(query, analyzer, field, doc.get("all"), 200));
			} catch (InvalidTokenOffsetsException e) {
				e.printStackTrace();
			}
		}*/
		return map;
	}
	/**
	 * 多域、多条件查询
	 * @param fields    域数组
	 * @param queries	查询数组
	 * @param flags		域之间的关系
	 * @return
	 * @throws ParseException
	 * @throws IOException 
	 */
	public Map<List<Document>,Integer> searcherByMultiFieldQueryParser(int pageIndex, int pageSize,String[] fields,String[] queries,Occur[] flags) throws ParseException, IOException{
		
		Query mfQuery = MultiFieldQueryParser.parse(queries, fields, flags, analyzer);
		searcher = getIndexSearcher();
		docMap = searchUtil(pageIndex,pageSize,mfQuery,searcher);
		return docMap;
	}
	/**范围查询
	 * 对于中文没有太大意义故占时不用
	 * @return
	 */
//	public List<ChineseMedicine> searcherByRangeQuery(){
//		//Query query = new TermRangeQuery(field, lowerTerm, upperTerm, includeLower, includeUpper);
//		return sList;
//	}
	
	
	
	
}
