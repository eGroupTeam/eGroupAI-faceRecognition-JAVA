package com.egroup.util.enums;

/**
 * @author daniel
 * @date 2021年2月5日 下午3:52:07
 * @version
 * @description:
 */
public class DateEnum {
  public enum Formate {
    YMDTHMSSSZ_("yyyy-MM-dd'T'HH:mm:ss.SSSZ"), YMDTHMSZ_("yyyy-MM-dd'T'HH:mm:ssZ"), YMDTHMS_("yyyy-MM-dd'T'HH:mm:ss"), YMDHMS_(
        "yyyy-MM-ddHH:mm:ss"), YMD_SPACE_HMS_("yyyy-MM-dd HH:mm:ss"), YMD_SPACE_H_("yyyy-MM-dd HH"), YMD_SPACE_HM_(
            "yyyy-MM-dd HH:mm"), YMD_SPACE_HMS_S_("yyyy-MM-dd HH:mm:ss.S"), YMD_SPACE_HMS_SS_(
                "yyyy-MM-dd HH:mm:ss.SS"), YMD_SPACE_HMS_SSS_("yyyy-MM-dd HH:mm:ss.SSS"), YMD_("yyyy-MM-dd"), YMD("yyyy/MM/dd"), YYYYMMDD(
                    "yyyyMMdd"), YYYYMMDDHHMMSS("yyyyMMddHHmmss"), HM("HH:mm"), HMS_("HH:mm:ss"), YMDHMS("yyyyMMddHHmmss"), YMDTHMSZ(
                        "yyyyMMdd'T'HHmmssZ"), MINGUO_TEXT("yyy年MM月dd日"), MINGUO_DIGITAL("yyy-MM-dd"), MINGUO_YMD("yyyMMdd");

    private String value;

    Formate(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum Zone {
    TAIPEI("Asia/Taipei"), GMT("GMT");

    private String value;

    Zone(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum Formula {
    PLUS("Plus"), MINUS("Minus");

    private String value;

    Formula(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum Category {
    SECONDS("Seconds"), MINUTES("Minutes"), HOURS("Hours"), DAYS("Days"), MONTHS("Months"), YEARS("Years");

    private String value;

    Category(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum Compare {
    BEFORE("Before"), AFTER("After"), EQUALS("Equals");

    private String value;

    Compare(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum Duration_ {
    NANOS("Nanos"), MILLIS("Millis"), MINUTES("Minutes"), HOURS("Hours"), DAYS("Days");

    private String value;

    Duration_(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public enum Calendar_ {
    ANNO_DOMINI("AnnoDomini"), MINGUO("Minguo");

    private String value;

    Calendar_(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}
