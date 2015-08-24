package market;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import entity.InstrumentHistory;

@XmlRootElement(name="SecurityHeadlines")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsList {
    @XmlElementWrapper(name="Headlines")
    @XmlElement(name="SecurityHeadline")
    private List<HeadLines> news;

}
