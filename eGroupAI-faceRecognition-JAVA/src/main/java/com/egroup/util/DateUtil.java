package com.egroup.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import com.egroup.util.enums.DateEnum.Calendar_;
import com.egroup.util.enums.DateEnum.Category;
import com.egroup.util.enums.DateEnum.Compare;
import com.egroup.util.enums.DateEnum.Duration_;
import com.egroup.util.enums.DateEnum.Formate;
import com.egroup.util.enums.DateEnum.Formula;
import com.egroup.util.enums.DateEnum.Zone;

public class DateUtil {


  // init func
  final static AttributeCheck attributeCheck = new AttributeCheck();

  /**
   * Get Taiwan noe date
   * 
   * @author eGroupAI
   *
   * @param calendar_
   * @param formate
   * @param localDate
   * @return
   */
  public String getMinguo(Calendar_ calendar_, Formate formate, LocalDate localDate) {
    String minguoDateString = null;
    if (calendar_ != null && calendar_.getValue().equals(Calendar_.MINGUO.getValue()) && formate != null && localDate != null) {
      final MinguoDate minguoDate = MinguoDate.from(localDate);
      if (formate.getValue().equals(Formate.MINGUO_TEXT.getValue())) {
        minguoDateString = minguoDate.toString().replace("Minguo ROC ", "").replace("-", "年").replace("-", "月") + "日";
      } else if (formate.getValue().equals(Formate.MINGUO_YMD.getValue())) {
        minguoDateString = minguoDate.toString().replace("Minguo ROC ", "").replace("-", "").replace("-", "");
      } else {
        minguoDateString = minguoDate.toString().replace("Minguo ROC ", "");
      }
    }
    return minguoDateString;
  }

