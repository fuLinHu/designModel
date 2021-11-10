package javautil;

import com.spire.pdf.PdfDocument;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/11/5 18:27
 * @Param $
 * @return $
 * @Version V1.0
 */
public class CutPdf {
    public static void main(String[] args) {
        //加载需要拆分的PDF文档

        PdfDocument doc = new PdfDocument();

        doc.loadFromFile("E:\\项目文件(北明智通)\\外高桥\\文档\\扫面得pdf\\GB T 12923-2012.PDF");

        //调用方法split()将PDF文档按每一页拆分为单独的文档

        doc.split("output/splitDocument-{0}.pdf", 1);

        doc.close();
    }
}
