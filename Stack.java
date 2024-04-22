public class Stack {
    private int mSize;
    private int [] stackArray;
    private int top;
    public Stack(int m){
        this.mSize = m;
        stackArray = new int[mSize];
        top = -1;
    }
    public void addElement(int element){
        stackArray[++top] = element;
    }
    public int deleteElement(){
        return stackArray[top--];
    }
    public int readTop(){
        return stackArray[top];
    }
    public boolean isEmpty(){
        return (top == -1);
    }
    public boolean isFull(){
        return (top == mSize - 1);
    }
}
class AwesomeStack {
    public static void main(String[] args){
        Stack mStack = new Stack(10);

        mStack.addElement(23);
        mStack.addElement(45);
        mStack.addElement(3);
        mStack.addElement(53);

        mStack.deleteElement();

        System.out.println("Стек: ");

        while (!mStack.isEmpty()){
            int value = mStack.deleteElement();
            System.out.println(value);
        }
    }
}