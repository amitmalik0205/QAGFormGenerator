import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CreateImageFromDom {

	public static void main(String[] args) throws Exception {
		CreateImageFromDom dom = new CreateImageFromDom();
		Questions questions = dom.readXML();
		dom.save(dom.createDocument());
	}
	
	
	public Document createDocument() {
        // Create a new document.
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document document = impl.createDocument(svgNS, "svg", null);
        
        Element root = document.getDocumentElement();
        root.setAttributeNS(null, "width", "450");
        root.setAttributeNS(null, "height", "500");

        // Add some content to the document.
        Element e;
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "50");
        e.setAttributeNS(null, "cy", "50");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "text");
        e.setAttributeNS(null, "x", "46");
        e.setAttributeNS(null, "y", "54");   
        e.setAttributeNS(null, "style", "font-family:Verdana;font-size:12;fill:Black");
        e.setTextContent("A");
        root.appendChild(e);
              
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "80");
        e.setAttributeNS(null, "cy", "50");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "text");
        e.setAttributeNS(null, "x", "76");
        e.setAttributeNS(null, "y", "54");
        e.setAttributeNS(null, "style", "font-family:Verdana;font-size:12;fill:Black");
        e.setTextContent("B");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "110");
        e.setAttributeNS(null, "cy", "50");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "text");
        e.setAttributeNS(null, "x", "106");
        e.setAttributeNS(null, "y", "54");
        e.setAttributeNS(null, "style", "font-family:Verdana;font-size:12;fill:Black");
        e.setTextContent("C");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "140");
        e.setAttributeNS(null, "cy", "50");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "text");
        e.setAttributeNS(null, "x", "136");
        e.setAttributeNS(null, "y", "54");
        e.setAttributeNS(null, "style", "font-family:Verdana;font-size:12;fill:Black");
        e.setTextContent("D");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "170");
        e.setAttributeNS(null, "cy", "50");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "text");
        e.setAttributeNS(null, "x", "166");
        e.setAttributeNS(null, "y", "54");
        e.setAttributeNS(null, "style", "font-family:Verdana;font-size:12;fill:Black");
        e.setTextContent("E");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "50");
        e.setAttributeNS(null, "cy", "80");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);
        
        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "80");
        e.setAttributeNS(null, "cy", "80");
        e.setAttributeNS(null, "r", "9");
        e.setAttributeNS(null, "style", "stroke:black;stroke-width:2;fill:white");
        root.appendChild(e);

        return document;
    }

	 public void save(Document document) throws Exception {

	        // Create a JPEGTranscoder and set its quality hint.
	        JPEGTranscoder t = new JPEGTranscoder();
	        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));

	        // Set the transcoder input and output.
	        TranscoderInput input = new TranscoderInput(document);
	        OutputStream ostream = new FileOutputStream("C:\\Amit\\QAG\\out.jpg");
	        TranscoderOutput output = new TranscoderOutput(ostream);

	        // Perform the transcoding.
	        t.transcode(input, output);
	        ostream.flush();
	        ostream.close();
	    }
	 
	 public Questions readXML() throws JAXBException {
		 
	        JAXBContext jc = JAXBContext.newInstance(Questions.class);

	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        Questions questions = (Questions) unmarshaller.unmarshal(new File("C:\\Amit\\QAG\\input.xml"));

	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(questions, System.out);
	        
	        return questions;
	 }
}
