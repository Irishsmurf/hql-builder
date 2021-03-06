package org.tools.hqlbuilder.common.jaxb;

import javax.xml.bind.annotation.XmlElement;

import org.tools.hqlbuilder.common.XmlWrapper;

public class MapEntry {
    @XmlElement(name = "key", required = false)
    XmlWrapper<?> key;

    @XmlElement(name = "val", required = false)
    XmlWrapper<?> value;

    private MapEntry() {
        super();
    }

    public MapEntry(Object key, Object val) {
        this();

        this.key = new XmlWrapper<Object>(key);
        this.value = new XmlWrapper<Object>(val);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
