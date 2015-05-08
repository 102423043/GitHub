package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class ParseXML {

	private XStream xstream = null;
	
	public String ObjParseToXML(String name, Object obj ){
		
		xstream = new XStream(new StaxDriver());
//		xstream = new XStream();
		xstream.alias(name, obj.getClass());
		
		return xstream.toXML(obj);
	}
	
	public Object XMLParseToObj(String xml){
		xstream = new XStream(new StaxDriver());
		return xstream.fromXML(xml);
	}
	
}
