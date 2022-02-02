package project;

import java.io.*;		//����� ���� API �ҷ����� 
import java.time.Instant;
import java.util.ArrayList;

public class Reservation {
	private long id;			//�߱޹�ȣ
	private long movieId;		//��ȭ ��ǩ��
	private String movieTitle;	//��ȭ ����
	private String seatName;	//�¼���
	private static final File file = new File("reservation.txt");
								//���� ��ü
	
	public Reservation(long id, long movieId, String movieTitle, String seatName) {
		this.id = id;
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.seatName = seatName;
	}
	
	public static Reservation findById(String reservationId) throws
	IOException {
		Reservation r =null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) !=null) {
												//�� ���� ���ڿ� �б�, ���� �ݺ�
			String[] temp = line.split(",");	//���ڿ��� ��ǥ�� ����
			if(reservationId.equals(temp[0])) { //�߱� ��ȣ�� ã����
				r = new Reservation(			//��ü ����
						Long.parseLong(temp[0]),//�߱޹�ȣ
						Long.parseLong(temp[1]),//��ȭ ��ǩ��
						temp[2],				//��ȭ ����
						temp[3]					//�¼���
				);
				break;							//�ݺ� Ż��
			}
		}
		br.close();								//�Է� �帧 ����
		return r;
	}
	
	public String toString() {
		return String.format("��ȭ: %s, �¼�: %s", movieTitle, seatName);		
	}
	
	public static Reservation cancel(String reservationId) throws IOException{
		Reservation canceled = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String text = "";
		String line = null;
		
		while((line = br.readLine()) != null) {
							//�� ���� ���ڿ� �б�, ���� �ݺ�
			String[] temp = line.split(",");		//���ڿ��� ��ǥ�� ����
			if(reservationId.equals(temp[0])) {		//�߱� ��ȣ�� ã����
				canceled = new Reservation (		//��ü ����
						Long.parseLong(temp[0]), 	//�߱޹�ȣ
						Long.parseLong(temp[1]),	//��ȭ ��ǩ��
						temp[2],					//��ȭ ����
						temp[3]						//�¼���
			  );
				continue;							//���� �ݺ����� �Ѿ(������� �ʰ�)						
			}
			text += line + "\n";					//���� ���ڿ��� �����Ͽ� ����
		}
		br.close();									//�Է� �帧 ����
		
		FileWriter fw = new FileWriter(file);
										//FileWriter ��ü ����(����� ���)
		fw.write(text);					//�������
		fw.close();						//��� �帧 ����
		return canceled;				//������ ���Ÿ� ��ü�� ��ȯ
	}
	
	public static ArrayList<Reservation> findByMovieId(String movieIdStr) throws IOException{
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) != null) {
									//�� ������ ���ڿ��� ����, ���� �ݺ�
			String[] temp = line.split(",");			//���ڿ��� ��ǥ �������� ����
			if(movieIdStr.equals(temp[1])) {			//��ȭ ��ǩ���� ã�Ҵٸ�
				long id = Long.parseLong(temp[0]);		//���� �߱޹�ȣ
				long movieId = Long.parseLong(temp[1]);	//���� ��ȭ�� ��ǩ��
				String movieTitle = temp[2];			//���� ��ȭ�� ����
				String seatName = temp[3];				//���� ��ȭ�� �¼��� 
				Reservation r = new Reservation(id, movieId, movieTitle, seatName);	//���� ��ü ����
				reservations.add(r);					//���� ��ü�� ArrayList�� �߰�
			}
		}
		br.close();				//�Է� �帧 ����
		return reservations;	//���� ��ü�� ���� ArrayList ��ȯ 
	}
	
	public Reservation(long movieId, String movieTitle, String seatName) {
		this.id = Instant.now().toEpochMilli();		//�и��� ���� Ÿ�ӽ����� ����
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.seatName = seatName;
	}
	
	public String getSeatName() {	//���� �޼ҵ�
		return seatName;
	}

	public void save() throws IOException {
		FileWriter fw = new FileWriter(file, true);
									// �̾�� (append) ��� ����(true)
		fw.write(this.toFileString() + "\n");
		fw.close();
	}
	
	private String toFileString() {	//��ü ������ ���� ���� ���� ���ڿ��� ��ȯ
		return String.format("%d,%d,%s,%s", id, movieId, movieTitle, seatName);
	}
	
	public long getId() { 			//���� �޼ҵ�
		return id;
	}
}
