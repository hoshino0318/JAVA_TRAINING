package ch24.ex24_01;

import java.util.Locale;
import java.util.ResourceBundle;

class GlobalHello {
  public static void main(String[] args) {
    ResourceBundle res =
        ResourceBundle.getBundle("ch24.ex24_01.GlobalRes");
    ResourceBundle res_en_AU =
        ResourceBundle.getBundle("ch24.ex24_01.GlobalRes", new Locale("en", "AU"));
    ResourceBundle res_en_US =
        ResourceBundle.getBundle("ch24.ex24_01.GlobalRes", new Locale("en", "US"));
    ResourceBundle res_en_US_aa =
        ResourceBundle.getBundle("ch24.ex24_01.GlobalRes", new Locale("en", "US", "aa"));
    String msg, msg_en_AU, msg_en_US, msg_en_US_aa;
    if (args.length > 0) {
      msg = res.getString(GlobalRes.GOODBYE);
      msg_en_AU = res_en_AU.getString(GlobalRes.GOODBYE);
      msg_en_US = res_en_US.getString(GlobalRes.GOODBYE);
      msg_en_US_aa = res_en_US_aa.getString(GlobalRes.GOODBYE);
    } else {
      msg = res.getString(GlobalRes.HELLO);
      msg_en_AU = res_en_AU.getString(GlobalRes.HELLO);
      msg_en_US = res_en_US.getString(GlobalRes.HELLO);
      msg_en_US_aa = res_en_US_aa.getString(GlobalRes.HELLO);
    }
    System.out.println(msg);
    System.out.println(msg_en_AU);
    System.out.println(msg_en_US);
    System.out.println(msg_en_US_aa);
  }
}
