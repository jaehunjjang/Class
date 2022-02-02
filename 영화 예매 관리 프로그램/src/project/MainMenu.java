package project;

import java.io.IOException;		//���� �߰��� �ڵ�
import java.util.ArrayList;

class MainMenu extends AbstractMenu{ //AbstractMenu�� ��ӹ���
	private static final MainMenu instance = new MainMenu(null);	//�ڱ� �ڽ��� ��ü ����
	private static final String MAIN_MENU_TEXT =	//�⺻ ����
			"1 : ��ȭ �����ϱ�\n"+
			"2 : ���� Ȯ���ϱ�\n"+
			"3 : ���� ����ϱ�\n"+
			"4 : ������ �޴��� �̵�\n" +
			"q : ����\n\n"+
			"�޴��� �����ϼ���: ";
	
	private MainMenu(Menu prevMenu) {
		super( MAIN_MENU_TEXT, prevMenu);			//�θ� ������ ȣ��
	}
	
	public static MainMenu getInstance() {
		return instance;							//�޴� ��ü ��ȯ
	}
	
	public Menu next() {
		switch (scanner.nextLine()) {				//����� �Է��� ������� ������
		case "1" :
			reserve();								//��ȭ ���Ÿ� ����
			return this;							//���� �޴� ��ü ��ȯ(�ٽ� ���� �޴��� ����)
		case "2" :
			checkReservation();						//���� Ȯ��
			return this;							// ���� �޴� ��ü ��ȯ(�ٽ� ���� �޴��� ����)
		case "3" :
			cancelReservation(); 					//���� ���
			return this;							//���� �޴� ��ü ��ȯ(�ٽ� ���� �޴��� ����)
		case "4" :
			if( ! checkAdminPassword()) { 			//������ ��й�ȣ
				System.out.println(">>��� ��ȣ�� Ʋ�Ƚ��ϴ�.");
				return this;						//������ ��� ���� �޴� ��ü ��ȯ
			}
			AdminMenu adminMenu = AdminMenu.getInstance();
			adminMenu.setPrevMenu(this);			//���� �޴��� ���� �޴��� ���
			return adminMenu;						//������ �޴� ��ü�� ��ȯ
		case "q" : return prevMenu;					//q �Է� ��, prevMenu�� ��ȯ
		default : return this; 						//�� �� �Է��� ���� �޴�(this)�� ���ư�
		}
	}
	
	private boolean checkAdminPassword() {
		System.out.println("������ ��й�ȣ�� �Է��ϼ���: ");
		return "1234".equals(scanner.nextLine());
				//������ ��й�ȣ		//���� ����� �Է�
	}
	
	private void checkReservation() {
		System.out.println("�߱� ��ȣ�� �Է��ϼ���: ");
		try {
			Reservation r =
					Reservation.findById(scanner.nextLine()); //���� Ȯ��
			if(r != null) {
				System.out.printf(">> [Ȯ�� �Ϸ�] %s\n", r.toString());
			}else {
				System.out.println(">> ���� ������ �����ϴ�.");
			}
		}catch (IOException e) {
			System.out.println(">> ���� ����¿� ������ ������ϴ�.");
		}
	}
	
	private void cancelReservation() {
		System.out.println("�߱� ��ȣ�� �Է��ϼ���: ");
		try {
			Reservation canceled = Reservation.cancel(scanner.nextLine());
			if(canceled != null) {
				System.out.printf(">> [��ҿϷ�] %s�� ���Ű� ��ҵǾ����ϴ�. \n", canceled.toString());				
			}else {
				System.out.println(">> ���� ������ �����ϴ�.");
			}					
		}catch (IOException e) {
			System.out.println(">> ���� ����¿� ������ ������ϴ�.");
		}
	}
	
	private void reserve() {
		try {
			ArrayList<Movie> movies = Movie.findAll();
			for(int i = 0; i < movies.size(); i++) {						//��ȭ ��� ���
				System.out.printf("%s\n", movies.get(i).toString());
			}
			System.out.println("������ ��ȭ�� �����ϼ���: ");
			String movieIdStr = scanner.nextLine();							//���� ��ȭ ����
			Movie m = Movie.findById(movieIdStr);
			
			ArrayList<Reservation> reservations =
					Reservation.findByMovieId(movieIdStr);					//���� ��ȭ�� �¼� ��Ȳ ���
			Seats seats = new Seats(reservations);
			seats.show();
			
			System.out.print("�¼��� �����ϼ��� (��: E-9): ");
			String seatName = scanner.nextLine();							//���� �¼� ����
			seats.mark(seatName);
			
			Reservation r = new Reservation(
					Long.parseLong(movieIdStr),		//��ȭ ��ǩ��
					m.getTitle(),					//��ȭ ����				//���� ��ü ����
					seatName						//�¼���
			);
			r.save();
			
			System.out.println(">>���Ű� �Ϸ�Ǿ����ϴ�.");
			System.out.printf(">> �߱޹�ȣ: %d\n", r.getId());				//���� �Ϸ� ��� ���
			
		}catch (IOException e) {
			System.out.println(">> ���� ����¿� ������ ������ϴ�..");
		}catch(Exception e) {
			System.out.printf(">> ���ſ� �����Ͽ����ϴ�: %s\n", e.getMessage());
			
		}
	}
}

















