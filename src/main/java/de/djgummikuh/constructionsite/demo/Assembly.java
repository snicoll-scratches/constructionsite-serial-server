package de.djgummikuh.constructionsite.demo;

import de.djgummikuh.constructionsite.data.ControlDatagram;

public class Assembly {

    private final String identifier;
    private final SerialController serialPort;

    public Assembly(String identifier, String serialPort) {
        this.identifier = identifier;
        this.serialPort = new SerialController(serialPort);
    }

    public void processDatagram(ControlDatagram datagram) {
        // save positions, validate if they are valid, then send the (validated) datagrams to the serial port.
        serialPort.sendDatagram(datagram);
    }

    public String getIdentifier() {
        return identifier;
    }
}
