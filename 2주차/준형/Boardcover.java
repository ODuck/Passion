package Chapter6;

import java.util.Scanner;

public class Boardcover {
	static int height, weight;
	static int board[][] = new int[20][20];
	final static int BLACK = 1;
	final static int WHITE = 0;
	final static int blockType[][][] = { { { 0, 0 }, { 1, 0 }, { 0, 1 } }, // ┌
																			// Type
			{ { 0, 0 }, { 1, 0 }, { 1, 1 } }, // ┐ Type
			{ { 0, 0 }, { 0, 1 }, { -1, 1 } }, // ┘ Type
			{ { 0, 0 }, { 0, 1 }, { 1, 1 } } // └ Type
	};

	static boolean cover(int x, int y, int type, boolean put) {
		// 덮어지는지 확인
		for (int i = 0; i < 3; ++i) {
			int nx = x + blockType[type][i][0];
			int ny = y + blockType[type][i][1];
			if (nx < 0 || nx >= weight || ny < 0 || ny >= height)
				return false; // 블록이 게임판을 벗어나는 경우
			else if (board[nx][ny] > WHITE)
				return false; // 블록이 다른 블록과 겹치는 경우
		}
		// 덮기 실행
		for (int i = 0; i < 3; ++i) {
			int nx = x + blockType[type][i][0];
			int ny = y + blockType[type][i][1];
			if (put)
				board[nx][ny] = BLACK;
			else
				board[nx][ny] = WHITE;
		}
		return true;
	}

	static int solve() {
		// 아직 덮이지 않은 부분 찾기
		int x = -1;
		int y = -1;
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < weight; ++j)
				if (board[j][i] == WHITE) {
					x = j;
					y = i;
					break;
				}
			if (y != -1)
				break;
		}
		// 다 덮었으면 끝내기
		if (y == -1)
			return 1;
		// 4가지 블록으로 덮어보기
		int result = 0;
		for (int type = 0; type < 4; ++type) {
			if (cover(x, y, type, true))
				result += solve();
			cover(x, y, type, false); // 블록 치우기
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int i = 0; i < C; i++) {
			// 초기화
			for (int j = 0; j < 20; j++)
				for (int k = 0; k < 20; k++)
					board[j][k] = -1;
			// 입력
			height = sc.nextInt();
			weight = sc.nextInt();
			sc.nextLine(); // 버퍼 비우기
			int whiteCnt = 0;
			for (int j = 0; j < height; j++) {
				String tmp = sc.nextLine();
				for (int k = 0; k < weight; k++) {
					// 문자열 변환
					if (tmp.charAt(k) == '#')
						board[k][j] = BLACK;
					else {
						board[k][j] = WHITE;
						whiteCnt++;
					}
				}
			}
			// 출력
			if ((whiteCnt % 3) != 0)
				System.out.println(0);
			else
				System.out.println(solve());
		}
		sc.close();
	}
}