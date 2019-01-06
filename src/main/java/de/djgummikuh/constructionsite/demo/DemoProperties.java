package de.djgummikuh.constructionsite.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stephane Nicoll
 */
@ConfigurationProperties("demo")
public class DemoProperties {

    private final Map<String, AssemblyConfiguration> robots = new HashMap<>();

    public Map<String, AssemblyConfiguration> getRobots() {
        return this.robots;
    }

    static class AssemblyConfiguration {

        private String serialPort;

        public String getSerialPort() {
            return this.serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

    }
}
