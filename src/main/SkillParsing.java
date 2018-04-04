package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import utils.MongoWorker;
import utils.TextLoader;

public class SkillParsing {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Map> list = new ArrayList<>();
		String load = TextLoader.load("resource/skill.html");
//		System.out.println(load);
		Document doc =  Jsoup.parse(load);
//		System.out.println(doc);
		Elements elms =doc.getElementsByTag("tr");
		for(Element el : elms) {
			Map map = new LinkedHashMap<>();
			Elements subs = el.getElementsByTag("td");
//				System.out.println(subs.get(0));
//				System.out.println(subs.get(0).children().size());
//				System.out.println(subs.get(0).child(0).text());
				map.put("skill", subs.get(0).child(0).text());
//				System.out.println(subs.get(0).child(1).text());
//				System.out.println(subs.get(1));
				List<String> effect = new ArrayList<>();
				for(int idx =0; idx<subs.get(1).childNodeSize(); idx++) {
					String val = subs.get(1).childNode(idx).toString();
					if(val.startsWith("<"))
						continue;
					effect.add(val);
				}
//				System.out.println(effect);
				map.put("max", effect.size());
				map.put("effect", effect);
				list.add(map);
		}
//		String out = new Gson().toJson(list);
//		System.out.println(out);
		
		boolean rst = MongoWorker.inserts("skills", list);
		System.out.println("RST= "+ rst);
	}
}