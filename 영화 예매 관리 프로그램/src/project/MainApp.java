package project;

public class MainApp {
	public static void main(String[] args) {
		System.out.println("���α׷��� �����մϴ�!");	//���α׷� ���๮��
		Menu menu = MainMenu.getInstance();		//"���� �޴�"�� ������
			
		while(menu != null) { 	//�޴��� ������ ��� �ݺ�
			menu.print();		//���� �޴� ���
			menu = menu.next();	//����� �Է� ��, ���� �޴��� ����
		}
		System.out.println("���α׷��� ����˴ϴ�."); //���α׷� ���� ����
	}

}

interface Menu{
	void print();	//�޴����
	Menu next();	//���� �޴��� �̵�
}
