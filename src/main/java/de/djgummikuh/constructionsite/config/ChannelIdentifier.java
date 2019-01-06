package de.djgummikuh.constructionsite.config;

import java.util.Objects;

public class ChannelIdentifier {

    private String assembly;
    private int channel;
    private String speakingName;

    public ChannelIdentifier(String assembly, int channel, String speakingName) {
        this.assembly = assembly;
        this.channel = channel;
        this.speakingName = speakingName;
    }

    public ChannelIdentifier() {
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getSpeakingName() {
        return speakingName;
    }

    public void setSpeakingName(String speakingName) {
        this.speakingName = speakingName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelIdentifier that = (ChannelIdentifier) o;
        return channel == that.channel &&
                assembly.equals(that.assembly) &&
                Objects.equals(speakingName, that.speakingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assembly, channel, speakingName);
    }
}
