public interface TetrisBlock {
   public abstract void move(int[][] arr);
   public abstract boolean checkStop(int[][] arr);
   public abstract void rotate(int[][] arr);
   public abstract int hardDown(int[][] arr);
   public abstract void moveLeft(int[][] arr);
   public abstract void moveRight(int[][] arr);
   public abstract void disappear(int[][] arr);
}