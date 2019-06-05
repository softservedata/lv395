package com.softserve.edu.rest.data;

public final class PasswordRepository {

    public static final String DEFAULT_PASSWORD = "qwerty";
    public static final String LONG_NUMERIC_PASSWORD = "4083050945415395";
    public static final String LONG_CHAR_PASSWORD_SPACELESS = "FourZeroEightThreeZeroFiveZeroNineFourFiveFourOneFiveThreeNineFive";
    public static final String SHORT_NUMERIC_PASSWORD = "381508";
    public static final String SHORT_CHAR_PASSWORD_SPACELESS = "ThreeEightOneFiveZeroEight";
    public static final String LONG_SYMBOLIC_RANDOM = "%&}#:{:\"}/>\\'}:@";
    public static final String STRONG_RANDOM_PASSWORD_1 = "?D??:[_fnxqQol|T";
    public static final String STRONG_RANDOM_PASSWORD_2 = "f8I>_0&$ls%{_RDx";
    public static final String STRONG_RANDOM_PASSWORD_3 = "2`'#bHZCVJ1Yt-!x";
    public static final String STRONG_RANDOM_PASSWORD_4 = "~XR\\G)*|zPaF6WMD";
    public static final String STRONG_RANDOM_PASSWORD_5 = "_KVD>76Qpi0i2mJo";
    public static final String SQL_INJECTION_PASSWORD = "SELECT * FROM Users WHERE Username='$username' AND Password='$password'";
    public static final String EMPTY_PASSWORD = "";
    public static final String LONGEST_PASSWORD = "EyA6wPS8wRedEBKxHuDcmSa3fFrmYtIrQ6laL387Ia8dzGy7017aJ63NziXoAvFgpQeYIaTg8RhynFtbuob4k2FKasWALpEY5IMBGtIFaP3llkkFylxHb0fbg1CPg4D9";
    public static final String UNICODE_PASSWORD = "ταБЬℓσ";
    public static final String SPECIALCHARS_PASSWORD = "\uD83D\uDE02\uD83D\uDE02\uD83D\uDE02";
    public static final String SHORT_LOREM_PASSWORD = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec elit lacus, placerat ac erat sed, maximus convallis lacus. " +
            "Integer eget ultrices felis. Aliquam mattis, nisl at laoreet consectetur, leo tellus ornare libero, non tincidunt leo magna vel dolor. Phasellus ac egestas libero, eu elementum enim. Vestibulum eu lorem dolor. Phasellus in convallis tortor. Integer malesuada nunc non sapien maximus facilisis. Fusce tincidunt, erat quis fringilla vestibulum, diam arcu laoreet felis, ultrices vestibulum sapien metus in libero. " +
            "Aenean id sem at ex elementum dignissim at sit amet enim. Duis sagittis velit in vulputate sodales. Nam consectetur diam metus, ac laoreet massa suscipit in. " +
            "Sed ultricies tincidunt diam tempor tempus. Duis mattis nulla at molestie malesuada. Fusce et neque sit amet erat maximus ornare ac eu ex. " +
            "Fusce neque mauris, commodo nec elit at, ornare maximus quam. Quisque sit amet tempor sapien.";
    public static final String LONG_LOREM_PASSWORD = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec elit lacus, placerat ac erat sed, maximus convallis lacus. Integer eget ultrices felis. Aliquam mattis, nisl at laoreet consectetur, leo tellus ornare libero, non tincidunt leo magna vel dolor. Phasellus ac egestas libero, eu elementum enim. Vestibulum eu lorem dolor. " +
            "Phasellus in convallis tortor. Integer malesuada nunc non sapien maximus facilisis. Fusce tincidunt, erat quis fringilla vestibulum, diam arcu laoreet felis, ultrices vestibulum sapien metus in libero. Aenean id sem at ex elementum dignissim at sit amet enim. Duis sagittis velit in vulputate sodales. Nam consectetur diam metus, ac laoreet massa suscipit in. Sed ultricies tincidunt diam tempor tempus. " +
            "Duis mattis nulla at molestie malesuada.Fusce et neque sit amet erat maximus ornare ac eu ex. Fusce neque mauris, commodo nec elit at, ornare maximus quam. Quisque sit amet tempor sapien Nunc tempus eu tortor sed molestie. Vestibulum eu diam eget elit sollicitudin imperdiet. Vivamus ac ultricies magna. " +
            "Ut eget fringilla mi. Donec ullamcorper nulla nisi, quis faucibus eros lobortis id. Praesent vulputate efficitur nibh. Praesent leo eros, semper bibendum leo sed, placerat sodales enim. Sed a nisl tempus, viverra neque et, viverra eros. Curabitur vitae tristique magna. Vestibulum venenatis, magna ut sollicitudin aliquam, orci risus egestas leo, ut ultricies leo velit ac quam. In hac habitasse platea dictumst. Curabitur nibh purus, volutpat eu consequat at, pretium et magna. Proin in turpis luctus, tristique nunc et, mollis diam. In efficitur feugiat laoreet. Proin nec erat faucibus, interdum urna ac, lobortis ante. Vestibulum luctus vehicula purus ornare vulputate. Ut tincidunt urna vel dolor sodales, sed convallis mauris elementum. Cras vitae velit ornare, dictum urna sit amet, gravida dui. Curabitur nulla eros, fermentum sed dolor non, sollicitudin porta lectus. Duis nec suscipit dui, vitae blandit orci. Donec tincidunt mi lectus, scelerisque iaculis arcu venenatis id.In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus. Suspendisse iaculis suscipit dui et faucibus. Curabitur ut lorem mollis libero suscipit consequat. Maecenas nec pretium magna, nec vestibulum diam. Sed mollis ipsum sit amet justo volutpat, vitae rutrum augue interdum. Nam ac sem efficitur, vestibulum tortor vulputate, posuere justo. Vivamus commodo egestas turpis sed consequat. Vivamus tempus nibh quis ante fringilla mollis dignissim ac augue. Praesent felis libero, scelerisque non magna non, iaculis bibendum lorem. Mauris suscipit lacinia bibendum. Integer efficitur risus sapien, id tristique massa tincidunt id. Sed sit amet ullamcorper nisl. Nullam lacus dui, euismod id dui scelerisque, ornare rhoncus nulla.";


