package dev.vatuu.backbone.hud;

public final class UnicodeHUD {

    public static final char SPACE_1PX = '\uF821';
    public static final char SPACE_2PX = '\uF822';
    public static final char SPACE_5PX = '\uF825';
    public static final char BACK_1PX = '\uF801';

    public enum Numbers {
        ZERO('\uF000', '\uF010'),
        ONE('\uF001', '\uF011'),
        TWO('\uF002', '\uF012'),
        THREE('\uF003', '\uF013'),
        FOUR('\uF004', '\uF014'),
        FIVE('\uF005', '\uF015'),
        SIX('\uF006', '\uF016'),
        SEVEN('\uF007', '\uF017'),
        EIGHT('\uF008', '\uF018'),
        NINE('\uF009', '\uF019');

        private final char unicode, inflatedUnicode;

        Numbers(char unicode, char inflatedUnicode) {
            this.unicode = unicode;
            this.inflatedUnicode = inflatedUnicode;
        }

        public char get() {
            return unicode;
        }

        public char getInflated() {
            return inflatedUnicode;
        }

        public static char get(int number, boolean inflated) {
            if(number < 0)
                return inflated ? Numbers.ZERO.getInflated() : Numbers.ZERO.get();
            if(number > 9)
                return inflated ? Numbers.NINE.getInflated() : Numbers.NINE.get();

            return inflated ? Numbers.values()[number].getInflated() : Numbers.values()[number].get();
        }
    }
}
