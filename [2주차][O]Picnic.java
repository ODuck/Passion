package Chapter6;

import java.util.Scanner;

public class Picnic {
	static int n, m;
	static boolean friends[][] = new boolean[10][10];

	static int search(boolean check[]) {
		// 남는 짝 찾기
		int standard = -1;
		for (int i = 0; i < n; ++i) {
			if (!check[i]) {
				standard = i;
				break;
			}
		}
		// 남는 짝이 없으면 종료
		if (standard == -1)
			return 1;

		// 조합 시작
		int count = 0;
		for (int pair = standard + 1; pair < n; ++pair) {
			if (!check[pair] && friends[standard][pair]) {
				check[standard] = check[pair] = true;
				count += search(check);
				check[standard] = check[pair] = false;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int i = 0; i < C; i++) {
			// 변수 선언 및 초기화
			n = m = 0;
			boolean check[] = new boolean[10]; // check: 친구가 있는지 확인
			for (int j = 0; j < 10; j++)
				check[j] = false;
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 10; k++)
					friends[j][k] = false;

			// 입력
			n = sc.nextInt();
			m = sc.nextInt();
			for (int j = 0; j < m; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				friends[a][b] = true;
			}
			// 출력
			System.out.println(search(check));
		}
		sc.close();
	}
}
