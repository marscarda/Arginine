package arginine;
//****************************************************************************

//****************************************************************************
public class Shield {
    //************************************************************************
    public static String stopInjection(String convert) {
        convert = convert.replaceAll("<", "&lt");
        convert = convert.replaceAll(">", "&gt");
        convert = convert.replaceAll("\\(", "&#40");
        convert = convert.replaceAll("\\)", "&#41");
        convert = convert.replaceAll("'", "&#39");
        convert = convert.replaceAll("\"", "&#34");
        return convert;
    }
    //************************************************************************
}
//****************************************************************************
