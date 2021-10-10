public class SquareTetris implements TetrisBlock {
   private int[] myX;
   private int[] myY;
   public SquareTetris(int x) {
      myX = new int[]{x, x+1, x, x+1};
      myY = new int[]{22, 22, 21, 21};
   }
   public void move(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 0;
      } 
      for (int i = 0; i < 4; i++) {
         myY[i]--;
      }
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 2;
      } 
   }
   public boolean checkStop(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         if (myY[i] <= 0) {
            return true;
         }
      } 
      for (int i = 2; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         if (arr[y-1][x] != 0) {
            return true;
         }
      } 
      return false;
   }
   public void rotate(int[][] arr) {
   }
  public int hardDown(int[][] arr) {
      int moves = 0;
      while(checkStop(arr) == false) {
         move(arr);
         moves++;
      }
      return moves;
   }
   public void moveLeft(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 0;
      }
      for (int i = 0; i < 4; i++) {
         myX[i]--;
      }
      
      boolean good = true;
      for (int i = 0; i < 4; i++) {
         if (myX[i] < 0) {
            good = false;
         }
      }
   
      for(int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         if (x >= 0) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
      }  
              
      if (good == false) {
         for (int i = 0; i < 4; i++) {
            myX[i]++;
         }
      }
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 2;
      }
   }
   public void moveRight(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 0;
      }
      for (int i = 0; i < 4; i++) {
         myX[i]++;
      }
      boolean good = true;
      for (int i = 0; i < 4; i++) {
         if (myX[i] >= 10) {
            good = false;
         }
      }
      
      for(int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         if (x < 10) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
      }
      
      if (good == false) {
         for (int i = 0; i < 4; i++) {
            myX[i]--;
         }
      }
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 2;
      }
   }
   public void disappear(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 0;
      } 
   }
}