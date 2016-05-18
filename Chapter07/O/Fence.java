package Chapter7;

import java.util.Scanner;

public class Fence {
	static int[] fence = new int[20000];

	static int cut(int left, int right) {
		if (left == right)
			return fence[left];

		int mid = (left + right) / 2;
		int ret = Math.max(cut(left, mid), cut(mid + 1, right));
		int low = mid;
		int high = mid + 1;
		int height = Math.min(fence[low], fence[high]);
		ret = Math.max(ret, height * 2);

		while (left < low || high < right) {
			if (high < right && (low == left || fence[low - 1] < fence[high + 1])) {
				++high;
				height = Math.min(height, fence[high]);
			} else {
				--low;
				height = Math.min(height, fence[low]);
			}

			ret = Math.max(ret, height * (high - low + 1));

		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		for (int i = 0; i < C; i++) {
			// 초기화
			for (int j = 0; j < 20000; j++)
				fence[j] = -1;
			// 입력
			int n = sc.nextInt();
			for (int j = 0; j < n; j++) {
				fence[j] = sc.nextInt();
			}
			// 출력
			System.out.println(cut(0, n));
		}
		sc.close();
	}
}