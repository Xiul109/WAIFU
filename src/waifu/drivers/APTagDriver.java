package waifu.drivers;

import java.util.List;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import waifu.drivers.TagDriver;

public class APTagDriver implements TagDriver{
	private UserAgent ua;
	private List<Element> tagGroup;
	int i=1;

	public APTagDriver(String url) throws ResponseException, NotFound {
		ua=new UserAgent();
		ua.visit(url);
		tagGroup= ua.doc.findFirst("<div id=\"multipletags\">").getChildElements().get(0).getChildElements();
	}
	
	public String next(){
		if(i>=tagGroup.size()) return null;
		Element e=tagGroup.get(i);
		i++;
		return e.getChildElements().get(1).innerText();
	}
	public boolean hasNext(){
		return i<tagGroup.size();
	}
}
