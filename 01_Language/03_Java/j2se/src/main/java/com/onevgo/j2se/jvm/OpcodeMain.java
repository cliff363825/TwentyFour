package com.onevgo.j2se.jvm;

public class OpcodeMain {
    // https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
    // Jvm字节码指令
    public static void main(String[] args) {
        // iconst_1: 将int常量<i=1>压入操作数栈。
        // istore_1: 将操作数栈顶的int值弹出，局部变量<n=1>的值设置为(value=1)。
        int i = 1;
        // iload_1: 将局部变量<n=1>的值(value=1)压入操作数栈。
        // iinc 1, 1: <const>是一个有符号字节。 局部变量<index>必须包含一个int值。首先将<const>值符号扩展为int，然后将<index>处的局部变量增加该数量。
        // istore_1: 将操作数栈顶的int值弹出，局部变量<n=1>的值设置为(value=1)。
        // i = i++ 解析:
        // 先将i的值1压入操作数栈 => operand(0x01)=1
        // 然后对局部变量i自增 => i=2
        // 将操作数栈顶的值1弹出，将局部变量i(此时为2)的值设置为1 => i=operand(0x01)=1
        i = i++;
        // iload_1 => operand(0x01)=1
        // iinc 1, 1: => i=2
        // istore_2: => j=operand(0x01)=1
        int j = i++;
        // iload_1: operand(0x01)=2
        // iinc 1, 1: i=3
        // iload_1: operand(0x02)=3
        // iload_1: operand(0x02)=3
        // iinc 1, 1: i=4
        // imul: operand(0x02)*operand(0x02)=3*3=9
        // iadd: operand(0x01)+9=2+9=11
        // istore_3: k=11
        int k = i + ++i * i++;
        System.out.println("i=" + i); // 4
        System.out.println("j=" + j); // 1
        System.out.println("k=" + k); // 11

        // 赋值=，最后计算
        // =右边的从左到右加载值依次压入操作数栈
        // 实际先算哪个，看运算符优先级
        // 自增、自减操作都是直接修改变量的值，不经过操作数栈
        // 最后的赋值之前，临时结果也是存储在操作数栈中
    }
}
