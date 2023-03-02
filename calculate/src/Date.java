import java.awt.*;

public class Date {
    private RestDay restDay = new RestDay(1,28);



    private double center = 1.5;
    /**
     * start_year 开始的年份
     * start_month 开始的月份
     * start_day 开始的日子
     * start_hour 开始的小时
     * start_min 开始的分钟
     * */
    private int start_year;
    private int start_month;
    private int start_day;
    private int start_hour;
    private int start_min;

    /**
     * end_year 截止的年份
     * end_month 截至的月份
     * end_day 截至的日子
     * end_hour 截至的小时
     * end_min 截至的分钟
     * */
    private int end_year;
    private int end_month;
    private int end_day;
    private int end_hour;
    private int end_min;

    public Date(String start_date, String end_date) {
        readStartDate(start_date);
        readEndDate(end_date);
        setRestDay();
    }

    public void setRestDay() {
        restDay.setRestDay(1,1);
        restDay.setRestDay(1,2);
        restDay.setRestDay(1,3);
        restDay.setRestDay(1,31);
        restDay.setRestDay(2,1);
        restDay.setRestDay(2,2);
        restDay.setRestDay(2,3);
        restDay.setRestDay(2,4);
        restDay.setRestDay(2,5);
        restDay.setRestDay(2,6);
        restDay.setRestDay(4,3);
        restDay.setRestDay(4,4);
        restDay.setRestDay(4,5);
        restDay.setRestDay(4,30);
        restDay.setRestDay(5,1);
        restDay.setRestDay(5,2);
        restDay.setRestDay(5,3);
        restDay.setRestDay(5,4);
        restDay.setRestDay(6,3);
        restDay.setRestDay(6,4);
        restDay.setRestDay(6,5);
        restDay.setRestDay(9,10);
        restDay.setRestDay(9,11);
        restDay.setRestDay(9,12);
        restDay.setRestDay(10,1);
        restDay.setRestDay(10,2);
        restDay.setRestDay(10,3);
        restDay.setRestDay(10,4);
        restDay.setRestDay(10,5);
        restDay.setRestDay(10,6);
        restDay.setRestDay(10,7);
    }

    /**
     * 通过开始时间和截至时间计算出停车所需的费用
     * */
    public double getPayment() {
        double price= 0;
        boolean restDay = false;
        if (isLessThanAnyMin(30)) {
            return 0;
        }
        while (isSameDay() != true) {
            restDay = false;
            if (isRestDay(start_month,start_day)) {
                restDay = true;
            }
            if (isNight(start_hour,start_min) != true) {
                int time = (17 * 60 + 30) - (start_hour * 60 + start_min); //到晚上为止的分钟数减去开始的分钟数
                int count = time / 30;
                if (time % 30 != 0) {
                    count += 1;
                }
                if (restDay == true) {
                    price += count * center > 15 ? 15 : count * center;
                }
                else {
                    price += count * center > 24 ? 24 : count * center;
                }
            }
            price += 5; //5为中心区夜间费用
            start_day += 1;
            start_hour = 8;
            start_min = 0;
            autoTimeFix();
        }
        if (isLessThanAnyMin(0) == true) {
            return price;
        }
        restDay = false;
        if (isRestDay(start_month,start_day)) {
            restDay = true;
        }
        if (isNight(end_hour,end_min)) {
            if (isNight(start_hour,start_min)) {
                price += 5;
            }
            if (start_hour < 8 && end_hour < 8) {
                return price;
            }
            if (start_hour > 17 && end_hour > 17) {
                return price;
            }
            if (start_hour == 17 && start_min >= 30 && end_hour > 17) {
                return price;
            }
            if (start_hour < 8) {
                start_hour = 8;
                start_min = 0;
            }
            int time = 17 * 60 + 30 - start_hour * 60 - start_min;
            int count = time / 30 + (time % 30 == 0 ? 0 : 1);
            if (restDay == true) {
                price += count * center > 15 ? 15 : count * center;
            }
            if (restDay == false) {
                price += count * center > 24 ? 24 : count * center;
            }
            price += 5;
            return price;
        }
        if (isNight(start_hour, start_min)) {
            price += 5;
            start_hour = 8;
            start_min = 0;
        }
        int time = end_hour * 60 + end_min - start_hour * 60 - start_min;
        int count = time / 30;
        if (time % 30 != 0) {
            count += 1;
        }
        if (restDay == true) {
            price += count * center > 15 ? 15 : count * center;
            return price;
        }
        price += count * center > 16 * center ? 16 * center : count * center;
        return price;
    }

