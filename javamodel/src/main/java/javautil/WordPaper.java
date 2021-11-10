package javautil;

import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlideShow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/8/14 17:42
 * @Param $
 * @return $
 * @Version V1.0
 */
public class WordPaper {

    public static void main(String[] args) throws Exception {
        int num=run("E:\\项目文件(北明智通)\\外高桥\\文档\\碎片化\\180KBC-19103-CS-R2.2 (WinGD).docx");
        System.out.println(num);
    }

    private static int run(String filePath) throws Exception {
        String lowerFilePath = filePath.toLowerCase();

        if (lowerFilePath.endsWith(".xls")) {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(lowerFilePath));

            Integer sheetNums = workbook.getNumberOfSheets();

            if (sheetNums > 0) {
                return workbook.getSheetAt(0).getRowBreaks().length + 1;

            }

        } else if (lowerFilePath.endsWith(".xlsx")) {
            XSSFWorkbook xwb = new XSSFWorkbook(lowerFilePath);

            Integer sheetNums = xwb.getNumberOfSheets();

            if (sheetNums > 0) {
                return xwb.getSheetAt(0).getRowBreaks().length + 1;

            }

        } else if (lowerFilePath.endsWith(".docx")) {
            XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(lowerFilePath));
            List<IBodyElement> bodyElements = docx.getBodyElements();
            for (IBodyElement bodyElement : bodyElements) {
                System.out.println(bodyElement.getPart());
            }

            List<XWPFHeader> headerList = docx.getHeaderList();
            for (XWPFHeader xwpfHeader : headerList) {
                List<XWPFParagraph> paragraphs = xwpfHeader.getParagraphs();
                /*for (XWPFParagraph paragraph : paragraphs) {
                    String text = paragraph.getText();
                    System.out.println(text);
                }*/
                List<XWPFParagraph> listParagraph = xwpfHeader.getListParagraph();
                for (XWPFParagraph xwpfParagraph : listParagraph) {
                    String text = xwpfParagraph.getText();
                    System.out.println(text);
                }
                /*String text = xwpfHeader.getText();
                System.out.println(text);*/
            }
            List<XWPFFooter> footerList = docx.getFooterList();
            if(footerList!=null&&footerList.size()>0){
                for (XWPFFooter xwpfFooter : footerList) {
                    List<XWPFParagraph> listParagraph = xwpfFooter.getListParagraph();
                    if(listParagraph!=null&&listParagraph.size()>0){
                        for (XWPFParagraph xwpfParagraph : listParagraph) {
                            String text = xwpfParagraph.getText();
                            System.out.println(text);
                        }
                    }
                }
            }
            CTProperties underlyingProperties = docx.getProperties().getExtendedProperties().getUnderlyingProperties();
            return underlyingProperties.getPages();

        } else if (lowerFilePath.endsWith(".doc")) {
            HWPFDocument wordDoc = new HWPFDocument(new FileInputStream(lowerFilePath));

            return wordDoc.getSummaryInformation().getPageCount();

        } /*else if (lowerFilePath.endsWith(".ppt")) {
            HSLFSlideShow document = new HSLFSlideShow(new FileInputStream(lowerFilePath));

            SlideShow slideShow = new SlideShow(document);

            return slideShow.getSlides().length;

        } else if (lowerFilePath.endsWith(".pptx")) {
            XSLFSlideShow xdocument = new XSLFSlideShow(lowerFilePath);

            XMLSlideShow xslideShow = new XMLSlideShow(xdocument);

            return xslideShow.getSlides().length;

        }*/
        return 0;

    }

}
