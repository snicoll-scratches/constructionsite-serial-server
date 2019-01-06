package de.djgummikuh.constructionsite.demo;

import de.djgummikuh.constructionsite.data.ControlDatagram;
import de.djgummikuh.constructionsite.data.ControlDatagramList;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageReceiver {

    private Map<String, Assembly> assemblyMap = new HashMap<>();

    @JmsListener(destination = "control-channel-1", containerFactory = "myFactory")
    public void receive(ControlDatagramList datagram) {
        System.out.println("Received a Datagram List: " + datagram);
        for (ControlDatagram cd : datagram.getDatagrams()) {
            assemblyMap.get(cd.getAssembly()).processDatagram(cd);
        }

    }


    // FIXME: This method should (if possible) be called by spring on startup once for every Assembly configured in a configuration file.
    public void addAssembly(Assembly assembly) {
        assemblyMap.put(assembly.getIdentifier(), assembly);
    }
}
