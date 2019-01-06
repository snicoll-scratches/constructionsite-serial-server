package de.djgummikuh.constructionsite.demo;

import de.djgummikuh.constructionsite.data.ControlDatagram;

public class Assembly {


    private String identifier;
    private SerialController serialPort;



    // FIXME Should be wired by Spring based on the configuration mapping (arm1 -> USB1, arm2 -> USB0, arm3 -> USB1)
    public void setSerialPort(SerialController controller) {
        this.serialPort = controller;
    }

    // FIXME Should also come from the configuration (I added it only so that I can create the mapping in the MessageReceiver) (arm1, arm2, arm3)
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void processDatagram(ControlDatagram datagram) {
        // save positions, validate if they are valid, then send the (validated) datagrams to the serial port.
            serialPort.sendDatagram(datagram);
    }


    public String getIdentifier() {
        return identifier;
    }
}
