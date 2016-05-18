package Chapter7;

import java.util.Scanner;

public class Fanmeeting {
	static int meeting(String members, String fans) {
		// 변수선언 및 초기화
		int membersNum = members.length();
		int fansNum = fans.length();

		// 비교
		boolean isMembersNumLarge = (membersNum > fansNum) ? true : false;
		int result = 0;

		if (isMembersNumLarge) {
			for (int i = 0; i < (membersNum - fansNum + 1); i++) {
				boolean hug = true;
				for (int j = 0; j < fansNum; j++)
					if (members.charAt(j + i) == 'M' && fans.charAt(j) == 'M')
						hug = false;
				if (hug)
					result++;
			}
		} else {
			for (int i = 0; i < (fansNum - membersNum + 1); i++) {
				boolean hug = true;
				for (int j = 0; j < membersNum; j++)
					if (members.charAt(j) == 'M' && fans.charAt(j + i) == 'M')
						hug = false;
				if (hug)
					result++;
			}
		}
		
		// 출력
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		sc.nextLine(); // 버퍼 비우기
		for (int i = 0; i < C; i++) {
			String members = sc.nextLine();
			String fans = sc.nextLine();
			System.out.println(meeting(members, fans));
		}
		sc.close();
	}
}
