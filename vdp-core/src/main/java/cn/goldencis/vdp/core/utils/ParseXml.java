package cn.goldencis.vdp.core.utils;

import java.io.*;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
    public static Document parseXmlJDom2(InputStream inXml) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(inXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    @SuppressWarnings("rawtypes")
    public static String updateCandidateInfo(String xml, String id, String type, String value) {
        Document document = null;
        try {
            InputStream inXml = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            document = parseXmlJDom2(inXml);
            if (document == null) {
                return null;
            }
            Element root = document.getRootElement();
            /* root.addNamespace("xmlns", "http://www.omg.org/spec/BPMN/20100524/MODEL")
                     .addNamespace("activiti", "http://activiti.org/bpmn")
                     .addNamespace("bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI")
                     .addNamespace("omgdi", "http://www.omg.org/spec/DD/20100524/DI");*/
            Element process = root.element("process");
            for (Object object : process.elements()) {
                Element element = (Element) object;
                if (id.equals(element.attributeValue("id"))) {
                    Iterator it = element.attributeIterator();
                    while (it.hasNext()) {
                        Attribute attribute = (Attribute) it.next();
                        if ("activiti:candidateUsers"
                                .equals(attribute.getNamespacePrefix() + ":" + attribute.getName())
                                || "activiti:candidateGroups".equals(attribute.getNamespacePrefix() + ":"
                                + attribute.getName())) {
                            it.remove();
                        }

                    }
                    element.addAttribute(type, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document == null ? null : document.asXML();
    }

    /**
     * 这个方法时用来判断是否存在自动筛选功能
     * @param xml
     * @param id
     * @return
     */
    public static boolean getIsScreenCandidatesByDepartmentTaskListener(String xml, String id) {
        Document document = null;
        InputStream inXml = null;
        try {
            inXml = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            document = parseXmlJDom2(inXml);
            Element root = document.getRootElement();

            Element process = root.element("process");
            for (Object object : process.elements()) {
                Element element = (Element) object;
                if (id.equals(element.attributeValue("id"))) {
                    Element extensionElements = element.element("extensionElements");
                    if (extensionElements.elements() != null) {
                        for (Object tmp : extensionElements.elements()) {
                            Element listener = (Element) tmp;
                            if ("${screenCandidatesByDepartmentTaskListener}".equals(listener.attributeValue("delegateExpression").toString())) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {

    }

}