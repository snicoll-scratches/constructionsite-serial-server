package de.djgummikuh.constructionsite.demo;


import com.fazecast.jSerialComm.SerialPort;
import de.djgummikuh.constructionsite.config.ChannelIdentifier;
import de.djgummikuh.constructionsite.data.ControlDatagram;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SerialController {

    private static final byte MESSAGE_PREFIX = 0x00; // 0
    private static final byte MESSAGE_SUFFIX = -0x01; // 255
    private static final byte TYPE_POSITION_UPDATE = 0x01;
    private static final byte TYPE_SET_SYNCHRONIZED = 0x02;
    private static final byte TYPE_GET_SYNCHRONIZED = 0x03;

    private final Map<ChannelIdentifier, Integer> positionsMap = new HashMap<>();


    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    /*
     * Datagram format for type TYPE_POSITION_UPDATE: <br>
     *     0 Message prefix (1 byte) <br>
     *     1 Message type (1 byte) <br>
     *     2 Channel Id (1 byte) <br>
     *     3-6 Value (4 bytes) <br>
     *     7 Speed (1 byte) <br>
     *     8 Suffix (1 byte) <br>
     *
     *  Expected Response for Acknowledgement: -0x80 (128)
     *  Expected Response for error: -0x01 (255)
     *
     * Datagram format for type TYPE_GET_SYNCHRONIZED: <br>
     *     Message prefix (1 byte) <br>
     *     Message type (1 byte) <br>
     *     Channel Id (1 byte) <br>
     *     Message Suffix (1 byte) <br>
     *
     *  Expected Response for Channel already synchronized: -0x80 (128)
     *  Expected Response for Channel not yet synchronized: -0x7F (129)
     *  Expected Response for error: -0x01 (255)
     *
     */

    SerialPort port;

    private int readValue = 0x00;

    private boolean initialized = false;

    // FIXME: Ideally one SerialController instance should be generated for every /dev/ttyUSB* interface found. This could be handled with a factory though I do not know how to provide the instances in a way that the Assemblies get the correct instance based on the configuraiton file.
    public SerialController(String portToOpen) {
        List<String> ports = new LinkedList<>();
        for (SerialPort port : SerialPort.getCommPorts()) {
            System.out.println(port.getSystemPortName());
            ports.add(port.getSystemPortName());
        }
        port = SerialPort.getCommPort(portToOpen);
        port.setBaudRate(9600);
        System.out.println("Opening port");
        initialized = port.openPort();
        System.out.println(initialized);

        if (initialized) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(0);
                        if (port.getInputStream().available() > 0) {
                            readValue = port.getInputStream().read();
                            System.out.println("<< " + readValue);
                        }
                    } catch (Exception e) {
                        System.out.println("Exception");
                    }
                }
            }).start();
        }

    }


    public void sendDatagram(ControlDatagram dg) {
        if (!initialized) {
            System.out.println("Not initialized");
            return;
        }
        byte[] command = new byte[9];
        command[0] = MESSAGE_PREFIX;
        command[1] = TYPE_POSITION_UPDATE;
        command[2] = (byte) dg.getChannel();
        command[3] = (byte) (dg.getValue() >> 24);
        command[4] = (byte) (dg.getValue() >> 16);
        command[5] = (byte) (dg.getValue() >> 8);
        command[6] = (byte) dg.getValue();
        command[7] = dg.getSpeed().getSpeedValue();
        command[8] = MESSAGE_SUFFIX;
        ses.schedule(() -> {
            System.out.println(">> " + arrayToString(command));
            write(command);
        }, dg.getDelay(), TimeUnit.MILLISECONDS);
    }

    private synchronized void write(byte[] command) {
        port.writeBytes(command, command.length);
    }

    public static void main(String[] args) {
        System.out.println(-0x7F & 0xFF);
        System.out.println(-0x80 & 0xFF);
        System.out.println(MESSAGE_SUFFIX & 0xFF);
        System.out.println(arrayToString(new byte[]{-1}));

        byte[] command = new byte[4];
        int val = 65536;
        command[0] = (byte) (val >> 24);
        command[1] = (byte) (val >> 16);
        command[2] = (byte) (val >> 8);
        command[3] = (byte) val;
        System.out.println("> " + arrayToString(command) + " <");
    }

    private static String arrayToString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02X ", b & (0xFF)));
        }
        return sb.toString();
    }
}
