package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import utils.TextLoader;

public class AcceParsing {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Map> list = new ArrayList<>();
		String load = TextLoader.load("resource/acce.html");
//		System.out.println(load);
		Document doc =  Jsoup.parse(load);
//		System.out.println(doc);
		Elements elms =doc.getElementsByTag("tr");
		for(Element el : elms) {
			if(!el.hasAttr("data-name"))
				continue;
			Map map = new LinkedHashMap<>();
			map.put("name"  ,el.getElementsByClass("name").get(0).text() );
			map.put("skill"  ,el.getElementsByClass("skill").get(0).children().get(0).text() );
			Element skill = el.getElementsByClass("skill").get(0);
//			System.out.println(el.getElementsByClass("skill").get(0));
//			System.out.print(el.getElementsByClass("skill").get(0).children().size() + "#");
			System.out.println(skill.childNodeSize()+"#"+skill.children().size()+"#"+skill);
			for(Element s : skill.children()) {
				System.out.println(s);
			}
			System.out.println();
			
			map.put("slot"  , Integer.valueOf(el.getElementsByClass("slot").get(0).text() ));
			map.put("rare"  , Integer.valueOf(el.getElementsByClass("rare").get(0).text()) );
			
			list.add(map);
		}
		String out = new Gson().toJson(list);
//		System.out.println(out);
		/*
		MongoClient mc = new MongoClient("13.125.143.220", 51249);
		MongoCollection collection = mc.getDatabase("saansoo").getCollection("accessory");
		int cnt=0;
		try {
			for(Map map : list) {
				org.bson.Document d = new org.bson.Document(map);
				collection.insertOne(d);
				cnt++;
			}
		}catch (Exception e) {
			e.printStackTrace();
			collection.drop();
		}
		System.out.println(list.size()==cnt);
		*/
	}
}
