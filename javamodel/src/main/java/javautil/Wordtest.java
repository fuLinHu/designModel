package javautil;

import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TableOfContent;
import com.spire.doc.fields.TextRange;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;

/**
 * word 转换成html
 */
public class Wordtest {

    /**
     * 2007版本word转换成html
     * @throws IOException
     */
//    public void Word2007ToHtml() throws IOException {
//        String filepath = "F:/test/";
//        String fileName = "123.docx";
//        String htmlName = "123.html";
//        final String file = filepath + fileName;
//        File f = new File(file);
//        if (!f.exists()) {
//            System.out.println("Sorry File does not Exists!");
//        } else {
//            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
//
//                // 1) 加载word文档生成 XWPFDocument对象
//                InputStream in = new FileInputStream(f);
//                XWPFDocument document = new XWPFDocument(in);
//
//                // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
//                File imageFolderFile = new File(filepath);
//                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
//                options.setExtractor(new FileImageExtractor(imageFolderFile));
//                options.setIgnoreStylesIfUnused(false);
//                options.setFragment(true);
//
//                // 3) 将 XWPFDocument转换成XHTML
//                OutputStream out = new FileOutputStream(new File(filepath + htmlName));
//                XHTMLConverter.getInstance().convert(document, out, options);
//
//                //也可以使用字符数组流获取解析的内容
////                ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                XHTMLConverter.getInstance().convert(document, baos, options);
////                String content = baos.toString();
////                System.out.println(content);
////                 baos.close();
//            } else {
//                System.out.println("Enter only MS Office 2007+ files");
//            }
//        }
//    }
    public static void main(String[] args) throws IOException {
//        try {
//            Word2003ToHtml();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
        //doWord2007(new File("F:\\180KBC-19103-CS-R2.2 (WinGD).docx"));
        //grentWordCat();
        parse();
    }


    /**
     * /**
     * 2003版本word转换成html
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public static void Word2003ToHtml() throws IOException, TransformerException, ParserConfigurationException {
        final String imagepath = "F:\\";//解析时候如果doc文件中有图片  图片会保存在此路径
        String filepath = "F:\\";
        String fileName = "新2003.doc";
        String htmlName = "新2003.html";
        long l = System.currentTimeMillis();

        final String file = filepath + fileName;
        InputStream input = new FileInputStream(new File(file));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(imagepath);
                if(!imgPath.exists()){//图片目录不存在则创建
                    imgPath.mkdirs();
                }
                File file = new File(imagepath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return imagepath + suggestedName;
            }
        });

        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

        File htmlFile = new File(filepath + htmlName);
        OutputStream outStream = new FileOutputStream(htmlFile);

        //也可以使用字符数组流获取解析的内容
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        OutputStream outStream = new BufferedOutputStream(baos);

        DOMSource domSource = new DOMSource(htmlDocument);

        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        serializer.transform(domSource, streamResult);

        //也可以使用字符数组流获取解析的内容
//        String content = baos.toString();
//        System.out.println(content);
//        baos.close();
        outStream.close();
        long end = System.currentTimeMillis();
        System.out.println((end-l)/1000);
    }

    public static String doWord2007(File inFile) {

//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        String randomName = "PIC"+getOnlyId();
//        //转换后html中图片src的链接
//        final String baseUrl = projectUrl+"wordpic/"+randomName+"/";
//        //转换后图片存放的位置
//        String dir = projectPath+"/wordpic/"+randomName+"/";
//        File dirF = new File(dir);
//        if(!dirF.exists()||!dirF.isDirectory()){
//            dirF.mkdir();
//        }
        try{

            XWPFDocument wordDocument = new XWPFDocument(new FileInputStream(inFile));
            OutputStream out = new FileOutputStream("F:\\180KBC-19103-CS-R2.2 (WinGD).html");
            XHTMLOptions options = XHTMLOptions.create();
            //options.setFragment(true);
//            XHTMLConverter.getInstance().convert(document, out, options);

//            XHTMLOptions options = XHTMLOptions.create().URIResolver(new BasicURIResolver(baseUrl));
//            File imageFolderFile = new File(dir);
//            options.setExtractor(new FileImageExtractor(imageFolderFile));
            XHTMLConverter.getInstance().convert(wordDocument, out, options);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String();
    }


    public static void grentWordCat(){
        //加载已设置大纲级别的测试文档
        com.spire.doc.Document doc = new com.spire.doc.Document("F:\\180KBC-19103-CS-R2.2 (WinGD).docx");

        //在文档最前面插入一个段落，写入文本并格式化
        Paragraph parainserted = new Paragraph(doc);
        TextRange tr= parainserted.appendText("目 录");
        tr.getCharacterFormat().setBold(true);
        tr.getCharacterFormat().setTextColor(Color.gray);
        doc.getSections().get(0).getParagraphs().insert(0,parainserted);
        parainserted.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //通过域代码添加目录表
        TableOfContent toc = new TableOfContent(doc, "{\\o \"1-3\" \\h \\z \\u}");

        doc.getSections().get(0).getParagraphs().get(0).appendTOC(1,3);
        doc.updateTableOfContents();

        //保存文档
        doc.saveToFile("F:\\AddToc2.docx", FileFormat.Docx_2010);
    }

    public static void parse() throws IOException {
        //加载Word测试文档
        com.spire.doc.Document doc = new  com.spire.doc.Document();
        doc.loadFromFile("F:\\180KBC-19103-CS-R2.2 (WinGD).docx");

        //保存标题内容到.txt文档
        File file = new File("F:\\GetTitle.txt");
        if (file.exists())
        {
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        //遍历section
        for (int i = 0; i < doc.getSections().getCount(); i++)
        {
            Section section = doc.getSections().get(i);
            //遍历Paragraph
            for (int j = 0; j < section.getParagraphs().getCount(); j++)
            {
                Paragraph paragraph = section.getParagraphs().get(j);

                //获取标题
                if ( paragraph.getStyleName().matches("1"))//段落为“标题1”的内容
                {
                    //获取段落标题内容
                    String text = paragraph.getText();

                    //写入文本到txt文档
                    bw.write("标题1: "+ text + "\r");
                }
                //获取标题
                if ( paragraph.getStyleName().matches("2"))//段落为“标题2”的内容
                {
                    //获取段落标题内容
                    String text = paragraph.getText();

                    //写入文本到txt文档
                    bw.write("标题2: " + text + "\r");
                }
                //获取标题
                if ( paragraph.getStyleName().matches("3"))//段落为“标题3”的内容
                {
                    //获取段落标题内容
                    String text = paragraph.getText();

                    //写入文本到txt文档
                    bw.write("标题3: " + text+"\r");
                }
                //获取标题
                if ( paragraph.getStyleName().matches("4"))//段落为“标题4”的内容
                {
                    //获取段落标题内容
                    String text = paragraph.getText();

                    //写入文本到txt文档
                    bw.write("标题4: " + text+"\r");
                }

                bw.write("\n");
            }

        }
        bw.flush();
        bw.close();
        fw.close();
    }




}
