package com.softserve.edu.rest.data;

public final class CoolDownRepository {

    public static final String DEFAULT_COOLDOWN_TIME = "180000";
    public static final String LONG_COOLDOWN_TIME = "360000";
    public static final String SHORT_COOLDOWN_TIME = "1";
    public static final String NEGATIVE_COOLDOWN_TIME = "-999999";
    public static final String SQL_INJECTION_COOLDOWN_TIME = "SELECT * FROM Users WHERE Username='$username' AND Password='$password'";
    public static final String ZERO_COOLDOWN_TIME = "0";
    public static final String EMPTY_COOLDOWN_TIME = "";
    public static final String LONGEST_COOLDOWN_TIME = "9999999999999";
    public static final String UNICODE_COOLDOWN_TIME = "ταБЬℓσ";
    public static final String SPECIALCHARS_COOLDOWN_TIME = "\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02";
    public static final String SHORT_LOREM_COOLDOWN_TIME = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec elit lacus, placerat ac erat sed, maximus convallis lacus. " +
            "Integer eget ultrices felis. Aliquam mattis, nisl at laoreet consectetur, leo tellus ornare libero, non tincidunt leo magna vel dolor. Phasellus ac egestas libero, eu elementum enim. Vestibulum eu lorem dolor. Phasellus in convallis tortor. Integer malesuada nunc non sapien maximus facilisis. Fusce tincidunt, erat quis fringilla vestibulum, diam arcu laoreet felis, ultrices vestibulum sapien metus in libero. " +
            "Aenean id sem at ex elementum dignissim at sit amet enim. Duis sagittis velit in vulputate sodales. Nam consectetur diam metus, ac laoreet massa suscipit in. " +
            "Sed ultricies tincidunt diam tempor tempus. Duis mattis nulla at molestie malesuada. Fusce et neque sit amet erat maximus ornare ac eu ex. " +
            "Fusce neque mauris, commodo nec elit at, ornare maximus quam. Quisque sit amet tempor sapien.";
    public static final String LONG_LOREM_COOLDOWN_TIME ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec elit lacus, placerat ac erat sed, maximus convallis lacus. Integer eget ultrices felis. Aliquam mattis, nisl at laoreet consectetur, leo tellus ornare libero, non tincidunt leo magna vel dolor. Phasellus ac egestas libero, eu elementum enim. Vestibulum eu lorem dolor. " +
            "Phasellus in convallis tortor. Integer malesuada nunc non sapien maximus facilisis. Fusce tincidunt, erat quis fringilla vestibulum, diam arcu laoreet felis, ultrices vestibulum sapien metus in libero. Aenean id sem at ex elementum dignissim at sit amet enim. Duis sagittis velit in vulputate sodales. Nam consectetur diam metus, ac laoreet massa suscipit in. Sed ultricies tincidunt diam tempor tempus. " +
            "Duis mattis nulla at molestie malesuada.Fusce et neque sit amet erat maximus ornare ac eu ex. Fusce neque mauris, commodo nec elit at, ornare maximus quam. Quisque sit amet tempor sapien Nunc tempus eu tortor sed molestie. Vestibulum eu diam eget elit sollicitudin imperdiet. Vivamus ac ultricies magna. " +
            "Ut eget fringilla mi. Donec ullamcorper nulla nisi, quis faucibus eros lobortis id. Praesent vulputate efficitur nibh. Praesent leo eros, semper bibendum leo sed, placerat sodales enim. Sed a nisl tempus, viverra neque et, viverra eros. Curabitur vitae tristique magna. Vestibulum venenatis, magna ut sollicitudin aliquam, orci risus egestas leo, ut ultricies leo velit ac quam. In hac habitasse platea dictumst. Curabitur nibh purus, volutpat eu consequat at, pretium et magna. Proin in turpis luctus, tristique nunc et, mollis diam. In efficitur feugiat laoreet. Proin nec erat faucibus, interdum urna ac, lobortis ante. Vestibulum luctus vehicula purus ornare vulputate. Ut tincidunt urna vel dolor sodales, sed convallis mauris elementum. Cras vitae velit ornare, dictum urna sit amet, gravida dui. Curabitur nulla eros, fermentum sed dolor non, sollicitudin porta lectus. Duis nec suscipit dui, vitae blandit orci. Donec tincidunt mi lectus, scelerisque iaculis arcu venenatis id.In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus. Suspendisse iaculis suscipit dui et faucibus. Curabitur ut lorem mollis libero suscipit consequat. Maecenas nec pretium magna, nec vestibulum diam. Sed mollis ipsum sit amet justo volutpat, vitae rutrum augue interdum. Nam ac sem efficitur, vestibulum tortor vulputate, posuere justo. Vivamus commodo egestas turpis sed consequat. Vivamus tempus nibh quis ante fringilla mollis dignissim ac augue. Praesent felis libero, scelerisque non magna non, iaculis bibendum lorem. Mauris suscipit lacinia bibendum. Integer efficitur risus sapien, id tristique massa tincidunt id. Sed sit amet ullamcorper nisl. Nullam lacus dui, euismod id dui scelerisque, ornare rhoncus nulla.";



    private CoolDownRepository() {
    }

    public static Cooldown getDefault() {
        return new Cooldown(DEFAULT_COOLDOWN_TIME);
    }

    public static Cooldown GetLongTime() {
        return new Cooldown(LONG_COOLDOWN_TIME);
    }

    public static Cooldown GetShortTime() {
        return new Cooldown(SHORT_COOLDOWN_TIME);
    }

    public static Cooldown GetNegativeTime() {
        return new Cooldown(NEGATIVE_COOLDOWN_TIME);
    }

    public static Cooldown GetSQLITime() {
        return new Cooldown(SQL_INJECTION_COOLDOWN_TIME);
    }

    public static Cooldown GetZeroTime() {
        return new Cooldown(ZERO_COOLDOWN_TIME);
    }

    public static Cooldown GetLongestTime() {
        return new Cooldown(LONGEST_COOLDOWN_TIME);
    }

    public static Cooldown GetEmptyTime() {
        return new Cooldown(EMPTY_COOLDOWN_TIME);
    }

    public static Cooldown GetUnicodeTime() {
        return new Cooldown(UNICODE_COOLDOWN_TIME);
    }

    public static Cooldown GetSmileyTime() {
        return new Cooldown(SPECIALCHARS_COOLDOWN_TIME);
    }

    public static Cooldown GetSLoremTime() {
        return new Cooldown(SHORT_LOREM_COOLDOWN_TIME);
    }

    public static Cooldown GetLLoremTime() {
        return new Cooldown(LONG_LOREM_COOLDOWN_TIME);
    }



}
