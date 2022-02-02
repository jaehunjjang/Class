package project;

import java.io.*;
import java.time.Instant; 		
import java.util.ArrayList;		//�ڹ� API�ҷ�����

public class Movie {			
	private long id;			//��ȭ ��ǩ��
	private String title;		//��ȭ ����
	private String genre;		//��ȭ �帣
	private static final File file = new File("movies.txt");
								//movies.txt ���� ��ü
	
	public Movie (long id, String title, String genere) { //������
		this.id = id;
		this.title = title;
		this.genre = genre;
	}
	
	public static ArrayList<Movie> findAll() throws IOException {
		ArrayList<Movie> movies = new ArrayList<Movie> ();
		BufferedReader br = new BufferedReader (new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) != null) { //������ �� �྿ �о�� �ݺ�
			String[] temp = line.split(",");	//��ǥ�� �������� ���ڿ��� ����
			Movie m = new Movie(				//��ȭ ��ü ����
					Long.parseLong(temp[0]),	//��ȭ ��ǩ��
					temp[1],					//��ȭ ����
					temp[2]						//��ȭ �帣
			);
			movies.add(m);	//���� ��ȭ ��ü�� ArrayList�� �߰�
		}
		br.close();			//���� �Է� �帧 ����
		return movies;		//��ȭ ��ü�� ��� ArrayList ��ȯ
	}
	
	public String toString() {
		return String.format("[%d]: %s(%s)", id, title, genre);
	}
	
	public Movie(String title, String genre) {
		this.id = Instant.now().getEpochSecond();		//Ÿ�ӽ�����
		this.title = title;
		this.genre = genre;
	}
	
	public void save() throws IOException {
		FileWriter fw = new FileWriter(file, true);
									//�̾� ��� (append) ��� ����(true)
		fw.write(this.toFileString() + "\n");
		fw.close();
	}
	
	private String toFileString() { //��ü ������ ���ڿ��� ��ȯ
		return String.format("%d,%s,%s", id, title, genre);
	}
	
	public static void delete(String movieIdStr) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String text = "";								//������ �����ϱ� ���� �� ���ڿ�
		String line = null;
		
		while((line = br.readLine()) != null) {
												//������ �� ������ �о��(�ݺ�)
			String[] temp = line.split(",");	//��ǥ �������� ���ڿ��� ����
			if (movieIdStr.equals(temp[0])) { 	//���� ����� ã����
				continue;						//���� �ݺ����� �Ѿ (������� �ʰ�)
			}
			text += line + "\n";				// ���� ���ڿ��� �����Ͽ� ����
		}
		br.close(); 							//�Է� �帧 ����
		
		FileWriter fw = new FileWriter(file);
									//FileWriter ��ü ����(����� ���)
		fw.write(text);				//���� ���
		fw.close();					//��� �帧 ����
	}
	
	public static Movie findById(String movieIdStr) throws IOException {
		Movie movie = null;
		BufferedReader br = new BufferedReader( new FileReader(file));
		String line = null;
		
		while ((line = br.readLine()) != null) {
							//�� ���� ���ڿ� �б� , ���� �ݺ� 
			String[] temp = line.split(","); 		//���ڿ��� ��ǥ�� ����
			if(movieIdStr.equals(temp[0])) {
										//��ȭ ��ǩ���� ã����, ��ü ����
				movie = new Movie(Long.parseLong(temp[0]), temp[1], temp[2]);
				break;					//�ݺ� �� Ż��(�� �̻� ã�� ����)
			}
		}
		br.close();						//�Է� �帧 ����
		return movie;					//��ȭ ��ü ��ȯ
	}
	
	public String getTitle() {
		return title;
	}
}


















