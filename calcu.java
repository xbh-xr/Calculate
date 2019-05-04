package calculate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

//import javax.swing.*;
//�ܹ��Զ���������������ϰ��
//���Զ�����Ŀ����
//�û�����ѡ�������
//�û��������������ʮ���ڡ������ڵȣ�
//�û�ѡ���Ƿ������š��Ƿ���С��
//�û�ѡ�������ʽ����������ļ�����ӡ���ȣ�
//������ṩͼ���û����棨�����Լ�����ѡ�����������������Ϊ����

public class calcu {
    public static ArrayList<String> operator = null;
    static int oper_NUM=0;
	//int Question_MAX=50;	// ��Ŀ����
	static int Question_point=0;	//С���㣬���ż��Ӽ��˳�����λ
	static int Question_bracket=1;
	static int Question_plus=1;
	static int Question_minus=1;
	static int Question_multiply = 1;
	static int Question_divid = 1;
	//int Number_MAX=100; // �û��������������ʮ���ڡ������ڵȣ�

	/* ���ô洢��Ŀ����⣺HashSet,�����ӡ��ͬ��Ŀ */
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
			//bracket_n = 2 + (int)(Math.random() * 2); //�������λ��
			bracket_n = 1 + (int)(Math.random() * i);
		}
		else
			i = 1 + (int) (Math.random() * 4);
		int factor;
		String oper = "";
		factor = 1 + (int) (Math.random() * Number_MAX); // ��������1С��Number_MAX�������
		ques = ques + factor;
		if(Question_point == 1){
			factor = 1 + (int) (Math.random() * 100); // ������λС������
			ques = ques + '.' + factor;
		}
		while(i!=0){
	        i--;
			oper = operator.get((int) (Math.random() * oper_NUM));// �����������
			ques = ques + oper;
			if(Question_bracket == 1 && bracket_n + i == 5) 
				ques = ques +'(';
			factor = 1 + (int) (Math.random() * Number_MAX); // ��������1С��Number_MAX�������
			ques = ques + factor;
			if(Question_point == 1){
				factor = 1 + (int) (Math.random() * Number_MAX); // ������λС������
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
		System.out.println("�������Ƿ�Ҫ��С����1/0����");	//С���㣬���ż��Ӽ��˳�����λ
		Question_point=scan.nextInt();
		System.out.println("�������Ƿ�Ҫ�����ţ�1/0����");
		Question_bracket=scan.nextInt();
		System.out.println("�������Ƿ�Ҫ�мӷ���1/0����");
		Question_plus=scan.nextInt();
		System.out.println("�������Ƿ�Ҫ�м�����1/0����");
		Question_minus=scan.nextInt();
		System.out.println("�������Ƿ�Ҫ�г˷���1/0����");
		Question_multiply=scan.nextInt();
		System.out.println("�������Ƿ�Ҫ�г�����1/0����");
		Question_divid=scan.nextInt();
		
		
		if(Question_plus == 1){
			operator.add("��");
			oper_NUM++;
		}
		if(Question_minus == 1){
			operator.add("��");
			oper_NUM++;
		}
		if(Question_multiply == 1){
			operator.add("��");
			oper_NUM++;
		}
		if(Question_divid == 1){
			operator.add("��");
			oper_NUM++;
		}
	}
	
	public static void Questions_choose2(){
		
		if(Question_plus == 1){
			operator.add("��");
			oper_NUM++;
		}
		if(Question_minus == 1){
			operator.add("��");
			oper_NUM++;
		}
		if(Question_multiply == 1){
			operator.add("��");
			oper_NUM++;
		}
		if(Question_divid == 1){
			operator.add("��");
			oper_NUM++;
		}
	}
	
	public void init()
	{
		Question_point=0;	//С���㣬���ż��Ӽ��˳�����λ
		Question_bracket=0;
		Question_plus=1;
		Question_minus=1;
		Question_multiply = 0;
		Question_divid = 0;
	}
	public static void main(String[] args) throws IOException {
		calcu ques = new calcu();
		
		int Question_MAX;	// ��Ŀ����
		int Number_MAX; // �û��������������ʮ���ڡ������ڵȣ�
		
		System.out.println("********************************");
		System.out.println("------------��������������-----------");
		System.out.println("********************************");
		Scanner scan = new Scanner(System.in);
		System.out.print("��������Ŀ������");
		Question_MAX = scan.nextInt();
		System.out.print("�����������������");
		Number_MAX = scan.nextInt();
		
		ques.Questions_choose1();
		ques.get_questions(Question_MAX,Number_MAX);
		ques.showQuestions();
	}
}