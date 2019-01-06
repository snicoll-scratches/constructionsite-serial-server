package de.djgummikuh.constructionsite.demo;

import de.djgummikuh.constructionsite.data.ControlDatagram;
import de.djgummikuh.constructionsite.data.ControlDatagramList;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final AssemblyRepository assemblyRepository;

    public MessageReceiver(AssemblyRepository assemblyRepository) {
        this.assemblyRepository = assemblyRepository;
    }

    @JmsListener(destination = "control-channel-1", containerFactory = "myFactory")
    public void receive(ControlDatagramList datagram) {
        System.out.println("Received a Datagram List: " + datagram);
        for (ControlDatagram cd : datagram.getDatagrams()) {
            this.assemblyRepository.findById(cd.getAssembly()).processDatagram(cd);
        }

    }

}
