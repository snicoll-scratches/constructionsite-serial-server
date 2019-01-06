package de.djgummikuh.constructionsite.data;

public class ControlDatagram {

    /**
     * Which assembly to address with this datagram.
     */
    String assembly;

    /**
     * which channel in the assembly this datagram is for.
     */
    int channel;

    /**
     * At which speed this datagram should be executed.
     */
    Speed speed;

    /**
     * The amount of movement for this datagram.
     */
    int value;
    /**
     * The delay before this command should be enqueued.
     */
    int delay;

    /**
     * Whether this is a relative movement or an absolute one.
     */
    boolean relative;

    Unit unit;

    public ControlDatagram(String assembly, int channel, Speed speed, int value, Unit unit, boolean relative, int delay) {
        this.assembly = assembly;
        this.channel = channel;
        this.unit = unit;
        this.value = value;
        this.relative = relative;
        this.speed = speed;
        this.delay = delay;
    }

    public ControlDatagram() {
    }

    @Override
    public String toString() {
        return "ControlDatagram{" +
                "assembly='" + assembly + '\'' +
                ", channel=" + channel +
                ", speed=" + speed +
                ", value=" + value +
                ", unit=" + unit +
                ", delay=" + delay +
                ", relative=" + relative +
                '}';
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public boolean isRelative() {
        return relative;
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
