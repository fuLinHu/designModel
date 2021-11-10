package javautil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/7/19 18:55
 * @Param $
 * @return $
 * @Version V1.0
 */
public class JavaParseDom {

    public static void main(String[] args) throws ReflectiveOperationException, ParserConfigurationException, SAXException, IOException {

        long l = System.currentTimeMillis();
        File file = new File("F:\\新2003.html");
        org.jsoup.nodes.Document parse = Jsoup.parse(file, "utf-8");
        Elements href = parse.getElementsByAttributeValueMatching("href", "^#_Toc");
        int i =0;
        List<org.jsoup.nodes.Element> titleElement = new ArrayList<>();
        Map<String,org.jsoup.nodes.Element> titleMap = new LinkedHashMap<>();
        for (org.jsoup.nodes.Element element : href) {
            String href2 = element.attr("href");
            String substring = href2.substring(1);
            Elements name = parse.getElementsByAttributeValue("name", substring);
            if(name==null||name.size()<=0){
                continue;
            }
            org.jsoup.nodes.Element element1 = name.get(0);
            boolean flage = true;
            org.jsoup.nodes.Element element2 = null;
            while (flage){
                org.jsoup.nodes.Element parent = element1.parent();
                Tag tag = parent.tag();
                String name1 = tag.getName();
                if("p".contains(name1)){
                    element2 = parent;
                    String text = element2.text();
                    if(text==null||"".equals(text)){
                        continue;
                    }
                    //System.out.println(text +"============="+i++);
                    flage = false;
                    titleElement.add(element2);
                    titleMap.put(text,element2);
                }
            }

        }
        org.jsoup.nodes.Element element = titleElement.get(0);
        //获取所有兄弟标签
        Elements elements = element.siblingElements();
        List<org.jsoup.nodes.Element> elements1 = elements.subList(0, elements.size());
        Map<String,StringBuffer> content =  new LinkedHashMap<>();
        StringBuffer result = null;
        for (org.jsoup.nodes.Element element1 : elements1) {
            String text = element1.text();
            if(titleMap.containsKey(text)){
                result = new StringBuffer();
                content.put(text,result);
            }else{
                if(result==null){
                    System.out.println("stop");
                }
                result.append(element1.toString());
            }
        }
        for (Map.Entry<String, StringBuffer> stringStringBufferEntry : content.entrySet()) {
            String key = stringStringBufferEntry.getKey();
            StringBuffer value = stringStringBufferEntry.getValue();
            System.out.println(key);
            System.out.println(value.toString());
        }

        long e = System.currentTimeMillis();
        System.out.println(e-l);

    }




    private Document document;
    private Element element;

    /**
     * 以标准XML字符（"UTF-8"编码）形式解析
     * @param xml {@link String}："UTF-8"编码的标准XML字符串；
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws ReflectiveOperationException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public JavaParseDom(String xml) throws ParserConfigurationException, SAXException, IOException, SecurityException, IllegalArgumentException, ReflectiveOperationException {
        this.constructor(new ByteArrayInputStream(xml.getBytes(Charset(xml))));
    }
    /**
     * 以File形式，读取一个XML文件。
     * @param file {@link File}：文件；
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws ReflectiveOperationException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public JavaParseDom(File file) throws ParserConfigurationException, SAXException, IOException, SecurityException, IllegalArgumentException, ReflectiveOperationException {
        this.constructor(file);
    }
    /**
     * 以xml文件{@link InputStream}输入流的方式，读取一个XML文件。
     * <p>此方式可应用为：在jar包内，无法得到实际路径时，获取xml文件。</p>
     * @param in {@link InputStream}：xml文件输入流；
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws ReflectiveOperationException
     */
    public JavaParseDom(InputStream in) throws ParserConfigurationException, SAXException, IOException, SecurityException, IllegalArgumentException, ReflectiveOperationException {
        this.constructor(in);
    }
    public JavaParseDom(Document document) {
        this.set(document);
    }

    /**
     * 设置文档上下文，并初始化{@link Element}为根节点。
     * @param document {@link Document}：文档上下文；
     * @return {@link JavaParseDom}：this。
     * @author Haining.Liu
     * @date 2018年6月21日 上午10:49:34
     */
    public JavaParseDom set(Document document) {
        this.document = document;
        this.element = document.getDocumentElement();
        return this;
    }

    public Document getDocument() {
        return document;
    }
    public Element getElement() {
        return element;
    }

