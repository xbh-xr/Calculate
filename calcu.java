package calculate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

//import javax.swing.*;
//能够自动生成四则运算练习题
//可以定制题目数量
//用户可以选择运算符
//用户设置最大数（如十以内、百以内等）
//用户选择是否有括号、是否有小数
//用户选择输出方式（如输出到文件、打印机等）
//最好能提供图形用户界面（根据自己能力选做，以完成上述功能为主）

public class calcu {
    public static ArrayList<String> operator = null;
    static int oper_NUM=0;
	//int Question_MAX=50;	// 题目数量
	static int Question_point=0;	//小数点，括号及加减乘除允许位
	static int Question_bracket=1;
	static int Question_plus=1;
	static int Question_minus=1;
	static int Question_multiply = 1;
	static int Question_divid = 1;
	//int Number_MAX=100; // 用户设置最大数（如十以内、百以内等）

	/* 设置存储题目的题库：HashSet,避免打印相同题目 */
	private static Set<String> questions = new HashSet<String>();
	
	calcu(){
		operator = new ArrayList<String>();
	}
	public static String get_question(int Number_MAX) {
		int bracket_n = 0;
		String ques = "";
		int i;
		if( Question_bracket == 1){
			//i = 4;
			i = 1 + (int) (Math.random() * 5);
			//bracket_n = 2 + (int)(Math.random() * 2); //随机括号位置
			bracket_n = 1 + (int)(Math.random() * i);
		}
		else
			i = 1 + (int) (Math.random() * 4);
		int factor;
		String oper = "";
		factor = 1 + (int) (Math.random() * Number_MAX); // 产生大于1小于Number_MAX的随机数
		ques = ques + factor;
		if(Question_point == 1){
			factor = 1 + (int) (Math.random() * 100); // 产生两位小数部分
			ques = ques + '.' + factor;
		}
		while(i!=0){
	        i--;
			oper = operator.get((int) (Math.random() * oper_NUM));// 产生随机符号
			ques = ques + oper;
			if(Question_bracket == 1 && bracket_n + i == 5) 
				ques = ques +'(';
			factor = 1 + (int) (Math.random() * Number_MAX); // 产生大于1小于Number_MAX的随机数
			ques = ques + factor;
			if(Question_point == 1){
				factor = 1 + (int) (Math.random() * Number_MAX); // 产生两位小数部分
				ques = ques + '.' + factor;
			}
			
			if(Question_bracket == 1 && bracket_n + i == 3)
				ques = ques +')';
		}
		return ques + '=' + "\r\n" ;
		
	}

	public static void get_questions(int Question_MAX,int Number_MAX) {
		int i;
		
		for (i = 0; i < Question_MAX; i++) {
			questions.add(get_question(Number_MAX));
		}
	}
	
	public static void showQuestions() throws IOException {
		String str;
		Iterator<String> iterator = questions.iterator();
		FileWriter fw=new FileWriter("F:\\log.txt");	
		while (iterator.hasNext()) {
			str = iterator.next();
			System.out.println(str);
			fw.write(str);	
		}
		fw.flush();
		fw.close();
	}
	
	public static void Questions_choose1(){
		Scanner scan = new Scanner(System.in);
		System.out.println("运算中是否要有小数（1/0）？");	//小数点，括号及加减乘除允许位
		Question_point=scan.nextInt();
		System.out.println("运算中是否要有括号（1/0）？");
		Question_bracket=scan.nextInt();
		System.out.println("运算中是否要有加法（1/0）？");
		Question_plus=scan.nextInt();
		System.out.println("运算中是否要有减法（1/0）？");
		Question_minus=scan.nextInt();
		System.out.println("运算中是否要有乘法（1/0）？");
		Question_multiply=scan.nextInt();
		System.out.println("运算中是否要有除法（1/0）？");
		Question_divid=scan.nextInt();
		
		
		if(Question_plus == 1){
			operator.add("＋");
			oper_NUM++;
		}
		if(Question_minus == 1){
			operator.add("－");
			oper_NUM++;
		}
		if(Question_multiply == 1){
			operator.add("×");
			oper_NUM++;
		}
		if(Question_divid == 1){
			operator.add("÷");
			oper_NUM++;
		}
	}
	
	public static void Questions_choose2(){
		
		if(Question_plus == 1){
			operator.add("＋");
			oper_NUM++;
		}
		if(Question_minus == 1){
			operator.add("－");
			oper_NUM++;
		}
		if(Question_multiply == 1){
			operator.add("×");
			oper_NUM++;
		}
		if(Question_divid == 1){
			operator.add("÷");
			oper_NUM++;
		}
	}
	
	public void init()
	{
		Question_point=0;	//小数点，括号及加减乘除允许位
		Question_bracket=0;
		Question_plus=1;
		Question_minus=1;
		Question_multiply = 0;
		Question_divid = 0;
	}
	public static void main(String[] args) throws IOException {
		calcu ques = new calcu();
		
		int Question_MAX;	// 题目数量
		int Number_MAX; // 用户设置最大数（如十以内、百以内等）
		
		System.out.println("********************************");
		System.out.println("------------四则运算生成器-----------");
		System.out.println("********************************");
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入题目数量：");
		Question_MAX = scan.nextInt();
		System.out.print("请输入运算最大数：");
		Number_MAX = scan.nextInt();
		
		ques.Questions_choose1();
		ques.get_questions(Question_MAX,Number_MAX);
		ques.showQuestions();
	}
}