import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Questions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Questions {

	@XmlElement(name = "Question")
	private List<Question> questions;
}
