package javautil;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.ByteBuffer;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import lombok.SneakyThrows;
import org.apache.poi.util.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/11/5 18:45
 * @Param $
 * @return $
 * @Version V1.0
 */
public class CutPdf1 {
    @SneakyThrows
    public static void main(String[] args) {
        splitFile("E:\\项目文件(北明智通)\\外高桥\\文档\\扫面得pdf\\GB T 12923-2012.PDF",0,0);

    }
    public static String splitFile(String pdfFile,Integer from,Integer end) throws Exception {
        File file = new File(pdfFile);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("GB T 12923-2012.PDF", fileInputStream);
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(multipartFile.getBytes());
            int n = reader.getNumberOfPages();
            for (int i = 0; i < n; i++) {
                document = new Document(reader.getPageSize(i+1));
                copy = new PdfCopy(document, new FileOutputStream("out/pdf/"+i+".pdf"));
                document.open();
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, i+1);
                copy.addPage(page);
                document.close();
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch(DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<Integer, OutputStream> splitFile(String pdfFile){
        Map<Integer,OutputStream> outputStreamMap = new HashMap<>();
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            for (int i = 0; i < n; i++) {
                document = new Document(reader.getPageSize(i+1));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                copy = new PdfCopy(document, byteArrayOutputStream);
                document.open();
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, i+1);
                copy.addPage(page);
                document.close();
                outputStreamMap.put(i+1,byteArrayOutputStream);
            }
            return outputStreamMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch(DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map<Integer, OutputStream> splitFile(MultipartFile multipartFile){
        Map<Integer,OutputStream> outputStreamMap = new HashMap<>();
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(multipartFile.getBytes());
            int n = reader.getNumberOfPages();
            for (int i = 0; i < n; i++) {
                document = new Document(reader.getPageSize(i+1));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                copy = new PdfCopy(document, byteArrayOutputStream);
                document.open();
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, i+1);
                copy.addPage(page);
                document.close();
                outputStreamMap.put(i+1,byteArrayOutputStream);
            }
            return outputStreamMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch(DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }


}
