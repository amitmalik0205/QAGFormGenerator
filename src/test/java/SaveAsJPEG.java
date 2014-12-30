import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;




public class SaveAsJPEG {

	  public static void main(String[] args) throws Exception {

/*	        // Create a JPEG transcoder
	        JPEGTranscoder t = new JPEGTranscoder();

	        // Set the transcoding hints.
	        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));

	        // Create the transcoder input.
	        File file = new File("C:\\Amit\\QAG\\a.svg");
	        TranscoderInput input = new TranscoderInput(file.toURI().toString());

	        // Create the transcoder output.
	        OutputStream ostream = new FileOutputStream("C:\\Amit\\QAG\\out.jpg");
	        TranscoderOutput output = new TranscoderOutput(ostream);

	        // Save the image.
	        t.transcode(input, output);

	        // Flush and close the stream.
	        ostream.flush();
	        ostream.close();
	        System.exit(0);	*/	
		  
	        StringBuilder builder = new StringBuilder("<html><body>");
	        builder.append("<div style=\"width:15px;height:15px;border-radius:15px;border:2px solid;line-height:15px;text-align:center;font-family:Arial;font-size:12px\">A</div>");
	        builder.append("</br>");
	        builder.append("<div style=\"width:15px;height:15px;border-radius:15px;border:2px solid;line-height:15px;text-align:center;font-family:Arial;font-size:12px\">1</div>");
	        builder.append("</br>");
	        builder.append("<div style=\"width:15px;height:15px;border-radius:15px;border:2px solid;line-height:15px;background-color:black\"></div>");
	        builder.append("</body></html>");
	        
	        //pd4ml demo
	        
	        /*File f = new File("C:\\Amit\\QAG\\pd4ml.pdf");
	        java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
	        OutputStream sos = System.out;
	         
	        File fz = new File("C:\\Users\\amitmalik\\Desktop\\demo.html");
	        java.io.FileInputStream fis = new java.io.FileInputStream(fz);
	        InputStreamReader isr = new InputStreamReader( fis, "UTF-8" );
	        
	        PD4ML html = new PD4ML();
	        html.setPageSize( new Dimension(450, 450) );
	        html.setPageInsets( new Insets(20, 50, 10, 10) );
	        html.setHtmlWidth( 750 );
	        html.enableImgSplit( false );
	   	      	        
	        html.render( isr, fos);*/
	        
	        // itext demo
	        
	        Document document = new Document();

	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Amit\\QAG\\itext.pdf"));

	        document.open();

	        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("C:\\Users\\amitmalik\\Desktop\\demo2.html"));

	        document.close();
	    }
}
