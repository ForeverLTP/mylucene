package com.yyy.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yyy.model.ChineseMedicine;
import com.yyy.model.Disease;
import com.yyy.model.Prescription;
import com.yyy.model.Symptom;
import com.yyy.service.SQLService;

import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;

public class IndexUtils {

	static List<Disease> disList = null;
	static List<Symptom> syList = null;
	static List<ChineseMedicine> cmList = null;
	static List<Prescription> preList = null;
	List<Document> doList = null;

	Document document = null;
	IndexSearcher searcher = null;
	IndexWriter writer = null;
	IndexReader reader = null;
	IndexWriter ramWriter = null;

	public static Analyzer analyzer;
	static Directory fsd;
	static File path;

	// 静态资源加载，当类加载的时候运行(因为只要加载一次)
	static {

		try {

			// IKAnalyzer中文分词器（可扩展）
			// 标准分词器：Analyzer analyzer = new StandardAnalyzer();
			// new IKAnalyzer(true)表示智能分词
			// new IKAnalyzer(false)表示最细粒度分词(默认也是这个)
			analyzer = new IKAnalyzer();
			path = new File("chinese_medical/index");// 磁盘索引库路径(相对路径)
			fsd = FSDirectory.open(path);// 创建磁盘目录

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 采集数据
	 * 
	 * @throws IOException
	 */
	public void creatIndex(SQLService sqlService) throws IOException {

		// 分词
		doList = analyze(sqlService);
		// lucene没有提供相应的更新方法，只能先删除然后在创建新的索引(耗时)
		// 由于IndexWriter对象只能实例化一次如果使用内存和磁盘想结合的方式则需要两个IndexWriter故行不通
		// 虽然创建的时候耗时但是这样使得文件只有6个 ，搜索时减少了一些io操作加快了搜索速度
		writer = deleteAllIndex();
		for (Document doc : doList) {
			writer.addDocument(doc);
		}
		writer.commit();
		writer.close();
	}

	/**
	 * 分词，工具方法
	 * 
	 * @throws IOException
	 */
	public List<Document> analyze(SQLService sqlService) throws IOException {

		disList = sqlService.initDisease();// 获取数据
		syList = sqlService.initSymptom();// 获取数据
		cmList = sqlService.initChineseMedicine();// 获取数据
		preList = sqlService.initPrescription();// 获取数据
		
		doList = new ArrayList<Document>();
		
		for (Disease d : disList) {
			document = new Document();
			// 不分词、索引、存储用StringField
			// 分词、索引、存储用TextField
			// 不分词、不索引、存储用StoredField
			// 分词、索引、不存储用TextField，设置store.NO

			// Field mid = new StringField("did",String.valueOf(d.getDid()),
			// Store.YES);
			Field did = new StoredField("did", String.valueOf(d.getDid()));
			Field dname = new TextField("dname", d.getDname(), Store.YES);
			Field dalias = new TextField("dalias", d.getDalias(), Store.YES);
			Field ddescription = new TextField("ddescription", d.getDdescription(), Store.YES);
			Field dsympton = new TextField("dsympton", d.getDsympton(), Store.YES);
			Field disAll = new TextField("disAll", d.toString(), Store.YES);// 将全部字段写入一个域中，目的是为了方便全文检索
			dname.setBoost(10f);
			// 添加到文档中
			document.add(did);
			document.add(dname);
			document.add(dalias);
			document.add(ddescription);
			document.add(dsympton);
			document.add(disAll);

			doList.add(document);
		}
		for (Symptom sy : syList) {
			document = new Document();
			Field sid = new StoredField("sid", String.valueOf(sy.getSid()));
			Field sname = new TextField("sname", sy.getSname(), Store.YES);
			Field salias = new TextField("salias", sy.getSalias(), Store.YES);
			Field sdescription = new TextField("sdescription", sy.getSdescription(), Store.YES);
			Field sdisease = new TextField("sdisease", sy.getSdisease(), Store.YES);
			Field syAll = new TextField("syAll", sy.toString(), Store.YES);// 将全部字段写入一个域中，目的是为了方便全文检索
			sname.setBoost(10f);
			document.add(sid);
			document.add(sname);
			document.add(salias);
			document.add(sdescription);
			document.add(sdisease);
			document.add(syAll);
			doList.add(document);
		}
		
		for(ChineseMedicine cm:cmList){
			document = new Document();
			Field mid = new StoredField("mid", String.valueOf(cm.getMid()));
			Field mname = new TextField("mname", cm.getMname(), Store.YES);
			Field mtype = new TextField("mtype", cm.getMtype(), Store.YES);
			//Field mpname = new TextField("mpname", cm.getMpinyin(), Store.YES);
			Field malias = new TextField("malias", cm.getMalias(), Store.YES);
			Field msexuality = new TextField("msexuality", cm.getMsexuality(), Store.YES);
			Field mfunction = new TextField("mfunction", cm.getMfunction(), Store.YES);
			//Field mban = new TextField("mban", cm.getMban(), Store.YES);
			Field cmAll = new TextField("cmAll", cm.toString(), Store.YES);// 将全部字段写入一个域中，目的是为了方便全文检索
			mname.setBoost(10f);
			
			document.add(mid);
			document.add(mname);
			document.add(mtype);
			//document.add(mpname);
			document.add(malias);
			document.add(msexuality);
			document.add(mfunction);
			//document.add(mban);
			document.add(cmAll);
			doList.add(document);
		}
		
		for(Prescription pre:preList){
			document = new Document();
			Field pid = new StoredField("pid", String.valueOf(pre.getPid()));
			Field pname = new TextField("pname", pre.getPname(), Store.YES);
			Field peffect = new TextField("peffect", pre.getPeffect(), Store.YES);
			Field pcomposition = new TextField("pcomposition", pre.getPcomposition(), Store.YES);
			Field psong = new TextField("psong", pre.getPsong(), Store.YES);
			Field psource = new TextField("psource", pre.getPsource(), Store.YES);
			Field preAll = new TextField("preAll", pre.toString(), Store.YES);// 将全部字段写入一个域中，目的是为了方便全文检索
			pname.setBoost(10f);
			
			document.add(pid);
			document.add(pname);
			document.add(pcomposition);
			document.add(psource);
			document.add(psong);
			document.add(peffect);
			document.add(preAll);
			doList.add(document);
		}
		return doList;
	}

	/**
	 * 删除索引库
	 * 
	 * @throws IOException
	 */
	public IndexWriter deleteAllIndex() throws IOException {

		writer = getWriter();

		writer.deleteAll();

		return writer;

	}

	/**
	 * 获取搜索器
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public IndexSearcher getIndexSearcher() throws IOException {

		if (null == searcher) {

			reader = DirectoryReader.open(fsd);
			searcher = new IndexSearcher(reader);
		}
		return searcher;
	}

	/**
	 * 获取磁盘写入
	 * 
	 * @return
	 * @throws IOException
	 */
	public IndexWriter getWriter() throws IOException {
		if (null == writer) {
			// 为什么使用这种new匿名方式创建该对象 IndexWriterConfig(Version.LUCENE_4_10_3,
			// analyzer)
			// 因为IndexWriterConfig对象只能使用一次、一次、一次
			writer = new IndexWriter(fsd, new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer));
		}
		return writer;
	}

	/**
	 * 工具方法
	 * 
	 * @param query
	 * @param num
	 * @return List<Document>
	 * @throws IOException
	 */
	public static Map<List<Document>,Integer> searchUtil(int pageIndex,int pageSize,Query query, IndexSearcher searcher) throws IOException {

		Map<List<Document>,Integer> map = new HashMap<List<Document>,Integer>();
		List<Document> docList = new ArrayList<Document>();
		int num = pageSize*(pageIndex-1);//获取上一页的最后数量 
		TopDocs td = searcher.search(query, 1);
		int count = td.totalHits;// 按照查询条件查出的的数据总数
		ScoreDoc sdoc = null;
		if(pageIndex != 1){
			sdoc = td.scoreDocs[num-1];
		}
		TopDocs topDoc = searcher.searchAfter(sdoc, query, pageSize);
		
		ScoreDoc[] sd = topDoc.scoreDocs;
		for (ScoreDoc score : sd) {

			int documentId = score.doc;
			Document doc = searcher.doc(documentId);
			docList.add(doc);
		}
		map.put(docList, count);
		return map;
	}

	/**
	 * 给查询的文字上色
	 * 
	 * @param query
	 *            查询方法
	 * @param analyzer
	 *            分词器
	 * @param fieldName
	 *            域名
	 * @param fieldContent
	 *            查询内容
	 * @param fragmentSize
	 *            文字结果截取的长度
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public static String displayHtmlHighlight(Query query, Analyzer analyzer, String fieldName, String fieldContent,
			int fragmentSize) throws IOException, InvalidTokenOffsetsException {
		// 创建一个高亮器
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<font color='red'>", "</font>"),
				new QueryScorer(query));
		Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
		highlighter.setTextFragmenter(fragmenter);

		return highlighter.getBestFragment(analyzer, fieldName, fieldContent);
	}

	/**
	 * 将document转换为对象
	 * 
	 * @param url
	 * @param docList
	 * @return
	 * @throws Exception
	 */
	public static List<Object> translate(String url, List<Document> docList, Query query, String field)
			throws Exception {
		Class<?> cl = Class.forName(url);

		List<Object> objList = new ArrayList<Object>();
		for (Document doc : docList) {
			Object object = cl.getConstructor().newInstance();
			List<IndexableField> fieldList = doc.getFields();
			for (IndexableField inf : fieldList) {
				if (!inf.name().endsWith("All")) {
					String text = displayHtmlHighlight(query, analyzer, field, doc.get(field), Integer.MAX_VALUE);
					cl.getDeclaredField(inf.name()).setAccessible(true);
					if (inf.name().endsWith(field)) {
						cl.getDeclaredField(inf.name()).set(object, text);
					} else {
						cl.getDeclaredField(inf.name()).set(object, doc.get(inf.name()));
					}

					objList.add(object);
				}
			}
		}

		return objList;
	}

	/**
	 * 将中文字符串转换成相应的域的英文名称
	 * 
	 * @param field
	 * @return
	 * @throws InvalidTokenOffsetsException
	 * @throws IOException
	 */
	public static List<Disease> translateDis(List<Document> docList, Query query, String field,int sign)
			throws IOException, InvalidTokenOffsetsException {

		disList = new ArrayList<Disease>();
		for (Document doc : docList) {
			Disease dis = new Disease();
			List<IndexableField> fieldList = doc.getFields();
			for (IndexableField inf : fieldList) {
				//if (doc.get(inf.name()).length() != 0) {
					switch (inf.name()) {
					case "did":
							dis.setDid(Integer.parseInt(doc.get(inf.name())));
							break;
					case "dname":
						if (field.equals(inf.name())||field.equals("disAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""||text == null){
								dis.setDname(doc.get(inf.name()));
							}else{
								dis.setDname(text);
							}
							
						} else {
							dis.setDname(doc.get(inf.name()));
						}

						break;
					case "dalias":
						if (field.equals(inf.name())||field.equals("disAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == "" || text == null){
								dis.setDalias("");
							}else{
								dis.setDalias(text);
							}
							
						} else {
							dis.setDalias(doc.get(inf.name()));
						}
						break;
					case "ddescription":
						if (field.equals(inf.name())||field.equals("disAll")) {
							String temp = doc.get(inf.name());
							temp = temp.replace("[详细]", "");
							String text = displayHtmlHighlight(query, analyzer, inf.name(), temp,
									Integer.MAX_VALUE);
							if(text == ""|| text==null){
								dis.setDdescription(temp);
							}else{
								dis.setDdescription(text);
							}
							
						} else {
							String temp = doc.get(inf.name());
							temp = temp.replace("[详细]", "");
							dis.setDdescription(temp);
						}
						break;
					case "dsympton":
						if (field.equals(inf.name())||field.equals("disAll")) {
							//String text = displayHtmlHighlight(query, analyzer, field, doc.get(field),
							//		Integer.MAX_VALUE);
							//dis.setDsympton(text);
							List<Symptom> syList = getSyData(doc.get(inf.name()),sign);
							for(Symptom sy:syList){
								String text = displayHtmlHighlight(query, analyzer, inf.name(),sy.getSname(),
										Integer.MAX_VALUE);
								
								if(text == "" || text == null){
									
								}else{
									sy.setSname(text);
								}
							}
							dis.setSymList(syList);;
						} else {
							dis.setSymList(getSyData(doc.get(inf.name()),sign));;
						}
						break;
					}
				//}
			}
			disList.add(dis);
		}

		return disList;

	}

	/**
	 * 将中文字符串转换成相应的域的英文名称
	 * 
	 * @param field
	 * @return
	 * @throws InvalidTokenOffsetsException
	 * @throws IOException
	 */
	public static List<Symptom> translateSy(List<Document> docList, Query query, String field)
			throws IOException, InvalidTokenOffsetsException {

		syList = new ArrayList<Symptom>();
		for (Document doc : docList) {
			Symptom sy = new Symptom();
			List<IndexableField> fieldList = doc.getFields();
			for (IndexableField inf : fieldList) {
				//if (doc.get(inf.name()).length() != 0) {
					switch (inf.name()) {
					case "sid":
							sy.setSid(Integer.parseInt(doc.get(inf.name())));
							break;
					case "sname":
						if (field.equals(inf.name())||field.equals("syAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""||text == null){
								sy.setSname(doc.get(inf.name()));
							}else{
								sy.setSname(text);
							}
							
						} else {
							sy.setSname(doc.get(inf.name()));
						}

						break;
					case "salias":
						if (field.equals(inf.name())||field.equals("syAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == "" || text == null){
								sy.setSalias("");
							}else{
								sy.setSalias(text);
							}
							
						} else {
							sy.setSalias(doc.get(inf.name()));
						}
						break;
					case "sdescription":
						if (field.equals(inf.name())||field.equals("syAll")) {
							String temp = doc.get(inf.name());
							temp = temp.replace("[详细]", "");
							String text = displayHtmlHighlight(query, analyzer, inf.name(), temp,
									Integer.MAX_VALUE);
							if(text == ""|| text==null){
								sy.setSdescription(temp);
							}else{
								sy.setSdescription(text);
							}
							
						} else {
							String temp = doc.get(inf.name());
							temp = temp.replace("[详细]", "");
							sy.setSdescription(temp);
						}
						break;
					case "sdisease":
						if (field.equals(inf.name())||field.equals("syAll")) {
							//String text = displayHtmlHighlight(query, analyzer, field, doc.get(field),
							//		Integer.MAX_VALUE);
							//dis.setDsympton(text);
							List<Disease> disList = getDisData(doc.get(inf.name()));
							for(Disease dis:disList){
								String text = displayHtmlHighlight(query, analyzer, inf.name(),dis.getDname(),
										Integer.MAX_VALUE);
								
								if(text == "" || text == null){
								
								}else{
									dis.setDname(text);
								}
							}
							sy.setDisList(disList);;
						} else {
							sy.setDisList(getDisData(doc.get(inf.name())));;
						}
						break;
					}
				//}
			}
			syList.add(sy);
		}

		return syList;

	}
	
	public static List<ChineseMedicine> translateCM(List<Document> docList, Query query, String field)
			throws IOException, InvalidTokenOffsetsException {

		cmList = new ArrayList<ChineseMedicine>();
		for (Document doc : docList) {
			ChineseMedicine cm = new ChineseMedicine();
			List<IndexableField> fieldList = doc.getFields();
			for (IndexableField inf : fieldList) {
				//if (doc.get(inf.name()).length() != 0) {
					switch (inf.name()) {
					case "mid":
							cm.setMid(Integer.parseInt(doc.get(inf.name())));
							break;
					case "mname":
						if (field.equals(inf.name())||field.equals("cmAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""||text == null){
								cm.setMname(doc.get(inf.name()));
							}else{
								cm.setMname(text);
							}
							
						} else {
							cm.setMname(doc.get(inf.name()));
						}

						break;
						
					case "mtype":
						if (field.equals(inf.name())||field.equals("cmAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""||text == null){
								cm.setMtype(doc.get(inf.name()));;
							}else{
								cm.setMtype(text);
							}
							
						} else {
							cm.setMtype(doc.get(inf.name()));
						}

						break;
					case "malias":
						if (field.equals(inf.name())||field.equals("cmAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == "" || text == null){
								cm.setMalias("");
							}else{
								cm.setMalias(text);
							}
							
						} else {
							cm.setMalias(doc.get(inf.name()));
						}
						break;
					case "mfunction":
						if (field.equals(inf.name())||field.equals("cmAll")) {
							
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""|| text==null){
								cm.setMfunction(doc.get(inf.name()));
							}else{
								cm.setMfunction(text);
							}
							
						} else {
							cm.setMfunction(doc.get(inf.name()));
						}
						break;
					case "msexuality":
						if (field.equals(inf.name())||field.equals("cmAll")) {
							
							String text = displayHtmlHighlight(query, analyzer, inf.name(),doc.get(inf.name()),
									Integer.MAX_VALUE);
							
							if(text == "" || text == null){
								cm.setMsexuality(doc.get(inf.name()));
							}else{
								cm.setMsexuality(text);
							}
							
						} else {
							cm.setMsexuality(doc.get(inf.name()));
						}
						cm.setMap(getCMData(cm.getMsexuality()));
						break;
					}
				//}
			}
			cmList.add(cm);
		}

		return cmList;

	}
	
	
	public static List<Prescription> translatePre(List<Document> docList, Query query, String field)
			throws IOException, InvalidTokenOffsetsException {

		preList = new ArrayList<Prescription>();
		for (Document doc : docList) {
			Prescription pre = new Prescription();
			List<IndexableField> fieldList = doc.getFields();
			for (IndexableField inf : fieldList) {
				//if (doc.get(inf.name()).length() != 0) {
					switch (inf.name()) {
					case "pid":
							pre.setPid(Integer.parseInt(doc.get(inf.name())));
							break;
					case "pname":
						if (field.equals(inf.name())||field.equals("preAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""||text == null){
								pre.setPname(doc.get(inf.name()));
							}else{
								pre.setPname(text);
							}
							
						} else {
							pre.setPname(doc.get(inf.name()));
						}

						break;
					case "psource":
						if (field.equals(inf.name())||field.equals("preAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == "" || text == null){
								pre.setPsource(doc.get(inf.name()));
							}else{
								pre.setPsource(text);
							}
							
						} else {
							pre.setPsource(doc.get(inf.name()));
						}
						break;
					case "pcomposition":
						if (field.equals(inf.name())||field.equals("preAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""|| text==null){
								pre.setPcomposition(doc.get(inf.name()));
							}else{
								pre.setPcomposition(text);
							}
							
						} else {
							pre.setPcomposition(doc.get(inf.name()));
						}
						break;
					case "peffect":
						if (field.equals(inf.name())||field.equals("preAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""|| text==null){
								pre.setPeffect(doc.get(inf.name()));
							}else{
								pre.setPeffect(text);
							}
							
						} else {
							pre.setPeffect(doc.get(inf.name()));
						}
						break;
					case "psong":
						if (field.equals(inf.name())||field.equals("preAll")) {
							String text = displayHtmlHighlight(query, analyzer, inf.name(), doc.get(inf.name()),
									Integer.MAX_VALUE);
							if(text == ""|| text==null){
								pre.setPsong(doc.get(inf.name()));
							}else{
								pre.setPsong(text);
							}
							
						} else {
							pre.setPsong(doc.get(inf.name()));
						}
						break;
					}
				//}
			}
			preList.add(pre);
		}

		return preList;

	}
	
