import java.util.Random;
import java.util.Scanner;

public class Game2048 {
    private static int row = 4;
    private static int col = 4;
    
    private static int UP = 5;
    private static int DOWN = 2;
    private static int LEFT = 1;
    private static int RIGHT = 3;
    
    private static int NUMBER = 2048;
    
    private static Random rand = new Random();
    
    // tao so ngau nhien 2(90%) hoac 4(10%)
    public static int createRandomNumber() {
        int a = rand.nextInt(10);
        int b = rand.nextInt(10);
        if(a == b) return 4;
        else return 2;
    }
    
    // tao luoi ban dau
    public static int[][] createGrid() {
        int[][] grid = new int[row][col];
        int a, b, c;
        
        for(int i=0; i<row; i++)
        for(int j=0; j<col; j++)
        grid[i][j] = 0;
        
        for(int i=0; i<2; i++) {
            a = rand.nextInt(row);
            b = rand.nextInt(row);
            c = createRandomNumber();
            grid[a][b] = c;
        }
        return grid;
    }
    
    // cap nhat luoi sau khi thuc hien thao tac
    public static int[][] updateGrid(int[][] grid, int verb) {
        boolean checkChange = false;
        
        if(verb == LEFT) {
            for(int i=0; i<row; i++) {
                for(int j=0; j<col-1; j++) {
                    if(grid[i][j] == 0) 
                    { 	 
                    	  
                          	continue;
                    }
                  
                    for(int k=j+1; k<col; k++) {
                        if(grid[i][k] == 0) continue;
                        if(grid[i][k] == grid[i][j]) {
                            grid[i][j] *= 2;
                            grid[i][k] = 0;
                            checkChange = true;
                        }
                        break;
                    }
                }
            }
            
            for(int i=0; i<row; i++) {
                for(int j=0; j<col-1; j++) {
                    if(grid[i][j] != 0) continue;
                    for(int k=j+1; k<col; k++) {
                        if(grid[i][k] == 0) continue;
                        else {
                            grid[i][j] = grid[i][k];
                            grid[i][k] = 0;
                            checkChange = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if(verb == RIGHT) {
            for(int i=row-1; i>=0; i--) {
                for(int j=col-1; j>0; j--) {
                    if(grid[i][j] == 0) continue;
                    for(int k=j-1; k>=0; k--) {
                        if(grid[i][k] == 0) continue;
                        if(grid[i][k] == grid[i][j]) {
                            grid[i][j] *= 2;
                            grid[i][k] = 0;
                            checkChange = true;
                        }
                        break;
                    }
                }
            }
            
            for(int i=row-1; i>=0; i--) {
                for(int j=col-1; j>0; j--) {
                    if(grid[i][j] != 0) continue;
                    for(int k=j-1; k>=0; k--) {
                        if(grid[i][k] == 0) continue;
                        else {
                            grid[i][j] = grid[i][k];
                            grid[i][k] = 0;
                            checkChange = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if(verb == UP) {
            for(int i=0; i<row-1; i++) {
                for(int j=0; j<col; j++) {
                    if(grid[i][j] == 0) continue;
                    for(int k=i+1; k<col; k++) {
                        if(grid[k][j] == 0) continue;
                        if(grid[k][j] == grid[i][j]) {
                            grid[i][j] *= 2;
                            grid[k][j] = 0;
                            checkChange = true;
                        }
                        break;
                    }
                }
            }
            
            for(int i=0; i<row-1; i++) {
                for(int j=0; j<col; j++) {
                    if(grid[i][j] != 0) continue;
                    for(int k=i+1; k<col; k++) {
                        if(grid[k][j] == 0) continue;
                        else {
                            grid[i][j] = grid[k][j];
                            grid[k][j] = 0;
                            checkChange = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if(verb == DOWN) {
            for(int i=row-1; i>0; i--) {
                for(int j=col-1; j>=0; j--) {
                    if(grid[i][j] == 0) continue;
                    for(int k=i-1; k>=0; k--) {
                        if(grid[k][j] == 0) continue;
                        if(grid[k][j] == grid[i][j]) {
                            grid[i][j] *= 2;
                            grid[k][j] = 0;
                            checkChange = true;
                        }
                        break;
                    }
                }
            }
            
            for(int i=row-1; i>0; i--) {
                for(int j=col-1; j>=0; j--) {
                    if(grid[i][j] != 0) continue;
                    for(int k=i-1; k>=0; k--) {
                        if(grid[k][j] == 0) continue;
                        else {
                            grid[i][j] = grid[k][j];
                            grid[k][j] = 0;
                            checkChange = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if(checkChange)
        while(true) {
            int a = rand.nextInt(row);
            int b = rand.nextInt(row);
            if(grid[a][b] == 0) {
                int c = createRandomNumber();
                grid[a][b] = c;
                break;
            }
        }
        
        return grid;
    }
    
    // chuyen luoi sang kieu String
    public static String convertGrid(int[][] grid) {
        String text = "";
        
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++)
            text += grid[i][j] + " ";
            text += "\n";
        }
        return text;
    }
    
    // kiem tra xem co the choi tiep dc ko
    public static boolean checkGrid(int[][] grid) {
        for(int i=0; i<row; i++)
        for(int j=0; j<col; j++)
        if(grid[i][j] == 0) return true;
        
        for(int i=0; i<row; i++)
        for(int j=0; j<col-1; j++) {
            if(grid[i][j] == grid[i][j+1]) return true;
            if(grid[j][i] == grid[j+1][i]) return true;
        }
        
        return false;
    }
    
    // kiem tra xem da dat diem 2048 chua
    public static boolean checkNumber(int[][] grid) {
        for(int i=0; i<row; i++)
        for(int j=0; j<col; j++)
        if(grid[i][j] >= NUMBER) return true;
        
        return false;
    }
    
    public static void main(String []args) {
        int[][] grid = Game2048.createGrid();
        Scanner scanner = new Scanner(System.in);
        int num;
        
        System.out.println(Game2048.convertGrid(grid));
        while(true) {
            System.out.print("Nhap so: ");
            num = scanner.nextInt();
            grid = Game2048.updateGrid(grid, num);
            System.out.println(Game2048.convertGrid(grid));
            if(num==-1)
            	  break;
            if(Game2048.checkNumber(grid)) {
                System.out.println("You win!");
                break;
            }
            if(!Game2048.checkGrid(grid)) {
                System.out.println("You lose!");
                break;
            }
        }
    }
}
