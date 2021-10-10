public class StraightTetris implements TetrisBlock {
   private int[] myX;
   private int[] myY;
   private int rotate;
   public StraightTetris(int x) {
      myX = new int[]{x, x+1, x+2, x+3};
      myY = new int[]{21, 21, 21, 21};
      rotate = 0;
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
         arr[y][x] = 1;
      } 
   }
   public boolean checkStop(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         if (myY[i] <= 0) {
            return true;
         }
      } 
      if (rotate == 0) {
         for (int i = 0; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (arr[y-1][x] != 0) {
               return true;
            }
         } 
      }
      else {
         int x = myX[3];
         int y = myY[3];
         if (arr[y-1][x] != 0) {
            return true;
         }
      }
      return false;
   }
   public void rotate(int[][] arr) {
      int[] tempX = new int[4];
      int[] tempY = new int[4];
      
      for (int i = 0; i < 4; i++) {
         tempX[i] = myX[i];
         tempY[i] = myY[i];
         
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 0;
      } 
      
      if(rotate == 0) {
         rotate++;
         myX[0] += 1;
         myY[0] += 2;
         myX[1] += 0;
         myY[1] += 1;
         myX[2] -= 1;
         myY[2] += 0;
         myX[3] -= 2;
         myY[3] -= 1;
         
         boolean up = false;
         for (int i = 0; i < 4; i++) {
            if(myY[i] < 0) {
               up = true;
            }
         } 
         while(up) {
            for (int i = 0; i < 4; i++) {
               myY[i]++;
            } 
            up = false;
            for (int i = 0; i < 4; i++) {
               if(myY[i] < 0) {
                  up = true;
               }
            } 
         }
         
         boolean left = false;
         for (int i = 0; i < 4; i++) {
            if(myX[i] < 0) {
               left = true;
            }
         } 
         while(left) {
            for (int i = 0; i < 4; i++) {
               myX[i]++;
            } 
            left = false;
            for (int i = 0; i < 4; i++) {
               if(myX[i] < 0) {
                  left = true;
               }
            } 
         }
         
         boolean right = false;
         for (int i = 0; i < 4; i++) {
            if(myX[i] > 9) {
               right = true;
            }
         } 
         while(right) {
            for (int i = 0; i < 4; i++) {
               myX[i]--;
            } 
            right = false;
            for (int i = 0; i < 4; i++) {
               if(myX[i] > 9) {
                  right = true;
               }
            } 
         }
         
         boolean redo = false;
         //boolean goUp = false;
         //boolean goDown = false;
         for (int i = 0; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (arr[y][x] != 0) {
               redo = true;
            }
         } 
         if(redo) {
            rotate = 0;
            for (int i = 0; i < 4; i++) {
               myX[i] = tempX[i];
               myY[i] = tempY[i];
            }
         }
         
      }
      else {
         rotate = 0;
         myX[0] -= 1;
         myY[0] -= 2;
         myX[1] -= 0;
         myY[1] -= 1;
         myX[2] += 1;
         myY[2] -= 0;
         myX[3] += 2;
         myY[3] += 1;
         
         boolean up = false;
         for (int i = 0; i < 4; i++) {
            if(myY[i] < 0) {
               up = true;
            }
         } 
         while(up) {
            for (int i = 0; i < 4; i++) {
               myY[i]++;
            } 
            up = false;
            for (int i = 0; i < 4; i++) {
               if(myY[i] < 0) {
                  up = true;
               }
            } 
         }
         
         boolean left = false;
         for (int i = 0; i < 4; i++) {
            if(myX[i] < 0) {
               left = true;
            }
         } 
         while(left) {
            for (int i = 0; i < 4; i++) {
               myX[i]++;
            } 
            left = false;
            for (int i = 0; i < 4; i++) {
               if(myX[i] < 0) {
                  left = true;
               }
            } 
         }
         
         boolean right = false;
         for (int i = 0; i < 4; i++) {
            if(myX[i] > 9) {
               right = true;
            }
         } 
         while(right) {
            for (int i = 0; i < 4; i++) {
               myX[i]--;
            } 
            right = false;
            for (int i = 0; i < 4; i++) {
               if(myX[i] > 9) {
                  right = true;
               }
            } 
         }
         
         boolean redo = false;
         boolean goLeft = false;
         boolean goRight = false;
         int times = 0;
         for (int i = 0; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (arr[y][x] != 0) {
               if (i <= 1) {
                  goRight = true;
               }
               else {
                  goLeft = true;
               }
               break;
            }
         }      
         if(goRight) {
            while (goRight) {
               goRight = false;
               for (int i = 0; i < 4; i++) {
                  myX[i]++;
                  if (myX[i] > 9 || times == 3) {
                     redo = true;
                     break;
                  }
               } 
               if(redo) {
                  break;
               }
               for (int i = 0; i < 4; i++) {
                  int x = myX[i];
                  int y = myY[i];
                  if (arr[y][x] != 0) {
                     goRight = true;
                     times++;
                  }
               }
            }
         }
         if(goLeft) {
            while (goLeft) {
               goLeft = false;
               for (int i = 0; i < 4; i++) {
                  myX[i]--;
                  if (myX[i] < 0 || times == 3) {
                     redo = true;
                     break;
                  }
               } 
               if(redo) {
                  break;
               }
               for (int i = 0; i < 4; i++) {
                  int x = myX[i];
                  int y = myY[i];
                  if (arr[y][x] != 0) {
                     goLeft = true;
                     times++;
                  }
               }
            }
         }
      
         if(redo) {
            rotate = 1;
            for (int i = 0; i < 4; i++) {
               myX[i] = tempX[i];
               myY[i] = tempY[i];
            }
         }
         
      }
      for (int i = 0; i < 4; i++) {
         int x = myX[i];
         int y = myY[i];
         arr[y][x] = 1;
      } 
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
      
      if (rotate == 0) {
         int x = myX[0];
         int y = myY[0];
         if (x >= 0) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
      }
      else {
         for (int i = 0; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (x >= 0) {
               if (arr[y][x] != 0) {
                  good = false;
               }
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
         arr[y][x] = 1;
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
      
      if (rotate == 0) {
         int x = myX[3];
         int y = myY[3];
         if (x < 10) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
      }
      else {
         for (int i = 0; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (x < 10) {
               if (arr[y][x] != 0) {
                  good = false;
               }
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
         arr[y][x] = 1;
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