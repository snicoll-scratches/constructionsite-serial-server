package de.djgummikuh.constructionsite.data;

public enum Speed {
    VERY_SLOW((byte)5),SLOW((byte)4),NORMAL((byte)3),FAST((byte)2),VERY_FAST((byte)1);

    private final byte speedValue;

    Speed(byte speedValue) {
        this.speedValue = speedValue;
    }

    public byte getSpeedValue() {
        return speedValue;
    }
}
