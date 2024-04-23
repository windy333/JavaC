public class Test {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // 假设缓冲区容量为5

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
