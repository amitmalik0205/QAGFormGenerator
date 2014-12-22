import java.io.File;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.JPanel;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.ImageHandlerPNGEncoder;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class SaveToPng {

	public static void main(String[] args) throws SVGGraphics2DIOException, UnsupportedEncodingException {
		
		DOMImplementation impl = GenericDOMImplementation
				.getDOMImplementation();
		String svgNS = "http://www.w3.org/2000/svg";
		Document myFactory = impl.createDocument(svgNS, "svg", null);

		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(myFactory);
		
		ImageHandlerPNGEncoder ihandler = new ImageHandlerPNGEncoder("C:\\Amit\\Airtel",null);
		ctx.setImageHandler(ihandler);
		
		SVGGraphics2D g2d = new SVGGraphics2D(ctx, false);
		TestSVGGen test = new TestSVGGen();
	    test.paint(g2d);
		
		JPanel panel = new JPanel();
		panel.paintComponents(g2d);
	    
		 Writer out = new OutputStreamWriter(System.out, "UTF-8");
		 g2d.stream(out, true);
		 
		 File file = new File("C:\\Amit\\Airtel\\a.txt");
	}
}
