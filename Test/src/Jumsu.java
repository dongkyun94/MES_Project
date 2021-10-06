import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Jumsu {

	public static void main(String[] args) {
		Map<String, ArrayList<String>> student = new HashMap<String, ArrayList<String>>();
		Map<String, Integer> rank = new HashMap<String, Integer>();
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 3; i++) {
			System.out.println("데이터를 입력해 주세요 ->(학생이름 / 국어 / 영어 / 수학");
			String str = sc.nextLine();
			String[] temp = str.split(" ");
			ArrayList<String> score = new ArrayList<String>();
			int sum = Integer.parseInt(temp[1]) + Integer.parseInt(temp[2]) + Integer.parseInt(temp[3]);
			double avg = sum / 3;
			score.add(temp[1]);
			score.add(temp[2]);
			score.add(temp[3]);
			score.add(Integer.toString(sum));
			score.add(Double.toString(avg));
			student.put(temp[0], score);
			rank.put(temp[0], sum);
		}
		String[] ranking = new String[3];
		Iterator<String> keys = rank.keySet().iterator();
		int idx = 0;
		while(keys.hasNext()) {
			String key = keys.next();
			ranking[idx] = key;
			idx++;
		}
		for(int i =0; i < ranking.length; i++) {
			for(int j = i+1; j <ranking.length; j++) {
				if(rank.get(ranking[i])<rank.get(ranking[j])) {
					String tmp = ranking[i];
					ranking[i] = ranking[j];
					ranking[j] = tmp;
				}
			}
		}
		
		System.out.println("등수  학생이름  국어  영어  수학  총점  평균");
		System.out.println("===============================================");
		for(int i = 0; i < ranking.length; i++) {
			System.out.printf("  %d   %s    %s    %s    %s    %s    %s  \n",(i+1),ranking[i],student.get(ranking[i]).get(0),
					student.get(ranking[i]).get(1),student.get(ranking[i]).get(2),
					student.get(ranking[i]).get(3),student.get(ranking[i]).get(4));
		}

	}

}
