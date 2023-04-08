package com.seidelsoft.util;

import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtil {

    private static Marshaller getMarshaller(Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return m;
    }
    public static String toXml(Object obj) throws JAXBException {
        StringWriter sw = new StringWriter();
        getMarshaller(obj).marshal(obj, sw);

        return sw.toString();
    }

    public static void objToXmlFile(Object obj, String filePath) throws JAXBException {
        getMarshaller(obj).marshal(obj, new File(filePath));
    }

    public static Object fromXmlToObj(String xml, Object obj) {
        return JAXB.unmarshal(new StringReader(xml), obj.getClass());
    }
}
