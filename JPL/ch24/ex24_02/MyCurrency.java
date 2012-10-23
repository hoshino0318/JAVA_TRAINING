package ch24.ex24_02;

import java.util.Locale;
import java.util.Currency;

class MyCurrency {
  public static void main(String[] args) {
    Currency[] currencies = {
        Currency.getInstance("CAD"), Currency.getInstance("CNY"),
        Currency.getInstance("DEM"), Currency.getInstance("FRF"),
        Currency.getInstance("JPY"), Currency.getInstance("GBP"),
    };
    Locale[] locales = {
        Locale.CANADA, Locale.CHINA, Locale.FRANCE,
        Locale.GERMANY, Locale.JAPAN, Locale.UK,
    };

    for (int i = 0; i < currencies.length; ++i) {
      for (int j = 0; j < locales.length; ++j) {
        System.out.println(currencies[i] + ": " +  currencies[i].getSymbol(locales[j]));
      }
    }
  }
}
