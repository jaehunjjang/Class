package project;

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
		case "q" : return prevMenu;					//q �Է� ��, prevMenu�� ��ȯ
		default : return this; 						//�� �� �Է��� ���� �޴�(this)�� ���ư�
		}
	}
}