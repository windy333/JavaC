package object;

public class CloneConstructorExample{
   /*
   使用 clone() 方法来拷贝一个对象既复杂又有风险，它会抛出异常，并且还需要类型转换。
    Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工
    厂来拷贝一个对象。
    */

    private int[] arr;
    public CloneConstructorExample(){
        arr = new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i]=i;
        }
    }

    public CloneConstructorExample(CloneConstructorExample cloneConstructorExample) {
        arr = new int[cloneConstructorExample.arr.length];
        for (int i = 0; i < cloneConstructorExample.arr.length; i++) {
            arr[i] = cloneConstructorExample.arr[i];
        }

    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }


    public static void main(String ar[]){
        CloneConstructorExample e1=new CloneConstructorExample();
        CloneConstructorExample e2=new CloneConstructorExample(e1);
        e1.set(2,33);
        System.out.println(e2.get(2)); // 2

    }
}