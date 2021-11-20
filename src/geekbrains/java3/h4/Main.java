package geekbrains.java3.h4;

public class Main {
    final char[] charOrder = new char[] {'A','B','C','A','B','C','A','B','C'};
    Integer currentChar = 0;
    public static void main(String[] args) {
	// write your code here
        new Main();
    }
    public Main(){
        new Thread(new Printer('C')).start();
        new Thread(new Printer('A')).start();
        new Thread(new Printer('B')).start();
    }
    private  class Printer implements Runnable{
        private final char letter;
        public Printer(char letter){
            this.letter = letter;
        }
        @Override
        public void run() {
            print();
        }
        public synchronized void print(){
            synchronized (charOrder){
                while(true){
                while(currentChar < charOrder.length
                        && charOrder[currentChar] != letter) {
                    try{
                        charOrder.wait();
                    }catch (InterruptedException exception){
                        exception.printStackTrace();
                    }
                }
                if(currentChar==charOrder.length)
                    break;
                System.out.print(letter);
                currentChar++;
                charOrder.notifyAll();
                }
            }

        }
    }
}
