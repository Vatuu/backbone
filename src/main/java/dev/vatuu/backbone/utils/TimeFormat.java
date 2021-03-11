package dev.vatuu.backbone.utils;

public enum TimeFormat {
    TICKS(1, "%dt"),
    SECONDS(20, "%ds"),
    MINUTES(1200, "%d:%02d");

    final int multiplier;
    final String format;

    TimeFormat(int multiplier, String format) {
        this.multiplier = multiplier;
        this.format = format;
    }

    public long getTicks(long amount) {
        return amount * multiplier;
    }
    public long getFormat(long amount) {
        return amount / multiplier;
    }
    public String getFormattedString(long amount) {
        if(this == MINUTES)
            return String.format(this.format, this.getFormat(amount), SECONDS.getFormat(amount % multiplier));
        return String.format(this.format, this.getFormat(amount));
    }
}