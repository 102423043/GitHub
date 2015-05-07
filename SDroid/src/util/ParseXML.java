package util;

import com.thoughtworks.xstream.XStream;

public class ParseXML {

	private XStream xstream = null;
	
	public String ObjParseToXML(String name, Object obj ){
		
//		xstream = new XStream(new StaxDriver());
		xstream = new XStream();
		xstream.alias(name, obj.getClass());
		
		return xstream.toXML(obj);
	}
	
}
