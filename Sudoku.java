import java.util.ArrayList;
public class MyProgram
{   
    public static int[][] board = new int[9][9];
    public static ArrayList<Integer> ns = new ArrayList<Integer>();
    public static int[] nums = new int[9];
    public static int count = 0;
    public static void main(String[] args)
    {
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                board[i][j] = 0;
            }
        }
        
        randomize();
        fillCell(0, 0, 0);
        
        
        
        
        
        print();
        
        
        
    }
    
    public static void randomize(){
        for(int i = 1; i<10; i++){
            ns.add(i);
        }
        
        for(int j = 0; j<nums.length; j++){
            int index = (int)(Math.random()*ns.size());
            nums[j] = ns.get(index);
            ns.remove(index);
        }
    }
    
    public static void print(){
        for(int r = 0; r<board.length; r++){
            if(r%3 == 0 && r!= 0){
                for(int i = 0; i<11; i++){
                        System.out.print("-");
                }
                System.out.println();
            }
            for(int c = 0; c <board.length; c++){
                if((c)%3 == 0 && c != 0){
                    System.out.print("|");
                }
                System.out.print(board[r][c]);
                
            }
            
            System.out.println();
        
        }
        
    }
    
    public static boolean safeColumn(int n, int col){
        for(int i = 0; i<board.length; i++){
            if(board[i][col] == n){
                return false;
            }
        }
        return true;
    }
    
    public static boolean safeRow(int n, int row){
        for(int i = 0; i<board.length; i++){
            if(board[row][i] == n){
                return false;
            }
        }
        return true;
    }
    
    public static boolean safeSquare(int n, int row, int col){
        int startRow = 3 * (row/3);
        int startCol = 3*(col/3);
        for(int i = startRow; i< startRow +3; i++){
            for(int j = startCol; j<startCol+3; j++){
                if(board[i][j] == n){
                    return false;
                } 
            }
        }
        return true;
        
    }
    
    public static boolean safe(int n, int row, int col){
        if(safeSquare(n, row, col) && safeRow(n, row) && safeColumn(n, col)){
            return true;
        }
        return false;
    }
    public static void fillCell(int start, int row, int col){
        randomize();
        if(safe(nums[start], row, col)){
            board[row][col] = nums[start];
            count = 0;
            col++;
            if(col == 9){
                col = 0;
                row++;
            }
            if(row == 4){
                return;
            }
            fillCell(start, row, col);
        }
        else{
            count++;
            if(count == 9){
                col--;
                if(col == -1){
                    row--;
                    col = 8;
                }
                count = 0;
                fillCell(start, row, col);
            }
            else{
                fillCell(start, row, col);
            }
        }
    }
}