public class RestDay {

    private boolean[] Jan;
    private boolean[] Feb;
    private boolean[] Mar;
    private boolean[] Apr;
    private boolean[] May;
    private boolean[] June;
    private boolean[] July;
    private boolean[] Aug;
    private boolean[] Sept;
    private boolean[] Oct;
    private boolean[] Nov;
    private boolean[] Dec;


    /**
     * @param firstWeekend 一月份的第一个周六的日期
     * @param febDays 二月份的日期
     * 创建一个自动添加所有双休日
     * */
    public RestDay(int firstWeekend, int febDays) {
        Jan = new boolean[31];
        Feb = new boolean[febDays];
        Mar = new boolean[31];
        Apr = new boolean[30];
        May = new boolean[31];
        June = new boolean[30];
        July = new boolean[31];
        Aug = new boolean[31];
        Sept = new boolean[30];
        Oct = new boolean[31];
        Nov = new boolean[30];
        Dec = new boolean[31];
        setAllDefault();
        setWeekend(firstWeekend);
    }

    /**
     * @param month 代表月份的bool数组
     * 将传入的bool数组设置为默认状态（即全部为false）
     * */
    private void setDefaultMonth(boolean[] month) {
        for (int i=0;i< month.length;i++) {
            month[i] = false;
        }
    }

    /**
     * 将所有的月份都重设为默认状态
     * */
    private void setAllDefault() {
        setDefaultMonth(Jan);
        setDefaultMonth(Feb);
        setDefaultMonth(Mar);
        setDefaultMonth(Apr);
        setDefaultMonth(May);
        setDefaultMonth(June);
        setDefaultMonth(July);
        setDefaultMonth(Aug);
        setDefaultMonth(Sept);
        setDefaultMonth(Oct);
        setDefaultMonth(Nov);
        setDefaultMonth(Dec);
    }

    /**
     * @param firstWeekend 该年份一月第一个周六的日期
     * 设置今年所有的双休日为true
     * */
    private void setWeekend(int firstWeekend) {
        int count = 7 - firstWeekend;
        count = setWeekendPerMonth(count, Jan);
        count = setWeekendPerMonth(count, Feb);
        count = setWeekendPerMonth(count, Mar);
        count = setWeekendPerMonth(count, Apr);
        count = setWeekendPerMonth(count, May);
        count = setWeekendPerMonth(count, June);
        count = setWeekendPerMonth(count, July);
        count = setWeekendPerMonth(count, Aug);
        count = setWeekendPerMonth(count, Sept);
        count = setWeekendPerMonth(count, Oct);
        count = setWeekendPerMonth(count, Nov);
        count = setWeekendPerMonth(count, Dec);
    }


    /**
     * @param count 计数器，用来确认是否为双休日
     * @param month 月份，修改的数组
     * */
    private int setWeekendPerMonth(int count, boolean[] month) {
        for (int i=0;i<month.length;i++) {
            if (count == 6 || count == 7) {
                month[i] = true;
                count %= 7;
            }
            count += 1;
        }
        return count;
    }

    /**
     * @param month 月份
     * @param day 日期
     * 通过给定的日期判断是否为假期
     * */
    public boolean isRestDay(int month, int day) {
        day =  day -1;
        switch (month) {
            case 1:
                return Jan[day];
            case 2:
                return Feb[day];
            case 3:
                return Mar[day];
            case 4:
                return Apr[day];
            case 5:
                return May[day];
            case 6:
                return June[day];
            case 7:
                return July[day];
            case 8:
                return Aug[day];
            case 9:
                return Sept[day];
            case 10:
                return Oct[day];
            case 11:
                return Nov[day];
            case 12:
                return Dec[day];
            default:
                System.out.println("Invalid Date");
                return false;
        }
    }

    /**
     * @param month 月份
     * @param day 日期
     * 通过给定的日期来设置假期
     * */
    public void setRestDay(int month, int day) {
        day = day - 1;
        switch (month) {
            case 1:
                Jan[day] = true;
                break;
            case 2:
                Feb[day] = true;
                break;
            case 3:
                Mar[day] = true;
                break;
            case 4:
                Apr[day] = true;
                break;
            case 5:
                May[day] = true;
                break;
            case 6:
                June[day] = true;
                break;
            case 7:
                July[day] = true;
                break;
            case 8:
                Aug[day] = true;
                break;
            case 9:
                Sept[day] = true;
                break;
            case 10:
                Oct[day] = true;
                break;
            case 11:
                Nov[day] = true;
                break;
            case 12:
                Dec[day] = true;
                break;
            default:
                System.out.println("Invalid Date");
        }
    }

    public int getDays(int month) {
        switch (month) {
            case 1:
                return Jan.length;
            case 2:
                return Feb.length;
            case 3:
                return Mar.length;
            case 4:
                return Apr.length;
            case 5:
                return May.length;
            case 6:
                return June.length;
            case 7:
                return July.length;
            case 8:
                return Aug.length;
            case 9:
                return Sept.length;
            case 10:
                return Oct.length;
            case 11:
                return Nov.length;
            case 12:
                return Dec.length;
            default:
                System.out.println("Invalid Month");
        }
        return 0;
    }
//    public void print() {
//        System.out.println("January");
//        int time = 0;
//        for (int i =0;i< Jan.length;i++) {
//            System.out.print(Jan[i] + " ");
//            if (time%7 == 0) {
//                System.out.println();
//            }
//        }
//    }
}