	/**
	 * 翻译域名
	 * @param field
	 * @return
	 */
	public static String getDisField(String field) {

		switch (field) {
		case "全文检索":
			field = "disAll";
			break;
		case "疾病名称":
			field = "dname";
			break;
		case "疾病别名":
			field = "dalias";
			break;
		case "相关症状":
			field = "dsympton";
			break;
		}
		return field;
	}
	public static String getSyField(String field) {

		switch (field) {
		case "全文检索":
			field = "syAll";
			break;
		case "症状名称":
			field = "sname";
			break;
		case "症状别名":
			field = "salias";
			break;
		case "相关疾病":
			field = "sdisease";
			break;
		}
		return field;
	}
	public static String getCMField(String field) {

		switch (field) {
		case "全文检索":
			field = "cmAll";
			break;
		case "中药名称":
			field = "mname";
			break;
		case "功效作用":
			field = "mfunction";
			break;
		case "性味归经":
			field = "msexuality";
			break;
		}
		return field;
	}
	public static String getPreField(String field) {

		switch (field) {
		case "全文检索":
			field = "preAll";
			break;
		case "药方名称":
			field = "pname";
			break;
		case "功效作用":
			field = "peffect";
			break;
		case "组成成分":
			field = "pcomposition";
			break;
		case "歌诀":
			field = "psong";
			break;
		}
		return field;
	}
	/**
	 * 将相关症状切割成list集合
	 * @param content
	 * @return
	 */
	public static List<Symptom> getSyData(String content,int sign){
		
		String[] spl = content.split("相关症状");
		String symptom = spl[spl.length - 1];
		String[] symptoms = symptom.split("\\s+");
		List<Symptom> syList = new ArrayList<Symptom>();
		int flag = 0;
		for (String str : symptoms) {
			flag++;
			Symptom sy = new Symptom();
			sy.setSname(str);
			syList.add(sy);
			if(sign == 0){
				if (5 == flag) {
					break;
				}
			}
		}
		return syList;
		
	}
	/**
	 * 将相关疾病切割成list集合
	 * @param content
	 * @return
	 */
	public static List<Disease> getDisData(String content){
		
		String[] spl = content.split("相关疾病");
		String symptom = spl[spl.length - 1];
		String[] symptoms = symptom.split("\\|");
		List<Disease> disList = new ArrayList<Disease>();
		int flag = 0;
		for (String str : symptoms) {
			flag++;
			Disease dis = new Disease();
			dis.setDname(str);
			disList.add(dis);
			if (5 == flag) {
				break;
			}
		}
		return disList;
		
	}
	
	/**
	 * 将性味归经切割成map集合
	 * @param content
	 * @return
	 */
	public static Map<String,String> getCMData(String content){
		
		Map<String,String> map = new HashMap<String,String>();
		String[] str1 = content.trim().split("。");
		String[] str2 = str1[0].split("，");
		String[] str3 = str2[0].split("性");//此时只剩下属于性的内容
		String str6 = "";
		for(String str:str3){
			str6 += str;
		}
		String[] str5 = str2[1].split("味");//此时只剩下属于味的内容
		String str8 = "";
		for(String str:str5){
			str8 += str;
		}
		String[] str7 = str1[1].split("归");//此时只剩下属于归的内容
		String str9 = "";
		for(String str:str7){
			str9 += str;
		}
		map.put("性", str6.trim());
		map.put("味", str8.trim());
		map.put("归", str9.trim());
		return map;
		
	}
}
