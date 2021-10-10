public class ZTetris implements TetrisBlock {
   private int[] myX;
   private int[] myY;
   private int rotate;
   public ZTetris(int x) {
      myX = new int[]{x, x+1, x+1, x+2};
      myY = new int[]{22, 22, 21, 21};
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
         arr[y][x] = 6;
      } 
   }
   public boolean checkStop(int[][] arr) {
      for (int i = 0; i < 4; i++) {
         if (myY[i] <= 0) {
            return true;
         }
      } 
      
      if (rotate == 1) {
         int x = myX[0];
         int y = myY[0];
         if (arr[y-1][x] != 0) {
            return true;
         }
         x = myX[2];
         y = myY[2];
         if (arr[y-1][x] != 0) {
            return true;
         }
      }
      else if (rotate == 0) {
         for (int i = 2; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (arr[y-1][x] != 0) {
               return true;
            }
         } 
         int x = myX[0];
         int y = myY[0];
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
         myX[0] += 0;
         myY[0] -= 1;
         myX[1] -= 1;
         myY[1] += 0;
         myX[2] += 0;
         myY[2] += 1;
         myX[3] -= 1;
         myY[3] += 2;
         
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
            for (int i = 0; i < 4; i++) {
               myX[i]++;
               if (myX[i] > 9) {
                  redo = true;
                  break;
               }
            } 
            for (int i = 0; i < 4; i++) {
               int x = myX[i];
               int y = myY[i];
               if (redo == false) {
                  if (arr[y][x] != 0) {
                     redo = true;
                     break;
                  }
               }
            }
         }
         if(goLeft) {
            for (int i = 0; i < 4; i++) {
               myX[i]--;
               if (myX[i] < 0) {
                  redo = true;
                  break;
               }
            } 
            for (int i = 0; i < 4; i++) {
               int x = myX[i];
               int y = myY[i];
               if (redo == false) {
                  if (arr[y][x] != 0) {
                     redo = true;
                     break;
                  }
               }
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
      
      else if(rotate == 1) {
         rotate = 0;
         myX[0] -= 0;
         myY[0] += 1;
         myX[1] += 1;
         myY[1] -= 0;
         myX[2] -= 0;
         myY[2] -= 1;
         myX[3] += 1;
         myY[3] -= 2;
         
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
         boolean goUp = false;
         
         for (int i = 0; i < 4; i++) {
            int x = myX[i];
            int y = myY[i];
            if (arr[y][x] != 0) {
               if (i == 0) {
                  goRight = true;
               }
               else if (i == 3) {
                  goLeft = true;
               }
               else {
                  goUp = true;
               }
               break;
            }
         }      
         if(goRight) {
            for (int i = 0; i < 4; i++) {
               myX[i]++;
               if (myX[i] > 9) {
                  redo = true;
                  break;
               }
            } 
            for (int i = 0; i < 4; i++) {
               int x = myX[i];
               int y = myY[i];
               if (redo == false) {
                  if (arr[y][x] != 0) {
                     redo = true;
                     break;
                  }
               }
            }
         }
         if(goLeft) {
            for (int i = 0; i < 4; i++) {
               myX[i]--;
               if (myX[i] < 0) {
                  redo = true;
                  break;
               }
            } 
            for (int i = 0; i < 4; i++) {
               int x = myX[i];
               int y = myY[i];
               if (redo == false) {
                  if (arr[y][x] != 0) {
                     redo = true;
                     break;
                  }
               }
            }
            
         }    
         if(goUp) {
            for (int i = 0; i < 4; i++) {
               myY[i]++;
               if (myY[i] > 19) {
                  redo = true;
                  break;
               }
            } 
            for (int i = 0; i < 4; i++) {
               int x = myX[i];
               int y = myY[i];
               if (redo == false) {
                  if (arr[y][x] != 0) {
                     redo = true;
                     break;
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
         arr[y][x] = 6;
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
         x = myX[2];
         y = myY[2];
         if (x >= 0) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
      }
      else if (rotate == 1) {
         int x = myX[0];
         int y = myY[0];
         if (x >= 0) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
         x = myX[1];
         y = myY[1];
         if (x >= 0) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
         x = myX[3];
         y = myY[3];
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
         arr[y][x] = 6;
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
         int x = myX[1];
         int y = myY[1];
         if (x < 10) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
         x = myX[3];
         y = myY[3];
         if (x < 10) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
      
      }
      else if (rotate == 1) {
         int x = myX[0];
         int y = myY[0];
         if (x < 10) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
         x = myX[2];
         y = myY[2];
         if (x < 10) {
            if (arr[y][x] != 0) {
               good = false;
            }
         }
         x = myX[3];
         y = myY[3];
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
         arr[y][x] = 6;
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