    /**
     * @param hour 当前的小时
     * @param min 当前的分钟
     * 判断输入的时间是否为晚上
     * */
    private boolean isNight(int hour, int min) {
        if (hour < 17 && hour >= 8) {
            return false;
        }
        if (hour == 17) {
            if (min <= 30) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param start_date 停车开始的时间
     * 通过开始的时间把年，月，日，时，分赋值到对应的参数上去
     * */
    private void readStartDate(String start_date) {
        String[] dateAndTime = start_date.split(" ");
        String[] yearMonthDay = dateAndTime[0].split("/");
        //System.out.println(yearMonthDay[0]  + " " + yearMonthDay[1] + " " + yearMonthDay[2]);
        String[] hourMinute = dateAndTime[1].split(":");
        //System.out.println(hourMinute[0]  + " " + hourMinute[1]);
        start_year = 2000 + Integer.parseInt(yearMonthDay[2]);
        start_month = Integer.parseInt(yearMonthDay[0]);
        start_day = Integer.parseInt(yearMonthDay[1]);
        start_hour = Integer.parseInt(hourMinute[0]);
        start_min = Integer.parseInt(hourMinute[1]);
        //System.out.println("Year: " + start_year + " Month: " + start_month + " Day: " + start_day + " Hour: " + start_hour + " Minute: " + start_min);
    }

    /**
     * @param end_date 停车开始的时间
     * 通过截至的时间把年，月，日，时，分赋值到对应的参数上去
     * */
    private void readEndDate(String end_date) {
        String[] dateAndTime = end_date.split(" ");
        String[] yearMonthDay = dateAndTime[0].split("/");
        //System.out.println(yearMonthDay[0]  + " " + yearMonthDay[1] + " " + yearMonthDay[2]);
        String[] hourMinute = dateAndTime[1].split(":");
        //System.out.println(hourMinute[0]  + " " + hourMinute[1]);
        end_year = 2000 + Integer.parseInt(yearMonthDay[2]);
        end_month = Integer.parseInt(yearMonthDay[0]);
        end_day = Integer.parseInt(yearMonthDay[1]);
        end_hour = Integer.parseInt(hourMinute[0]);
        end_min = Integer.parseInt(hourMinute[1]);
        //System.out.println("Year: " + end_year + " Month: " + end_month + " Day: " + end_day + " Hour: " + end_hour + " Minute: " + end_min);
    }

    /**
     * 判断是否为休息日或节假日
     * */
    private boolean isRestDay(int month, int day) {
        return restDay.isRestDay(month, day);
    }

    /**
     * 自动修正时间
     * */
    private void autoTimeFix() {
        if (start_min >= 60) {
            start_min %= 60;
            start_hour += 1;
        }
        if (start_hour >= 24) {
            start_hour %= 24;
            start_day += 1;
        }
        if (start_day > restDay.getDays(start_month)) {
            start_day = 1;
            start_month += 1;
        }
    }

    /**
     * 判断车辆停的时间是否小于30分钟，是则返还true，反之返还false
     * */
    private boolean isLessThanAnyMin(int any) {
        int temp_min = start_min + any;
        int temp_hour = start_hour;
        int temp_day = start_day;
        int temp_month = start_month;
        int temp_year = start_year;
        if (temp_min >= 60) {
            temp_min %= 60;
            temp_hour += 1;
        }
        if (temp_hour >= 24) {
            temp_hour %= 24;
            temp_day += 1;
        }
        if (temp_day > restDay.getDays(temp_month)) {
            temp_day = 1;
            temp_month += 1;
        }
        if (temp_month > 12) {
            return true;
        }
        if (temp_month > end_month) {
            return true;
        }
        if (temp_month == end_month) {
            if (temp_day > end_day) {
                return true;
            }
            if (temp_day == end_day) {
                if (temp_hour > end_hour) {
                    return true;
                }
                if (temp_hour == end_hour) {
                    if (temp_min > end_min) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断开始与截至日期是否为同一天
     * */
    private boolean isSameDay() {
        if (start_year != end_year) {
            return false;
        }
        if (start_month != end_month) {
            return false;
        }
        if (start_day != end_day) {
            return false;
        }
        return true;
    }
}
