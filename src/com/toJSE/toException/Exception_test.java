package com.toJSE.toException;

/**
 * 程序接收命令行两个参数，要求不能输入符数，计算两数相除。
 * 对数据不一致（NumberFormatException）、缺少命令行参数（ArrayIndexOutOfBoundsException）、
 * 除0（ArithmeticException）及输入负数（自定义异常）进行异常处理
 */
public class Exception_test {
    public static void main(String[] args) {
        try {
            int i = Integer.parseInt(args[0]);
            int j = Integer.parseInt(args[1]);
            int result = ecm(i, j);
            System.out.println(result);
        } catch (MyExpection myExpection) {
            System.out.println(myExpection.getMessage());
        } catch (NumberFormatException e){
            System.out.println("数据不一致");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("缺少命令行参数");
        } catch (ArithmeticException e){
            System.out.println("除0");
        }
    }

    // 除以方法
    public static int ecm(int i,int j) throws MyExpection {
        if(i<0 || j<0){
            throw new MyExpection("分子或分母为负数");
        }
        return i/j;
    }
}