    /**
     * 获取XML编码
     * @param xml {@link String}：XML文本；
     * @return {@link String}：编码格式。
     * @author Haining.Liu
     * @date 2018年7月12日 下午10:08:24
     */
    public static String Charset(String xml) {
        xml = xml.trim();
        if (xml.startsWith("<?xml")) {
            try {
                int ei = xml.indexOf("?>");
                String title = xml.substring(0, ei);
                int eci = title.indexOf("encoding", 5);
                if (eci != -1) {
                    String ec = xml.substring(eci, ei);
                    int eci1 = ec.indexOf("\"");
                    int eci2 = ec.indexOf("\"", ++eci1);
                    String charset = ec.substring(eci1, eci2);
                    if (charset != null && charset.trim().length() != 0)
                        return charset;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return java.nio.charset.Charset.defaultCharset().name();	//JVM默认编码
    }
    /**
     * 是否存在此属性
     * @param node {@link Node}：Element节点；
     * @param name {@link String}：属性名称；
     * @return {@link Boolean}：true - 存在。
     * @author Haining.Liu
     * @date 2018年5月30日 上午11:21:16
     */
    public static boolean ExistAttrs(Node node, String name) {
        if (name == null || name.trim().length() == 0)
            return false;
        NamedNodeMap nnm = node.getAttributes();
        for (int i = 0, len = nnm.getLength(); i < len; i++) {
            Node attr = nnm.item(i);
            if (attr.getNodeName().equals(name))
                return true;
        }
        return false;
    }
    /**
     * 获取所有属性名
     * @param node {@link Node}：Element节点；
     * @return {@link String}[]：属性名组。
     * @author Haining.Liu
     * @date 2018年3月16日 下午9:27:00
     */
    public static String[] GetAttrs(Node node) {
        NamedNodeMap nnm = node.getAttributes();
        int len = nnm.getLength();
        String[] rs = new String[len];
        for (int i = 0; i < len; i++) {
            Node attr = nnm.item(i);
            rs[i] = attr.getNodeName();
        }
        return rs;
    }
    /**
     * 获取属性Map
     * @param node {@link Node}：Element节点；
     * @return {@link Map}<{@link String}, {@link String}>：属性的key、value值。
     * @author Haining.Liu
     * @date 2018年3月16日 下午9:24:56
     */
    public static Map<String, String> GetAttrMap(Node node) {
        NamedNodeMap nnm = node.getAttributes();
        int len = nnm.getLength();
        Map<String, String> rs = new LinkedHashMap<String, String>(len);
        for (int i = 0; i < len; i++) {
            Node attr = nnm.item(i);
            rs.put(attr.getNodeName(), attr.getTextContent());
        }
        return rs;
    }

    /**
     * 按条件查找根节点，下级及以下，第一个符合条件的{@link Element}节点。
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 下午3:40:43
     * @see Assist#findOne(NodeList)
     */
    public Element findOne(String selector) {
        return FindOne(this.element, selector);
    }
    /**
     * 按条件，查找下级及以下，第一个符合条件的{@link Element}节点。
     * @param element {@link Element}：当前要查找的Element节点；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 下午3:42:26
     * @see Assist#findOne(NodeList)
     */
    public static Element FindOne(Element element, String selector) {
        return FindOne(element.getChildNodes(), selector);
    }
    /**
     * 按条件，查找下级及以下，第一个符合条件的{@link Element}节点。
     * @param nodes {@link NodeList}：节点列表；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 下午2:50:57
     * @see Assist#findOne(NodeList)
     */
    public static Element FindOne(NodeList nodes, String selector) {
        Assist la = FindOneIf(selector);
        return la.findOne(nodes);
    }
    /**
     * 按条件<b>逐级、逐次、纵向</b>查找根节点，下级及以下，第一个符合条件的{@link Element}节点。
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午2:24:11
     * @see Assist#findOneSib(NodeList)
     */
    public Element findOneSib(String selector) {
        return FindOneSib(this.element, selector);
    }
    /**
     * 按条件，<b>逐级、逐次、纵向</b>查找下级及以下，第一个符合条件的{@link Element}节点。
     * @param element {@link Element}：当前要查找的Element节点；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午2:23:11
     * @see Assist#findOneSib(NodeList)
     */
    public static Element FindOneSib(Element element, String selector) {
        return FindOneSib(element.getChildNodes(), selector);
    }
    /**
     * 按条件，<b>逐级、逐次、纵向</b>查找下级及以下，第一个符合条件的{@link Element}节点。
     * @param nodes {@link NodeList}：节点列表；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午2:22:11
     * @see Assist#findOneSib(NodeList)
     */
    public static Element FindOneSib(NodeList nodes, String selector) {
        Assist la = FindOneIf(selector);
        return la.findOneSib(nodes);
    }
    /**
     * 按条件<b>纵向、逐级</b>查找根节点，下级及以下，第一个符合条件的{@link Element}节点。
     * <p>适合查找有层级规范的{@link Element}节点。</p>
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午4:30:41
     * @see Assist#findOneSibLeap(NodeList)
     */
    public Element findOneSibLeap(String selector) {
        return FindOneSibLeap(this.element, selector);
    }
    /**
     * 按条件，<b>纵向、逐级</b>查找下级及以下，第一个符合条件的{@link Element}节点。
     * <p>适合查找有层级规范的{@link Element}节点。</p>
     * @param element {@link Element}：当前要查找的Element节点；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午4:30:28
     * @see Assist#findOneSibLeap(NodeList)
     */
    public static Element FindOneSibLeap(Element element, String selector) {
        return FindOneSibLeap(element.getChildNodes(), selector);
    }
    /**
     * 按条件，<b>纵向、逐级</b>查找下级及以下，第一个符合条件的{@link Element}节点。
     * <p>适合查找有层级规范的{@link Element}节点。</p>
     * @param nodes {@link NodeList}：节点列表；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午4:27:58
     * @see Assist#findOneSibLeap(NodeList)
     */
    public static Element FindOneSibLeap(NodeList nodes, String selector) {
        Assist la = FindOneIf(selector);
        return la.findOneSibLeap(nodes);
    }

    /**
     * 查找根{@link Element}节点下，所有的{@link Element}子孙节点
     * @return {@link List}<{@link Element}>：Element节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午11:14:04
     * @Description:
     */
    public List<Element> find() {
        return Find(this.element);
    }
    /**
     * 查找根{@link Element}节点下，所有的{@link Element}子孙节点
     * @param selector {@link String}：Element节点条件；
     * @return {@link List}<{@link Element}>：Element节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午11:14:07
     * @Description:
     */
    public List<Element> find(String selector) {
        return Find(this.element, selector);
    }
    /**
     * 查找下级所有的{@link Element}子孙节点
     * @param element {@link Element}：当前要查找的Element节点；
     * @return {@link List}<{@link Element}>：Element节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午11:09:52
     * @Description:
     */
    public static List<Element> Find(Element element) {
        return Find(element, null);
    }
    /**
     * 查找下级所有的{@link Element}子孙节点
     * @param nodes {@link NodeList}：节点列表；
     * @return {@link List}<{@link Element}>：Element节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午11:08:54
     * @Description:
     */
    public static List<Element> Find(NodeList nodes) {
        return Find(nodes, null);
    }
    /**
     *
     * 按条件，查找下级所有符合条件的{@link Element}子孙节点
     * @param element {@link Element}：当前要查找的Element节点；
     * @param selector {@link String}：Element节点条件；
     * @return {@link List}<{@link Element}>：Element节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午11:07:49
     * @Description:
     */
    public static List<Element> Find(Element element, String selector) {
        return Find(element.getChildNodes(), selector);
    }
    /**
     * 按条件，查找下级所有符合条件的{@link Element}子孙节点
     * @param nodes {@link NodeList}：节点列表；
     * @param selector {@link String}：Element节点条件；
     * @return {@link List}<{@link Element}>：Element节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午11:06:03
     * @Description:
     */
    public static List<Element> Find(NodeList nodes, String selector) {
        Assist la = FindIf(selector);
        return la.find(nodes);
    }

    /**
     * 查找第一个{@link Element}父节点。
     * @param node {@link Node}：当前要查找的Node节点；
     * @return {@link Element}：Element父节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月10日 上午11:04:17
     * @Title: Parent
     * @Description:
     */
    public static Element Parent(Node node) {
        return Parent(node, null);
    }
    /**
     * 查找第一个符合条件的{@link Element}父节点。
     * @param node {@link Node}：当前要查找的Node节点；
     * @param selector {@link String}：Element节点条件；
     * @return {@link Element}：Element父节点。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月9日 下午8:57:24
     * @Title: Parent
     * @Description:
     */
    public static Element Parent(Node node, String selector) {
        Assist la = FindIf(selector);
        return la.parent(node);
    }

    /**
     * 查找所有{@link Element}父节点。
     * @param node {@link Node}：当前要查找的Node节点；
     * @return {@link List}<{@link Element}>：Element父节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月9日 下午8:46:05
     * @Description:
     */
    public static List<Element> Parents(Node node) {
        return Parents(node, null);
    }
    /**
     * 查找所有符合条件的{@link Element}父节点。
     * @param node {@link Node}：当前要查找的Node节点；
     * @param selector {@link String}：Element节点条件；
     * @return {@link List}<{@link Element}>：Element父节点列表。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月9日 下午8:46:11
     * @Description:
     */
    public static List<Element> Parents(Node node, String selector) {
        Assist la = FindIf(selector);
        return la.parents(node);
    }

    /**
     * 查找首个Node节点条件处理
     * @param selector {@link String}：Element节点条件；
     * @return {@link Assist}：携带条件处理的JavaParseDom辅助类。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午2:20:30
     * @Description:
     */
    private static Assist FindOneIf(String selector) {
        final String[] lab = Selector(selector);
        Assist la = new Assist() {
            @Override
            protected boolean findIf(Element e) {
                if (lab.length == 1 && e.getNodeName().equals(lab[0]))	//按标签名获取
                    return true;
                if (lab.length == 2 && lab[1].equals(e.getAttribute(lab[0])))	//按属性名=属性值获取
                    return true;
                return false;
            }
        };
        return la;
    }
    /**
     * 查找Node节点条件处理
     * @param selector {@link String}：Element节点条件；
     * @return {@link Assist}：携带条件处理的JavaParseDom辅助类。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 下午2:53:35
     * @Description:
     */
    private static Assist FindIf(String selector) {
        final String[] lab = Selector(selector);
        return new Assist() {
            @Override
            protected boolean findIf(Element e) {
                if (lab.length == 1 && !e.getNodeName().equals(lab[0]))	//按标签名获取
                    return false;
                if (lab.length == 2 && !lab[1].equals(e.getAttribute(lab[0])))	//按属性名=属性值获取
                    return false;
                return true;
            }
        };
    }

    /**
     * “选择器”处理
     * @param selector {@link String}：“选择器”名称；
     * @return {@link String}[]：节点条件。
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年5月4日 下午2:14:52
     * @Description:
     */
    private static String[] Selector(String selector) {
        return (selector != null && selector.trim().length() != 0) ? selector.split("=") : new String[0];
    }

    /**
     * 解析xml文本
     * @param pms {@link Object}[]：用于读取xml的标准字符、文件、I/O流等；
     * @return {@link JavaParseDom}：this。
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws ReflectiveOperationException
     * @author Haining.Liu
     * @date 2019年4月9日 上午11:19:09
     */
    private JavaParseDom constructor(Object... pms) throws ParserConfigurationException, SAXException, IOException, SecurityException, IllegalArgumentException, ReflectiveOperationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false);		//禁止加载额外的xml信息
        DocumentBuilder db = dbf.newDocumentBuilder();

        Class<?>[] pmcs = new Class<?>[pms.length];
        for (int i = 0; i < pms.length; i++)
            pmcs[i] = pms[i].getClass();

        Class<? extends DocumentBuilder> dbCla = db.getClass();
        Method m = this.getMethod(dbCla, "parse", pmcs);	//反射调用"parse"方法
        Document doc = (Document) Objects.requireNonNull(m, "'parse' method is null.").invoke(db, pms);
        return this.set(doc);
    }
    /**
     * 获取指定方法
     * @param cs {@link Class}<?>：源Class类；
     * @param method {@link String}：要获取的方法名称；
     * @param pmcs {@link Class}<?>[]：指定方法的参数；
     * @return {@link Method}：方法对象。
     * @author Haining.Liu
     * @date 2019年4月9日 下午5:57:56
     */
    private Method getMethod(Class<?> cs, String method, Class<?>... pmcs) {
        Method[] ms = cs.getMethods();
        if (ms == null || method == null)
            return null;

        get: for (int i = 0; i < ms.length; i++) {
            Method m = ms[i];
            if (!m.getName().equals(method) || m.getParameterCount() != pmcs.length)
                continue;
            boolean thePms = false;
            Class<?>[] pms = m.getParameterTypes();
            for (int j = 0; j < pms.length; j++) {
                if (!(thePms = pms[j].isAssignableFrom(pmcs[j])))
                    continue get;
            }
            if (thePms)
                return m;
        }
        return null;
    }

    /**
     * {@link Node}节点，辅助调用
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2019年4月11日 下午12:03:22
     * @Description: 此类为优化{@link JavaParseDom}中的静态方法调用，可使用链式调用。
     * <b>非线程安全，不建议使用单例。</b>
     */
    public class Case {
        public Node nowNode;	//当前节点

        /**
         * {@link Node}节点，辅助调用
         * @param selector {@link String}：节点选择器；
         * @see JavaParseDom#FindOneSibLeap(Element, String)
         */
        public Case(String selector) {
            this(JavaParseDom.FindOneSibLeap(getElement(), selector));
        }
        /**
         * {@link Node}节点，辅助调用
         * @param dom {@link JavaParseDom}；
         */
        public Case(JavaParseDom dom) {
            this(dom.getElement());
        }
        /**
         * {@link Node}节点，辅助调用
         * @param doc {@link Document}：当前上下文；
         */
        public Case(Document doc) {
            this(doc.getDocumentElement());
        }
        /**
         * {@link Node}节点，辅助调用
         * @param node {@link Node}：当前节点；
         */
        public Case(Node node) {
            this.nowNode = node;
        }

        /**
         * 是否存在此属性
         * @param name {@link String}：属性名；
         * @return {@link Boolean}：true - 存在。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:20:45
         */
        public boolean existAttrs(String name) {
            return this.nowNode == null ?
                    false :
                    JavaParseDom.ExistAttrs(this.nowNode, name);
        }
        /**
         * 获取所有属性名
         * @return {@link String}[]：属性名组；
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:49:57
         */
        public String[] getAttrs() {
            return this.nowNode == null ?
                    new String[0] :
                    JavaParseDom.GetAttrs(this.nowNode);
        }
        /**
         * 获取属性Map
         * @return {@link Map}<{@link String}, {@link String}>：属性的key、value值。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         */
        public Map<String, String> getAttrMap() {
            return this.nowNode == null ?
                    new LinkedHashMap<String, String>(0) :
                    JavaParseDom.GetAttrMap(this.nowNode);
        }

        /**
         * 查找下级所有的{@link Element}子孙节点
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         */
        public CaseList find() {
            return this.find(null);
        }
        /**
         * 按条件，查找下级所有符合条件的{@link Element}子孙节点
         * @param selector {@link String}：Element节点条件；
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         */
        public CaseList find(String selector) {
            return this.nowNode == null ?
                    new CaseList() :
                    new CaseList(JavaParseDom.Find(this.nowNode.getChildNodes(), selector));
        }

        /**
         * 按条件，查找下级及以下，第一个符合条件的{@link Element}节点。
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#findOne(NodeList)
         */
        public Case findOne(String selector) {
            if (this.nowNode != null)
                this.nowNode = JavaParseDom.FindOne(this.nowNode.getChildNodes(), selector);
            return this;
        }
        /**
         * 按条件，<b>逐级、逐次、纵向</b>查找下级及以下，第一个符合条件的{@link Element}节点。
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#findOneSib(NodeList)
         */
        public Case findOneSib(String selector) {
            if (this.nowNode != null)
                this.nowNode = JavaParseDom.FindOneSib(this.nowNode.getChildNodes(), selector);
            return this;
        }
        /**
         * 按条件，<b>纵向、逐级</b>查找下级及以下，第一个符合条件的{@link Element}节点。
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#findOneSibLeap(NodeList)
         */
        public Case findOneSibLeap(String selector) {
            if (this.nowNode != null)
                this.nowNode = JavaParseDom.FindOneSibLeap(this.nowNode.getChildNodes(), selector);
            return this;
        }

        /**
         * 查找第一个符合条件的{@link Element}父节点。
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parent(Node)
         */
        public Case parent() {
            return this.parent(null);
        }
        /**
         * 查找第一个符合条件的{@link Element}父节点。
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parent(Node)
         */
        public Case parent(String selector) {
            if (this.nowNode != null)
                this.nowNode = JavaParseDom.Parent(this.nowNode, selector);
            return this;
        }

        /**
         * 查找所有符合条件的{@link Element}父节点。
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parents(Node)
         */
        public CaseList parents() {
            return this.parents(null);
        }
        /**
         * 查找所有符合条件的{@link Element}父节点。
         * @param selector {@link String}：Element节点条件；
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parents(Node)
         */
        public CaseList parents(String selector) {
            return this.nowNode == null ?
                    new CaseList() :
                    new CaseList(JavaParseDom.Parents(this.nowNode, selector));
        }
    }
    /**
     * {@link NodeList}节点有序集合，辅助调用
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2019年4月11日 下午12:03:22
     * @Description: 此类为优化{@link JavaParseDom}中的静态方法调用，可使用链式调用。
     */
    public class CaseList {
        public List<Element> nowNodes;

        private CaseList() {
            super();
        }
        /**
         * {@link Node}节点有序集合，辅助调用
         * @param selector {@link String}：节点选择器；
         * @see JavaParseDom#Find(Element, String)
         */
        public CaseList(String selector) {
            this(JavaParseDom.Find(getElement(), selector));
        }
        /**
         * {@link Node}节点有序集合，辅助调用
         * @param nodes {@link List}<{@link Element}>：当前节点集合；
         */
        public CaseList(List<Element> nodes) {
            this.nowNodes = nodes;
        }

        /**
         * 节点集合大小
         * @return {@link Integer}：大小值。
         * @author Haining.Liu
         * @date 2019年4月11日 下午8:02:58
         */
        public int size() {
            return this.nowNodes == null ? 0 : this.nowNodes.size();
        }
        /**
         * 获取指定索引处的{@link Element}节点
         * @param i {@link Integer}：指定索引；
         * @return {@link Element}：指定节点。
         * @author Haining.Liu
         * @date 2019年4月11日 下午8:02:58
         */
        public Element get(int i) {
            return this.size() <= i ? null : this.nowNodes.get(i);
        }
        /**
         * 获取{@link Case}辅助调用
         * @param i {@link Integer}：指定索引；
         * @return {@link Case}：辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午8:02:58
         */
        public Case getCase(int i) {
            return new Case(this.get(i));
        }

        /**
         * 按条件，查找指定节点下级所有符合条件的{@link Element}子孙节点
         * @param i {@link Integer}：指定索引；
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         */
        public CaseList find(int i) {
            return this.find(i, null);
        }
        /**
         * 按条件，查找指定节点下级所有符合条件的{@link Element}子孙节点
         * @param i {@link Integer}：指定索引；
         * @param selector {@link String}：Element节点条件；
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         */
        public CaseList find(int i, String selector) {
            return this.getCase(i).find(selector);
        }

        /**
         * 按条件，查找指定节点下级及以下，第一个符合条件的{@link Element}节点。
         * @param i {@link Integer}：指定索引；
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#findOne(NodeList)
         */
        public Case findOne(int i, String selector) {
            return this.getCase(i).findOne(selector);
        }
        /**
         * 按条件，<b>逐级、逐次、纵向</b>查找指定节点下级及以下，第一个符合条件的{@link Element}节点。
         * @param i {@link Integer}：指定索引；
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#findOneSib(NodeList)
         */
        public Case findOneSib(int i, String selector) {
            return this.getCase(i).findOneSib(selector);
        }
        /**
         * 按条件，<b>纵向、逐级</b>查找指定节点下级及以下，第一个符合条件的{@link Element}节点。
         * @param i {@link Integer}：指定索引；
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#findOneSibLeap(NodeList)
         */
        public Case findOneSibLeap(int i, String selector) {
            return this.getCase(i).findOneSibLeap(selector);
        }

        /**
         * 查找第一个符合条件的{@link Element}父节点。
         * @param i {@link Integer}：指定索引；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parent(Node)
         */
        public Case parent(int i) {
            return this.parent(i, null);
        }
        /**
         * 查找第一个符合条件的{@link Element}父节点。
         * @param i {@link Integer}：指定索引；
         * @param selector {@link String}：Element节点条件；
         * @return {@link Case}：Element节点，辅助调用。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parent(Node)
         */
        public Case parent(int i, String selector) {
            return this.getCase(i).parent(selector);
        }

        /**
         * 查找所有符合条件的{@link Element}父节点。
         * @param i {@link Integer}：指定索引；
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parents(Node)
         */
        public CaseList parents(int i) {
            return this.parents(i, null);
        }
        /**
         * 查找所有符合条件的{@link Element}父节点。
         * @param i {@link Integer}：指定索引；
         * @param selector {@link String}：Element节点条件；
         * @return {@link CaseList}：节点有序集合。
         * @author Haining.Liu
         * @date 2019年4月11日 下午7:50:55
         * @see Assist#parents(Node)
         */
        public CaseList parents(int i, String selector) {
            return this.getCase(i).parents(selector);
        }
    }

    /**
     * JavaParseDom辅助类
     * @version V1.0.1
     * @author Haining.Liu
     * @date 2018年3月19日 上午10:52:34
     * @Description:
     */
    private abstract static class Assist {
        /**
         * 查找Node节点的条件
         * @param e {@link Element}：Element节点；
         * @return {@link Boolean}：true - 条件成立。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年3月19日 上午10:53:04
         */
        protected abstract boolean findIf(Element e);
        /**
         * 查找下级及以下，第一个符合条件的{@link Element}节点。若有条件，则按{@link Assist}.findIf()条件过滤
         * @param nodes {@link NodeList}：节点列表；
         * @return {@link Element}：Element节点。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年3月19日 下午2:30:18
         * @Description: 查找方式：
         * <p>1：&lt;ol&gt;</p>
         * <p style="margin-left: 30px;">2：&lt;li&gt;</p>
         * <p style="margin-left: 60px;">3：&lt;p&gt;</p>
         * <p style="margin-left: 90px;">4：&lt;a&gt;</p>
         * <p style="margin-left: 90px;">5：&lt;a&gt;</p>
         * <p style="margin-left: 60px;">6：&lt;p&gt;</p>
         * <p style="margin-left: 90px;">7：&lt;a&gt;</p>
         * <p style="margin-left: 30px;">8：&lt;li&gt;</p>
         * <p style="margin-left: 60px;">9：&lt;p&gt;</p>
         * <p style="margin-left: 60px;">10：&lt;p&gt;</p>
         */
        public Element findOne(NodeList nodes) {
            Element e = null;
            if (nodes != null) {
//				String addStr = this.addStr(n ++);
                int len = nodes.getLength();
                for (int i = 0; i < len; i++) {
                    Node node = nodes.item(i);
                    short nt = node.getNodeType();	//节点类型
                    if (nt == Node.ELEMENT_NODE) {
                        Element ne = (Element) node;
//						System.out.print(addStr + ne.getNodeName());
                        if (this.findIf(ne)) {
//							System.out.println("\t\t√");
                            e = ne;
                            break;
                        } else {
//							System.out.println("\t\t×");
                            NodeList nes = ne.getChildNodes();
                            ne = null;				//释放此引用，防止递归时，内存溢出
                            e = this.findOne(nes);	//递归查找下级Node节点
                            if (e != null)
                                break;
                        }
                    }
                }
//				n --;
            }
            return e;
        }
        /**
         * <b>逐级、逐次、纵向</b>查找下级及以下，第一个符合条件的{@link Element}节点。
         * @param nodes {@link NodeList}：节点列表；
         * @return {@link Element}：Element节点。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年5月4日 上午11:54:56
         * @Description: 查找方式：
         * <p>1：&lt;ol&gt;</p>
         * <p style="margin-left: 30px;">2：&lt;li&gt;</p>
         * <p style="margin-left: 60px;">4：&lt;p&gt;</p>
         * <p style="margin-left: 90px;">6：&lt;a&gt;</p>
         * <p style="margin-left: 90px;">7：&lt;a&gt;</p>
         * <p style="margin-left: 60px;">5：&lt;p&gt;</p>
         * <p style="margin-left: 90px;">8：&lt;a&gt;</p>
         * <p style="margin-left: 30px;">3：&lt;li&gt;</p>
         * <p style="margin-left: 60px;">9：&lt;p&gt;</p>
         * <p style="margin-left: 60px;">10：&lt;p&gt;</p>
         */
        public Element findOneSib(NodeList nodes) {
            Element e = null;
            e: if (nodes != null) {
//				String addStr = this.addStr(n ++);
                int len = nodes.getLength();
                List<NodeList> ns = null;
                for (int i = 0; i < len; i++) {
                    Node node = nodes.item(i);
                    short nt = node.getNodeType();	//节点类型
                    if (nt == Node.ELEMENT_NODE) {
                        Element ne = (Element) node;
//						System.out.print(addStr + ne.getNodeName());
                        if (this.findIf(ne)) {
//							System.out.println("\t\t√");
                            e = ne;
                            break e;
                        } else {
//							System.out.println("\t\t×");
                            NodeList nes = ne.getChildNodes();
                            if (ns == null)
                                ns = new ArrayList<NodeList>(len);
                            ns.add(nes);
                        }
                    }
                }
                if (ns != null)
                    for (int i = 0, sz = ns.size(); i < sz; i++) {
                        NodeList n = ns.get(i);
                        e = this.findOneSib(n);
                        if (e != null)
                            break e;
                    }
//				n --;
            }
            return e;
        }
        /**
         * <b>纵向、逐级</b>查找下级及以下，第一个符合条件的{@link Element}节点。
         * <p>适合查找有层级规范的{@link Element}节点。</p>
         * @param nodes {@link NodeList}：节点列表；
         * @return {@link Element}：Element节点。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年5月4日 下午8:17:57
         * @Description: 查找方式：
         * <p>1：&lt;ol&gt;</p>
         * <p style="margin-left: 30px;">2：&lt;li&gt;</p>
         * <p style="margin-left: 60px;">4：&lt;p&gt;</p>
         * <p style="margin-left: 60px;">5：&lt;p&gt;</p>
         * <p style="margin-left: 90px;">8：&lt;a&gt;</p>
         * <p style="margin-left: 30px;">3：&lt;li&gt;</p>
         * <p style="margin-left: 60px;">6：&lt;p&gt;</p>
         * <p style="margin-left: 60px;">7：&lt;p&gt;</p>
         * <p style="margin-left: 90px;">9：&lt;a&gt;</p>
         * <p style="margin-left: 90px;">10：&lt;a&gt;</p>
         */
        public Element findOneSibLeap(NodeList nodes) {
            ArrayList<NodeList> ns = new ArrayList<NodeList>(1);
            ns.add(nodes);
            return this.findOneSibLeap(ns);
        }
        /**
         * <b>纵向、逐级</b>查找下级及以下，第一个符合条件的{@link Element}节点。
         * @param ns {@link List}<{@link NodeList}>：子节点列表集；
         * @return {@link Element}：Element节点。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年5月4日 下午8:18:29
         * @see Assist#findOneSibLeap(NodeList)
         */
        public Element findOneSibLeap(List<NodeList> ns) {
            Element e = null;
            e: if (ns != null) {
                List<NodeList> nls = null;
//				String addStr = this.addStr(n ++);
                for (int i = 0, sz = ns.size(); i < sz; i++) {
                    NodeList nodes = ns.get(i);
                    if (nodes != null) {
                        int len = nodes.getLength();
                        for (int j = 0; j < len; j++) {
                            Node node = nodes.item(j);
                            short nt = node.getNodeType();	//节点类型
                            if (nt == Node.ELEMENT_NODE) {
                                Element ne = (Element) node;
//								System.out.print(addStr + ne.getNodeName());
                                if (this.findIf(ne)) {
//									System.out.println("\t\t√");
                                    e = ne;
                                    break e;
                                } else {
//									System.out.println("\t\t×");
                                    NodeList nes = ne.getChildNodes();
                                    if (nls == null)
                                        nls = new ArrayList<NodeList>(len * sz);
                                    nls.add(nes);
                                }
                            }
                        }
                    }
                }
                e = this.findOneSibLeap(nls);
                if (e != null)
                    break e;
//				n--;
            }
            return e;
        }
        /**
         * 查找下级所有符合条件的{@link Element}子孙节点。
         * <p>若有条件，则按{@link Assist}.findIf()条件过滤</p>
         * @param nodes {@link NodeList}：节点列表；
         * @return {@link List}<{@link Element}>：Element节点列表。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年3月19日 上午10:48:55
         */
        public List<Element> find(NodeList nodes) {
            List<Element> es = null;
            if (nodes != null) {
                int len = nodes.getLength();
                for (int i = 0; i < len; i++) {
                    Node node = nodes.item(i);
                    short nt = node.getNodeType();	//节点类型
                    if (nt == Node.ELEMENT_NODE) {
                        Element e = (Element) node;
                        if (es == null)
                            es = new ArrayList<Element>(16);
                        NodeList ns = e.getChildNodes();
                        if (this.findIf(e))
                            es.add(e);
                        else
                            e = null;				//释放此引用，防止递归时，内存溢出
                        List<Element> nes = this.find(ns);	//递归查找下级Node节点
                        if (nes != null && nes.size() > 0)
                            es.addAll(nes);
                    }
                }
            }
            return es;
        }

        /**
         * 查找父级，第一个符合条件的{@link Element}节点。
         * <p>若没有条件，则查找第一个{@link Element}父节点后返回。</p>
         * @param node {@link Node}：当前节点；
         * @return {@link Element}：父节点。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年5月9日 下午8:15:40
         * @Description:
         */
        public Element parent(Node node) {
            while (true) {
                node = node.getParentNode();
                if (node == null)
                    return null;
                short nt = node.getNodeType();	//节点类型
                if (nt == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    if (this.findIf(e))
                        return e;
                }
            }
        }
        /**
         * 查找所有符合条件的{@link Element}父节点。
         * <p>若有条件，则按{@link Assist}.findIf()条件过滤</p>
         * @param node {@link Node}：当前节点；
         * @return {@link List}<{@link Element}>：Element父节点列表。
         * @version V1.0.1
         * @author Haining.Liu
         * @date 2018年5月9日 下午8:29:51
         * @Description:
         */
        public List<Element> parents(Node node) {
            List<Element> es = null;
            while (true) {
                node = node.getParentNode();
                if (node == null)
                    break;
                short nt = node.getNodeType();	//节点类型
                if (nt == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    if (this.findIf(e)) {
                        if (es == null)
                            es = new ArrayList<Element>(8);
                        es.add(e);
                    }
                }
            }
            return es;
        }

//		int n = 0;
//		/**
//		 * 用于控制台打印测试使用。
//		 */
//		private String addStr(int n) {
//			char[] c = new char[n];
//			for (int i = 0; i < n; i++) {
//				c[i] = '\t';
//			}
//			return new String(c);
//		}
    }

}
