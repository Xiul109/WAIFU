package waifu.drivers;

import java.util.List;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import waifu.drivers.TagDriver;

public class HummingTagDriver implements TagDriver{
	private UserAgent ua;
	private List<Element> tagGroup;
	int i=1;

	public HummingTagDriver(String url) throws ResponseException, NotFound {
		ua=new UserAgent();
		ua.visit(url);
		Element el= ua.doc.findFirst("<div class=\"large-12 columns filter-section no-padding\">").getChildElements().get(0);;
		tagGroup=el.getChildElements();
	}
	
	public String next(){
		if(i>=tagGroup.size()) return null;
		Element e=tagGroup.get(i);
		i++;
		return e.getChildElements().get(0).getChildElements().get(0).getAtString("value");
	}
	public boolean hasNext(){
		if(i>=tagGroup.size()) return false;
		return true;
	}
}
