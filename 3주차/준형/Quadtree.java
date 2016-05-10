package Chapter7;

import java.util.Scanner;

public class Quadtree {
	static String quadTree;
	static int idx;

	static String flip() {
		char head = quadTree.charAt(idx);
		++idx;
		if (head == 'b' || head == 'w')
			return Character.toString(head);
		
		String uL = flip();
		String uR = flip();
		String dL = flip();
		String dR = flip();

		return (new String("x")) + dL + dR + uL + uR;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < C; i++) {
			idx = 0;
			quadTree = null;
			quadTree = sc.nextLine();
			System.out.println(flip());
		}
		sc.close();
	}
}
