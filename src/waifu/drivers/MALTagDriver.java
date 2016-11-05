package waifu.drivers;

import java.util.List;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import waifu.drivers.TagDriver;

public class MALTagDriver implements TagDriver{
	private UserAgent ua;
	private List<Element> tagGroup;
	private int i=0;
	private int j=0;

	public MALTagDriver(String url) throws ResponseException, NotFound {
		ua=new UserAgent();
		ua.visit(url);
		tagGroup=ua.doc.findFirst("<div class=\"genre-link\">").getChildElements();
	}
	
	public String next(){
		if(i>=tagGroup.size()) return null;
		List<Element> subTag = tagGroup.get(i).getChildElements();
		Element el = subTag.get(j).getChildElements().get(0);
		j++;
		if(j>=subTag.size()){
			j=0;
			i++;
		}
		return el.getText().split(" \\(")[0];
	}
	public boolean hasNext(){
		return !(i>=tagGroup.size());
	}
}
