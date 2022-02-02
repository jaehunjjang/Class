package project;

import java.io.IOException;
import java.util.ArrayList;	//���� �߰��� �ڵ�

public class AdminMenu extends AbstractMenu { //AbstractMenu�� ��ӹ���
	private static final AdminMenu instance = new AdminMenu(null);//�ڱ� �ڽ��� ��ü ����
	private static final String ADMIN_MENU_TEXT = //�⺻ ����
			"1: ��ȭ ����ϱ�\n" +
			"2: ��ȭ ��� ����\n" +
			"3: ��ȭ �����ϱ�\n" +
			"b: ���� �޴��� �̵�\n\n" +
			"�޴��� �����ϼ���: ";
	
	private AdminMenu(Menu prevMenu) {
		super(ADMIN_MENU_TEXT, prevMenu);	//�θ� ������ ȣ��
	}
	
	public static AdminMenu getInstance() {
		return instance;					//�޴� ��ü�� ��ȯ
	}
	
	public Menu next() {
		switch(scanner.nextLine()) {
		case "1" :							//1�� �޴� ���� �� 
			createMovie();					//��ȭ ��� ����
			return this;					//������ �޴� ��ü ��ȯ
		case "2" : 							//2�� �޴� ���� ��
			printAllMovies();				//��ȭ ��� ���
			return this;					//������ �޴� ��ü ��ȯ
		case "3" :							//3�� �޴� ���� ��
			deleteMovie();					//��ȭ ���� ����
			return this;					//������ �޴� ��ü ��ȯ(�ٽ� ������ �޴��� ����)
		case "b" : return prevMenu;			//b �Է� ��, ���� �޴��� ��ȯ
		default : return this;
		}
	}
	
	private void printAllMovies() {
		try {
			ArrayList<Movie> movies = Movie.findAll();	  //��� ��ȭ�� ������
			System.out.println();
			for(int i = 0; i < movies.size(); i++) {
				System.out.printf("%s\n", movies.get(i).toString()); //���
			}
		}catch (IOException e) {
			System.out.println("������ ���ٿ� �����Ͽ����ϴ�."); //���� ó��
		}
	}
	
	private void createMovie() {
		System.out.println("����: ");
		String title = scanner.nextLine();			//������ �Է�
		System.out.println("�帣: ");
		String genre = scanner.nextLine();			//�帣 �Է�
		Movie movie = new Movie(title,genre);		//��ȭ ��ü ����
		try {
			movie.save(); 							//��ȭ ��ü�� ����
			System.out.println(">> ��ϵǾ����ϴ�.");
		}catch (IOException e) {					//���� ó��
			System.out.println(">> �����Ͽ����ϴ�.");
		}
	}
	
	private void deleteMovie() {
		printAllMovies();		//��� ��ȭ�� ���
		System.out.println("������ ��ȭ�� �����ϼ��� ");
		try {
			Movie.delete(scanner.nextLine());
								//����� �Է°� �������� ���� ��û
			System.out.println(">> �����Ǿ����ϴ�.");
		}catch (IOException e) {
			System.out.println(">> ������ �����Ͽ����ϴ�.");
		}
	}
			
}










