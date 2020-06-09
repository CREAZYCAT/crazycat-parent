package top.crazycat.web.biz;

public class TaxCalculation {
    public static void main(String[] args) {
        double salary_month = 17400;//税前月薪
        double salary_wxyj = salary_month*0.08+//社保
                salary_month*0.02+//医保
                salary_month*0.005+//失业
                salary_month*0.07;//公积金
        double salary_zxkc = 0;//专项扣除
        cal(salary_month, salary_wxyj, salary_zxkc);

    }

    private static void cal(double salary_month, double salary_wxyj, double salary_zxkc) {
        double total_salary = 0.00;
        double total_jcfy = 0.00;
        double total_wxyj = 0.00;
        double total_zxkc = 0.00;
        double total_ks = 0.00;
        for (int i = 0; i < 12; i++) {
            total_salary += salary_month;
            total_jcfy += 5000.00;
            total_wxyj += salary_wxyj;
            total_zxkc += salary_zxkc;
            double cur_ks = 0.00;
            double x = (total_salary - total_jcfy - total_wxyj - total_zxkc);
            System.out.println(x);
            if (x < 36000) {
                cur_ks = x * 0.03 - total_ks;
            } else if (x < 144000) {
                cur_ks = x * 0.1 - total_ks - 2520;
            } else if (x < 300000) {
                cur_ks = x * 0.2 - total_ks - 16920;
            } else if (x < 420000) {
                cur_ks = x * 0.25 - total_ks - 31920;
            } else if (x < 660000) {
                cur_ks = x * 0.3 - total_ks - 52920;
            } else if (x < 960000) {
                cur_ks = x * 0.35 - total_ks - 85920;
            } else {
                cur_ks = x * 0.4 - total_ks - 181920;
            }
            total_ks += cur_ks;
            System.out.println("总工资:" + total_salary);
            System.out.println("总减除费用:" + total_jcfy);
            System.out.println("总五险一金:" + total_wxyj);
            System.out.println("总专项扣除:" + total_zxkc);
            System.out.println("累计扣税:" + total_ks);
            System.out.println((i + 1) + "月扣税：" + cur_ks);
            System.out.println((i + 1) + "月实发：" + (salary_month-salary_wxyj-cur_ks));
            System.out.println();
        }
    }

}