  /**
   * Get date string
   * 
   * @author eGroupAI
   *
   * @param formate
   * @param calendar_
   * @param zone
   * @return
   */
  public String get(Formate formate, Calendar_ calendar_, Zone zone) {
    // Deprecate function name : getString
    String dateString = null;
    if (calendar_ != null && formate != null && zone != null) {
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate.getValue());
      if (calendar_.getValue().equals(Calendar_.ANNO_DOMINI.getValue())) {

        final ZoneId zoneId = ZoneId.of(zone.getValue());
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        dateString = zonedDateTime.format(formatter);
      } else {
        final LocalDate localDate = LocalDate.now();
        dateString = getMinguo(calendar_, formate, localDate);
      }
    }
    return dateString;
  }

  /**
   * Get date string verify has zonetime text "Z" formate or not
   * 
   * @author eGroupAI
   *
   * @param formateInput
   * @param formateOutput
   * @param dateString
   * @param calendar_
   * 
   * @return
   */
  public String convert(Formate formateInput, String dateString, Formate formateOutput, Calendar_ calendar_) {
    // Deprecate function name : getDateStringTransfer_zoneTime and getDateStringTransfer_localDateTime_fromTimestamp
    String newDateString = null;
    if (formateInput != null && formateOutput != null && calendar_ != null && attributeCheck.stringsNotNull(dateString)) {
      // Set DateTimeFormatter
      final DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern(formateInput.getValue());
      final DateTimeFormatter formatterOuptut = DateTimeFormatter.ofPattern(formateOutput.getValue());

      if (isZonedDateTime(calendar_, formateInput)) {
        final ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatterInput);
        newDateString = zonedDateTime.format(formatterOuptut);
      } else if (isMinguo(calendar_, formateInput)) {
        final LocalDate localDate = LocalDate.parse(dateString, formatterInput);
        newDateString = getMinguo(calendar_, formateOutput, localDate);
      } else {
        final LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatterInput);
        newDateString = localDateTime.format(formatterOuptut);
      }
    }
    return newDateString;
  }

  /**
   * Formate the date time endwith .0 , e.g the datetime string the mysql datetime type
   * 
   * @author eGroupAI
   *
   * @param dateString
   * @return
   */
  public String formateSQLDateTime(String dateString) {
    StringBuilder newDateStringBuilder;
    if (attributeCheck.stringsNotNull(dateString) && dateString.length() < 19) {
      dateString = dateString.substring(0, dateString.length() - 2);
      newDateStringBuilder = new StringBuilder();
      int length = 19 - dateString.length();
      for (int i = 1; i < length + 1; i++) {
        if (i % 2 == 1) {
          newDateStringBuilder.append("0");
        } else {
          newDateStringBuilder.append(":0");
        }
      }
      dateString = newDateStringBuilder.toString();
    } else {
      dateString = null;
    }
    return dateString;
  }

  /**
   * Get date string add zone
   * 
   * @author eGroupAI
   *
   * @param formateInput
   * @param formateOutput
   * @param zone
   * @param timestamp
   * @return
   */
  public String getAddZone(Formate formateInput, String dateString, Formate formateOutput, Zone zone) {
    // Deprecate function name : getDateStringTransfer_zoneTime_fromTimestampAddZone
    String newdateString = null;
    if (formateInput != null && formateOutput != null && zone != null && attributeCheck.stringsNotNull(dateString)) {
      final DateTimeFormatter formatterOuptut = DateTimeFormatter.ofPattern(formateOutput.getValue());

      final ZoneId zoneId = ZoneId.of(zone.getValue());
      final DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern(formateInput.getValue());
      final LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatterInput);
      ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
      newdateString = zonedDateTime.format(formatterOuptut);
    }
    return newdateString;
  }

  /**
   * Convert date time from A zone to B zone
   * 
   * @author eGroupAI
   *
   * @param formateInput
   * @param formateOutput
   * @param zone
   * @param timestamp
   * @return
   */
  public String convertZone(Formate formateInput, String dateString, Formate formateOutput, Calendar_ calendar_, Zone zone) {
    // Deprecate function name : getDateString_Transfer_zoneTime_fromTimestampUTCtoTimeZone
    String newDateString = null;
    if (formateInput != null && formateOutput != null && zone != null && calendar_ != null && attributeCheck.stringsNotNull(dateString)
        && isZonedDateTime(calendar_, formateInput)) {
      final DateTimeFormatter formatterOuptut = DateTimeFormatter.ofPattern(formateOutput.getValue());
      final ZoneId zoneId = ZoneId.of(zone.getValue());

      ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString);
      ZonedDateTime finalZonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);
      newDateString = finalZonedDateTime.format(formatterOuptut);
    }
    return newDateString;
  }

  /**
   * Compare two date
   * 
   * @author eGroupAI
   *
   * @param formate
   * @param dateString1
   * @param compare
   * @param dateString2
   * @return
   */
  public boolean compare2Date(Formate formate, String dateString1, Compare compare, String dateString2) {
    // Deprecate function name : getDateString_compare2Date
    boolean flag = false;
    if (formate != null && compare != null && attributeCheck.stringsNotNull(dateString1, dateString2)) {
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate.getValue());
      final LocalDateTime localDateTime1 = LocalDateTime.parse(dateString1, formatter);
      final LocalDateTime localDateTime2 = LocalDateTime.parse(dateString2, formatter);
      switch (compare.getValue()) {
        case "Before":
          flag = localDateTime1.isBefore(localDateTime2);
          break;
        case "After":
          flag = localDateTime1.isAfter(localDateTime2);
          break;
        case "Equals":
          flag = localDateTime1.equals(localDateTime2);
          break;
        default:
          break;
      }
    }
    return flag;
  }

  /**
   * Compare now with date
   * 
   * @author eGroupAI
   *
   * @param formate
   * @param dateString
   * @param compare
   * @param zone
   * @return
   */
  public boolean nowCompareWith(Formate formate, String dateString, Compare compare, Zone zone) {
    boolean flag = false;
    if (formate != null && compare != null && attributeCheck.stringsNotNull(dateString)) {
      final ZoneId zoneId = ZoneId.of(zone.getValue());
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate.getValue());
      final LocalDateTime localDateTime1 = LocalDateTime.now(zoneId);
      final LocalDateTime localDateTime2 = LocalDateTime.parse(dateString, formatter);
      switch (compare.getValue()) {
        case "Before":
          flag = localDateTime1.isBefore(localDateTime2);
          break;
        case "After":
          flag = localDateTime1.isAfter(localDateTime2);
          break;
        case "Equals":
          flag = localDateTime1.equals(localDateTime2);
          break;
        default:
          break;
      }
    }
    return flag;
  }

  /**
   * Calculate the date
   * 
   * @author eGroupAI
   *
   * @param startDateString
   * @param formate
   * @param category
   * @param formula
   * @param digital
   * @param calendar_
   * @param zone
   * @return
   */
  public String calculate(String startDateString, Formate formate, Category category, Formula formula, Long digital, Calendar_ calendar_, Zone zone) {
    String dateString = null;
    if (formate != null && category != null && formula != null && digital != null && calendar_ != null
        && attributeCheck.stringsNotNull(startDateString)) {
      final ZoneId zoneId = ZoneId.of(zone.getValue());
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate.getValue());
      final LocalDateTime localDateTime = LocalDateTime.parse(startDateString, formatter);
      final ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
      boolean isLocalDateTime = isLocalDateTime(calendar_, formate);

      switch (category.getValue()) {
        case "Seconds":
          dateString = caculateSeconds(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
          break;
        case "Minutes":
          dateString = caculateMinutes(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
          break;
        case "Hours":
          dateString = caculateHours(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
          break;
        case "Days":
          dateString = caculateDays(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
          break;
        case "Months":
          dateString = caculateMonths(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
          break;
        case "Years":
          dateString = caculateYears(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
          break;

        default:
          break;
      }

      if (!calendar_.getValue().equals(Calendar_.ANNO_DOMINI.getValue())) {
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        dateString = getMinguo(calendar_, formate, localDate);
      }
    }
    return dateString;
  }

  /**
   * Calculate the date from now
   * 
   * @author eGroupAI
   *
   * @param formate
   * @param category
   * @param formula
   * @param digital
   * @param calendar_
   * @param zone
   * @return
   */
  public String calculate(Formate formate, Category category, Formula formula, Long digital, Calendar_ calendar_, Zone zone) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate.getValue());
    final ZoneId zoneId = ZoneId.of(zone.getValue());
    final LocalDateTime localDateTime = LocalDateTime.now(zoneId);
    final ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
    boolean isLocalDateTime = isLocalDateTime(calendar_, formate);
    String dateString = "";

    switch (category.getValue()) {
      case "Seconds":
        dateString = caculateSeconds(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
        break;
      case "Minutes":
        dateString = caculateMinutes(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
        break;
      case "Hours":
        dateString = caculateHours(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
        break;
      case "Days":
        dateString = caculateDays(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
        break;
      case "Months":
        dateString = caculateMonths(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
        break;
      case "Years":
        dateString = caculateYears(localDateTime, zonedDateTime, formatter, formula, digital, isLocalDateTime, zone);
        break;

      default:
        break;
    }

    if (!calendar_.getValue().equals(Calendar_.ANNO_DOMINI.getValue())) {
      formatter = DateTimeFormatter.ofPattern(Formate.YMD_.getValue());
      LocalDate localDate = LocalDate.parse(dateString, formatter);
      dateString = getMinguo(calendar_, formate, localDate);
    }
    return dateString;
  }

  /**
   * Check whether the date string is zone date time
   * 
   * @author eGroupAI
   *
   * @param calendar_
   * @param formateInput
   * @return
   */
  private boolean isZonedDateTime(Calendar_ calendar_, Formate formateInput) {
    boolean flag = false;
    if (calendar_.getValue().equals(Calendar_.ANNO_DOMINI.getValue()) && (formateInput.getValue().equals(Formate.YMDTHMSZ_.getValue())
        || formateInput.getValue().equals(Formate.YMDTHMSSSZ_.getValue()) || formateInput.getValue().equals(Formate.YMDTHMSZ.getValue()))) {
      flag = true;
    }
    return flag;
  }

  /**
   * Check whether the date string is minguo date time
   * 
   * @author eGroupAI
   *
   * @param calendar_
   * @param formateInput
   * @return
   */
  private boolean isMinguo(Calendar_ calendar_, Formate formateInput) {
    boolean flag = false;
    if (calendar_.getValue().equals(Calendar_.MINGUO.getValue()) && (formateInput.getValue().equals(Formate.MINGUO_DIGITAL.getValue())
        || formateInput.getValue().equals(Formate.MINGUO_YMD.getValue()) || formateInput.getValue().equals(Formate.MINGUO_TEXT.getValue()))) {
      flag = true;
    }
    return flag;
  }

  /**
   * Check whether the date string is local date time
   * 
   * @author eGroupAI
   *
   * @param calendar_
   * @param formateInput
   * @return
   */
  private boolean isLocalDateTime(Calendar_ calendar_, Formate formateInput) {
    boolean flag = false;
    if (!isZonedDateTime(calendar_, formateInput) && !isMinguo(calendar_, formateInput)) {
      flag = true;
    }
    return flag;
  }

  /**
   * Calculate date time secondes
   * 
   * @author eGroupAI
   *
   * @param localDateTime
   * @param zonedDateTime
   * @param formula
   * @param formate
   * @param digital
   * @param isLocalDateTime
   * @param zone
   * @return
   */
  private String caculateSeconds(LocalDateTime localDateTime, ZonedDateTime zonedDateTime, DateTimeFormatter formatter, Formula formula, Long digital,
      boolean isLocalDateTime, Zone zone) {
    String dateString = null;
    if (localDateTime != null && formula != null && formatter != null && digital != null && zone != null) {
      if (isLocalDateTime) {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? localDateTime.plusSeconds(digital).format(formatter)
            : localDateTime.minusSeconds(digital).format(formatter);
      } else {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? zonedDateTime.plusSeconds(digital).format(formatter)
            : zonedDateTime.minusSeconds(digital).format(formatter);
      }
    }
    return dateString;
  }

  /**
   * Calculate date time minutes
   * 
   * @author eGroupAI
   *
   * @param localDateTime
   * @param zonedDateTime
   * @param formula
   * @param formate
   * @param digital
   * @param isLocalDateTime
   * @param zone
   * @return
   */
  private String caculateMinutes(LocalDateTime localDateTime, ZonedDateTime zonedDateTime, DateTimeFormatter formatter, Formula formula, Long digital,
      boolean isLocalDateTime, Zone zone) {
    String dateString = null;
    if (localDateTime != null && formula != null && formatter != null && digital != null && zone != null) {
      if (isLocalDateTime) {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? localDateTime.plusMinutes(digital).format(formatter)
            : localDateTime.minusMinutes(digital).format(formatter);
      } else {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? zonedDateTime.plusMinutes(digital).format(formatter)
            : zonedDateTime.minusMinutes(digital).format(formatter);
      }
    }
    return dateString;
  }

  /**
   * Calculate date time hours
   * 
   * @author eGroupAI
   *
   * @param localDateTime
   * @param zonedDateTime
   * @param formula
   * @param formate
   * @param digital
   * @param isLocalDateTime
   * @param zone
   * @return
   */
  private String caculateHours(LocalDateTime localDateTime, ZonedDateTime zonedDateTime, DateTimeFormatter formatter, Formula formula, Long digital,
      boolean isLocalDateTime, Zone zone) {
    String dateString = null;
    if (localDateTime != null && formula != null && formatter != null && digital != null && zone != null) {
      if (isLocalDateTime) {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? localDateTime.plusHours(digital).format(formatter)
            : localDateTime.minusHours(digital).format(formatter);
      } else {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? zonedDateTime.plusHours(digital).format(formatter)
            : zonedDateTime.minusHours(digital).format(formatter);
      }
    }
    return dateString;
  }

  /**
   * Calculate date time days
   * 
   * @author eGroupAI
   *
   * @param localDateTime
   * @param zonedDateTime
   * @param formula
   * @param formate
   * @param digital
   * @param isLocalDateTime
   * @param zone
   * @return
   */
  private String caculateDays(LocalDateTime localDateTime, ZonedDateTime zonedDateTime, DateTimeFormatter formatter, Formula formula, Long digital,
      boolean isLocalDateTime, Zone zone) {
    String dateString = null;
    if (localDateTime != null && formula != null && formatter != null && digital != null && zone != null) {
      if (isLocalDateTime) {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? localDateTime.plusDays(digital).format(formatter)
            : localDateTime.minusDays(digital).format(formatter);
      } else {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? zonedDateTime.plusDays(digital).format(formatter)
            : zonedDateTime.minusDays(digital).format(formatter);
      }
    }
    return dateString;
  }

  /**
   * Calculate date time month
   * 
   * @author eGroupAI
   *
   * @param localDateTime
   * @param zonedDateTime
   * @param formula
   * @param formate
   * @param digital
   * @param isLocalDateTime
   * @param zone
   * @return
   */
  private String caculateMonths(LocalDateTime localDateTime, ZonedDateTime zonedDateTime, DateTimeFormatter formatter, Formula formula, Long digital,
      boolean isLocalDateTime, Zone zone) {
    String dateString = null;
    if (localDateTime != null && formula != null && formatter != null && digital != null && zone != null) {
      if (isLocalDateTime) {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? localDateTime.plusMonths(digital).format(formatter)
            : localDateTime.minusMonths(digital).format(formatter);
      } else {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? zonedDateTime.plusMonths(digital).format(formatter)
            : zonedDateTime.minusMonths(digital).format(formatter);
      }
    }
    return dateString;
  }

  /**
   * Calculate date time month
   * 
   * @author eGroupAI
   *
   * @param localDateTime
   * @param zonedDateTime
   * @param formula
   * @param formate
   * @param digital
   * @param isLocalDateTime
   * @param zone
   * @return
   */
  private String caculateYears(LocalDateTime localDateTime, ZonedDateTime zonedDateTime, DateTimeFormatter formatter, Formula formula, Long digital,
      boolean isLocalDateTime, Zone zone) {
    String dateString = null;
    if (localDateTime != null && formula != null && formatter != null && digital != null && zone != null) {
      if (isLocalDateTime) {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? localDateTime.plusYears(digital).format(formatter)
            : localDateTime.minusYears(digital).format(formatter);
      } else {
        dateString = formula.getValue().equals(Formula.PLUS.getValue()) ? zonedDateTime.plusYears(digital).format(formatter)
            : zonedDateTime.minusYears(digital).format(formatter);
      }
    }
    return dateString;
  }

  /**
   * 
   * @author eGroupAI Team Get the duration by time
   * @param startTimeString
   * @param endTimeString
   * @param formate
   * @param duration_
   * @return
   */
  public Integer getDuration(String startTimeString, String endTimeString, Formate formate, Duration_ duration_) {
    int count = 0;
    if (attributeCheck.stringsNotNull(startTimeString, endTimeString)) {
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate.getValue());
      LocalDateTime localDateStartTime;
      LocalDateTime localDateEndTime;
      if (formate.getValue().equals(Formate.YMD.getValue()) || formate.getValue().equals(Formate.YMD_.getValue())) {
        LocalDate localDateStartDate = LocalDate.parse(startTimeString, formatter);
        LocalDate localDateEndDate = LocalDate.parse(endTimeString, formatter);
        localDateStartTime = localDateStartDate.atStartOfDay();
        localDateEndTime = localDateEndDate.atStartOfDay();
      } else {
        localDateStartTime = LocalDateTime.parse(startTimeString, formatter);
        localDateEndTime = LocalDateTime.parse(endTimeString, formatter);
      }
      switch (duration_.getValue()) {
        case "Nanos":
          count = (int) java.time.Duration.between(localDateStartTime, localDateEndTime).toNanos();
          break;
        case "Millis":
          count = (int) java.time.Duration.between(localDateStartTime, localDateEndTime).toMillis();
          break;
        case "Minutes":
          count = (int) java.time.Duration.between(localDateStartTime, localDateEndTime).toMinutes();
          break;
        case "Hours":
          count = (int) java.time.Duration.between(localDateStartTime, localDateEndTime).toHours();
          break;
        case "Days":
          count = (int) java.time.Duration.between(localDateStartTime, localDateEndTime).toDays();
          break;
        default:
          break;
      }
    }
    return count;
  }

  /**
   * Convert date to string
   * 
   * @author eGroupAI
   *
   * @param formate
   * @param date
   * @return
   */
  public String toString(Formate formate, Date date) {
    String dateString = null;
    if (date != null) {
      final SimpleDateFormat formatter = new SimpleDateFormat(formate.getValue());
      dateString = formatter.format(date);
    }
    return dateString;
  }

  /**
   * Check date string formate
   * 
   * @author eGroupAI
   *
   * @param dateString
   * @param formate
   * @return
   */
  public boolean checkFormat(String dateString, Formate formate) {
    boolean flag = true;
    if (formate != null && attributeCheck.stringsNotNull(dateString)) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate.getValue());
      simpleDateFormat.setLenient(false);
      try {
        // if not valid, it will throw ParseException
        simpleDateFormat.parse(dateString);
      } catch (ParseException e) {
        flag = false;
      }
    } else {
      flag = false;
    }
    return flag;
  }

  /**
   * Get year and month long time
   * 
   * @author eGroupAI
   *
   * @param date
   * @return
   */
  public int getYearMonthLong(Date date) {
    int count = 0;
    if (date != null) {
      Calendar calder = Calendar.getInstance();
      calder.setTime(date);
      int year = calder.get(Calendar.YEAR);
      int month = calder.get(Calendar.MONTH);
      count = year * 100 + month;
    }
    return count;
  }
}
