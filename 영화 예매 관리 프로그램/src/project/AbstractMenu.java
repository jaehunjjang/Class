package project;

import java.util.Scanner;

//�߻� Ŭ���� ����					//�޴� �������̽��� ����
abstract class AbstractMenu implements Menu{
	protected String menuText;		//�⺻ ����
	protected Menu prevMenu;		//�����޴� 		
	protected static final Scanner scanner = new Scanner(System.in); //Ű���� �Է��� ���� ��ü ����
	
	public AbstractMenu(String menuText, Menu prevMenu) { //������
		this.menuText = menuText;
		this.prevMenu = prevMenu;
	}
				//Menu �������̽��� print() �޼ҵ带 ������
	public void print() {
		System.out.println("\n" + menuText); //�޴� ���
	}
	
	public void setPrevMenu(Menu prevMenu) { //���� �޼ҵ�
		this.prevMenu = prevMenu;
	}
}