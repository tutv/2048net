import java.util.Random;

public class Matrix {
	public static final int SIZE 		= 4;

	public static final int MOVE_RIGHT	= 0;
	public static final int MOVE_DOWN	= 1;
	public static final int MOVE_LEFT	= 2;
	public static final int MOVE_UP		= 3;


	private int matrix [][];					// Luu gia tri cac o
	private boolean dienDuocSoMoi = false;		// Co the dien them so moi hay khong

	public Matrix() {
		matrix = new int[SIZE][SIZE];
		dienSoMoi();
	}

	public String toString(){			// Xuat ra ngoai class mot String chua thong tin Matrix
		String out = "";				// E.g: -> 0 0 0 0,1 2 0 4,2 2 2 2,8 8 8 8
		for (int i=0; i<SIZE; i++){
			for (int j=0; j<SIZE; j++){
				out += (matrix[i][j] + " ");
			}
		}
		return out;
	}

	public void printMatrix(){			// In matrix ra console dang ma tran
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private int taoSo(){					// Khoi tao ngau nhien so 2, 4 de dien vao Matrix
		Random ran = new Random();			// Xac suat:	2 -> 90%
		int soNgauNhien = ran.nextInt(10);	//				4 -> 10%
		if (soNgauNhien != 0) return 1;
		else return 2;
	}

	private int haiMu(int mu){		//			= pow(2, mu);
		int value = 1;
		for (int i=0; i<mu; i++){
			value *= 2;
		}
		return value;
	}

	private void dienSoMoi(){		// Dien them so vao Matrix
		Random ran = new Random();
		boolean oTrong = false;		// Co o trong hay khong
		for (int i=0; i<SIZE; i++){		// Kiem tra xem con o trong de dien so hay khong
			for (int j=0; j<SIZE; j++){
				if (matrix[i][j] == 0){
					oTrong = true;		break;
				}
			}
		}
		if (oTrong == false){		// Khong co o trong thi thoat
			return;
		}
		// Neu con o trong thi bat dau dien so
		int posX, posY;
		while (true){
			posX = ran.nextInt(SIZE);
			posY = ran.nextInt(SIZE);
			if (matrix[posX][posY] == 0){					// Gap o trong thi dien so
				matrix[posX][posY] = haiMu(taoSo());
				break;
			}
		}
	}


	private void rotate(){				// Xoay Matrix theo chieu kim dong ho goc 90*
		int matrixTemp [][] = new int [SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrixTemp[i][j] = matrix[j][SIZE-i-1];		//Cong thuc:	x' = y
			}												//				y' = SIZE - x -1
		}
		matrix = matrixTemp;
	}

	private void donHang(){				// Neu co o trong ben phai thi don het cac o co diem sang phai
		for (int i=0; i<SIZE; i++){				// Duyet tung hang
			for (int j=SIZE-1; j>0; j--){		// Duyet tung o tu phai sang
				if (matrix[i][j] == 0){				// Gap o trong
					int k = j - 1;					// Duyet toi o ben trai no
					while (k >=0 && matrix[i][k] ==0){	// Duyet toi o co so hoac den khi het hang
						k--;
					}
					if (k<0)	break;				// Di het sang trai roi thi thoat
					matrix[i][j] = matrix[i][k];	// Don cot
					matrix[i][k] = 0;				// Tra ve 0, tro thanh o trong
					dienDuocSoMoi = true;			// Khi da co the don cot thi cung co the dien them so moi
				}
			}
		}
	}

	private void congGopO(){
		for (int i=0; i<SIZE; i++){				// Duyet tung hang
			for (int j=SIZE-1; j>0; j--){		// Duyet tung o tu phai sang
				if (matrix[i][j] == matrix[i][j-1] && matrix[i][j] != 0){	// Gap o cung gia tri != 0
					matrix[i][j] += matrix[i][j-1];		// Cong gop o
					matrix[i][j-1] = 0;					// O con lai se la o trong
					dienDuocSoMoi = true;				// Khi da gop duoc o thi cung co the dien them so moi
				}
			}
		}
	}


	public void move(int real){
		int move = 0;
		switch (real) {
			case 37: move = 2; break;
			case 38: move = 3; break;
			case 39: move = 0; break;
			default: move = 1;
		}
		dienDuocSoMoi = false;
		for (int i=0; i<move; i++){			// Xoay matrix sang chieu: Move_right
			rotate();
		}
		donHang();
		congGopO();
		donHang();
		for (int i=0; i<4-move; i++){		// Xoay tro lai chieu ban dau
			rotate();
		}
		if (dienDuocSoMoi){
			dienSoMoi();
		}
	}

	public boolean isGameOver() {
		return !dienDuocSoMoi;
	}
}