package de.djgummikuh.constructionsite.data;

import java.util.List;

public class ControlDatagramList {

    /** a list of datagrams. required so jms does the deserialization correctly. */
    List<ControlDatagram> datagrams;

    public List<ControlDatagram> getDatagrams() {
        return datagrams;
    }

    public void setDatagrams(List<ControlDatagram> datagrams) {
        this.datagrams = datagrams;
    }

    @Override
    public String toString() {
        return "ControlDatagramlist{" +
                "datagrams=" + datagrams +
                '}';
    }

    public ControlDatagramList(List<ControlDatagram> datagrams) {
        this.datagrams = datagrams;
    }

    public ControlDatagramList() {
    }
}
