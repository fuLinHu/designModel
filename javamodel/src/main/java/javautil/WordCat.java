package javautil;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/7/21 10:23
 * @Param $
 * @return $
 * @Version V1.0
 */
public class WordCat {

    public static void main(String[] args) throws IOException {
        createTOC();
    }


    /**
     * 生成目录
     * @param doc
     */
    public static void createTOC() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\180KBC-19103-CS-R2.2 (WinGD).docx");
        XWPFDocument doc = new XWPFDocument(fileInputStream);
//        OutputStream out = new FileOutputStream("xxxx");
//        doc.write(out);
//        out.close();

        CTSdtBlock block = doc.getDocument().getBody().addNewSdt();
//        ExportWord toc = new ExportWord(block);
//
//        /*当前位置调用添加正文的方法，需要传参XWPFDocumen对象*/
//        writeAllNews(doc);

        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph par : paragraphs) {
            String text = par.getText();
            String parStyle = par.getStyle();
            if(text==null||"".equals(text)||parStyle==null||"null".equals(parStyle)||"26".equals(parStyle)||"32".equals(parStyle)||"33".equals(parStyle)||"35".equals(parStyle)||"22".equals(parStyle)||"24".equals(parStyle)||containsLetter(parStyle)){
                continue;
            }
            //System.out.println("===========================");
            //System.out.println(text);
            System.out.println(parStyle);
            if (parStyle != null && parStyle.startsWith("Heading")) {

                //获取书签，书签的对应关系很重要，关系到目录能否正常跳转
                List<CTBookmark> bookmarkList = par.getCTP().getBookmarkStartList();
                try {
                    int level = Integer.parseInt(parStyle.substring("Heading".length()));

                    //添加标题
//                    toc.addRow(level, par.getText(), 1, bookmarkList.get(0).getName());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean containsLetter(String str) {
        String patt="[a-z|A-Z]";
        Pattern r = Pattern.compile(patt);
        Matcher matcher = r.matcher(str);
        return matcher.find();
    }

}