    private PasswordRepository() {
    }

    public static Password getDefault() {
        return new Password(DEFAULT_PASSWORD);
    }

    public static Password GetLongNumPass() {
        return new Password(LONG_NUMERIC_PASSWORD);
    }

    public static Password GetLongCharPass() {
        return new Password(LONG_CHAR_PASSWORD_SPACELESS);
    }

    public static Password GetShortNumPass() {
        return new Password(SHORT_NUMERIC_PASSWORD);
    }

    public static Password GetShortCharPass() {
        return new Password(SHORT_CHAR_PASSWORD_SPACELESS);
    }

    public static Password GetLongSymbPass() {
        return new Password(LONG_SYMBOLIC_RANDOM);
    }

    public static Password GetAwesomePass1() {
        return new Password(STRONG_RANDOM_PASSWORD_1);
    }

    public static Password GetAwesomePass2() {
        return new Password(STRONG_RANDOM_PASSWORD_2);
    }

    public static Password GetAwesomePass3() {
        return new Password(STRONG_RANDOM_PASSWORD_3);
    }

    public static Password GetAwesomePass4() {
        return new Password(STRONG_RANDOM_PASSWORD_4);
    }

    public static Password GetAwesomePass5() {
        return new Password(STRONG_RANDOM_PASSWORD_5);
    }

    public static Password GetSQLIPass() {
        return new Password(SQL_INJECTION_PASSWORD);
    }

    public static Password GetEmptyPass() {
        return new Password(EMPTY_PASSWORD);
    }

    public static Password GetLongestPass() {
        return new Password(LONGEST_PASSWORD);
    }

    public static Password GetUNIPASS() {
        return new Password(UNICODE_PASSWORD);
    }

    public static Password GetSmileyPass() {
        return new Password(SPECIALCHARS_PASSWORD);
    }

    public static Password GetSLoremPass() {
        return new Password(SHORT_LOREM_PASSWORD);
    }

    public static Password GetLLoremPass() {
        return new Password(LONG_LOREM_PASSWORD);
    }

}
