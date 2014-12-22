import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CreateDom {

	public static void main(String[] args) throws TransformerException {
		
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		Document doc = impl.createDocument(svgNS, "svg", null);

		// Get the root element (the 'svg' element).
		Element svgRoot = doc.getDocumentElement();

		// Set the width and height attributes on the root 'svg' element.
		svgRoot.setAttributeNS(null, "width", "400");
		svgRoot.setAttributeNS(null, "height", "450");

		// Create the rectangle.
		Element rectangle = doc.createElementNS(svgNS, "rect");
		rectangle.setAttributeNS(null, "x", "10");
		rectangle.setAttributeNS(null, "y", "20");
		rectangle.setAttributeNS(null, "width", "100");
		rectangle.setAttributeNS(null, "height", "50");
		rectangle.setAttributeNS(null, "fill", "red");

		// Attach the rectangle to the root 'svg' element.
		svgRoot.appendChild(rectangle);
		
		//Now save the document as xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\Amit.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
	}
}
