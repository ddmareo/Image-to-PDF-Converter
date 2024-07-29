import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;

//Inheritance
public class PdfConverter extends Converter {
    private Rectangle pageSize;

    public PdfConverter(String outputFileName) {
        super(outputFileName);
    }

    public void setOrientation(boolean isPortrait) {
        if (isPortrait) {
            this.pageSize = PageSize.A4;
        } else {
            this.pageSize = PageSize.A4.rotate();
        }
    }

    //Overriding
    @Override
    public void convert(File[] files) {
        try {
            Document document = new Document();
            document.setPageSize(pageSize);

            PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
            document.open();

            for (File file : files) {
                Image image = Image.getInstance(file.getAbsolutePath());
                float width = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float height = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
                image.scaleToFit(width, height);
                document.add(image);
            }

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Conversion failed: " + e.getMessage());
        }
    }
}