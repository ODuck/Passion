package Chapter6;

import java.util.Scanner;

public class Clocksync {
	final static int INF = 9999;
	static int switches[][] = new int[10][];
	static int clock[] = new int[16];

	// 시계 정렬 확인
	static boolean check() {
		for (int i = 0; i < 16; i++) {
			if (clock[i] != 12)
				return false;
		}
		return true;
	}

	// 스위치 누르기
	static void push(int swNum) {
		// 연결된 시계 처리
		for (int i = 0; i < switches[swNum].length; ++i) {
			clock[switches[swNum][i]] += 3;
			// 시계가 12시를 넘어갈때
			if (clock[switches[swNum][i]] == 15)
				clock[switches[swNum][i]] = 3;
		}
	}

	static int solve(int swNum) {
		if (swNum == 10) {
			if (check())
				return 0;
			else
				return INF;
		}
		// 경우의 수가 작은 값 찾기
		int result = INF;
		for (int i = 0; i < 4; ++i) {
			result = Math.min(result, i + solve(swNum + 1));
			push(swNum);
		}
		return result;
	}

	public static void main(String[] args) {
		// 스위치 초기화
		switches[0] = new int[] { 0, 1, 2 };
		switches[1] = new int[] { 3, 7, 9, 11 };
		switches[2] = new int[] { 4, 10, 14, 15 };
		switches[3] = new int[] { 0, 4, 5, 6, 7 };
		switches[4] = new int[] { 6, 7, 8, 10, 12 };
		switches[5] = new int[] { 0, 2, 14, 15 };
		switches[6] = new int[] { 3, 14, 15 };
		switches[7] = new int[] { 4, 5, 7, 14, 15 };
		switches[8] = new int[] { 1, 2, 3, 4, 5 };
		switches[9] = new int[] { 3, 4, 5, 9, 13 };

		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int i = 0; i < C; i++) {
			// 입력
			for (int j = 0; j < 16; j++)
				clock[j] = sc.nextInt();
			// 출력
			int result = solve(0);
			if (result == INF)
				System.out.println(-1);
			else
				System.out.println(result);
		}
		sc.close();
	}
}
