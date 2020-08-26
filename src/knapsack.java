import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class knapsack{

    public void print(int[][] matrix, int row){
        System.out.print("Memoization table, Row "+row+" completed\n");
        System.out.print("                        Weights--->                        \n            ");
        for(int x=0;x<matrix[0].length;x++){
            System.out.printf("%10s",x);
        }
        System.out.print("\n----------------------------------------------------------------------------------\n");
        String sets="";
        for(int y=0; y<matrix.length;y++){
            System.out.printf("{%10s}",sets);
            sets=sets+Integer.toString(y+1);
            for(int z=0; z<matrix[0].length; z++){
                System.out.printf("%10d",matrix[y][z]);
            }
            System.out.print("\n");
        }

        System.out.println("\n\n");

    }


    public int[][] knapsackAlgorithm(ArrayList<Items> i, int weight){
        //bookkeeping array
        int[][] book= new int[i.size()+1][weight+1];
        //output statement
        System.out.println("Solving knapsack weight capacity "+weight+", with "+i.size()+" items");
        //initiate a global array M[0,n][0,W]
        //n is the size of the arraylist items and W is the weight limit
        int[][] matrix= new int[i.size()+1][weight+1];
        //complete row 0
        for (int w=0; w<weight;w++){
            matrix[0][w]=0;
            book[0][w]=0;

        }
        //set all the entries to -1
        for (int a=1;a<i.size()+1; a++){
            for(int b=0; b<weight+1; b++){
                matrix[a][b]=-1;
            }
        }
        print(matrix, 0);
        for(int a=1;a<i.size()+1; a++){
            for (int b=0; b<weight+1; b++){
                if(i.get(a-1).getWeight()>b){
                    matrix[a][b]=matrix[a-1][b];
                    book[a][b]=0;

                }
                else{
                    if(matrix[a-1][b]>(matrix[a-1][(b-i.get(a-1).getWeight())]+i.get(a-1).getValue())) {
                        matrix[a][b] = matrix[a - 1][b];
                    }


                    else {
                        matrix[a][b] = matrix[a - 1][(b - i.get(a - 1).getWeight())] + i.get(a - 1).getValue();
                        book[a][b]=1;
                    }

                }
            }
            print(matrix,a);

        }
        System.out.println("Knapsack with weight capacity "+weight+" has optimal value: "+matrix[i.size()][weight]);
        System.out.println("______Knapsack Contains_________");
        String solution="";

        for(int s=0; s<i.size()+1;s++) {
            int prev = book[i.size() - s][weight - (i.get(i.size() - 1).getWeight())];
            if(prev==0)
                break;

            weight = weight - (i.get(i.size() - 1).getWeight());
            solution = solution + Integer.toString(i.size()-s);
            System.out.println("Item "+ (i.size()-s)+"  (Value = "+(i.get(i.size()-1-s).getValue())+" , Weight = "+(i.get(i.size()-1-s).getWeight())+" )");
        }




        return matrix;
    }





    public static void main(String[] args) {
        //from the input file get the maximum weight of the object
        int maxWeight = 6;
        //from the input file get the values, weights of the objects and push them into an arraylist
        ArrayList<Items> items = new ArrayList<Items>();
        Items i1 = new Items();
        i1.setItemNum(1);
        i1.setValue(6);
        i1.setWeight(5);
        Items i2 = new Items();
        i2.setItemNum(2);
        i2.setValue(18);
        i2.setWeight(2);
        Items i3 = new Items();
        i3.setValue(2);
        i3.setWeight(1);
        i3.setItemNum(3);
        items.add(i1);
        items.add(i2);
        items.add(i3);

        knapsack k = new knapsack();
        k.knapsackAlgorithm(items, maxWeight);

    }

}

class Items{
    private int itemNum;
    private int value;
    private int weight;

    public void setItemNum(int itemNum) {
        this.itemNum=itemNum;
    }

    public void setValue(int value){
        this.value=value;
    }

    public void setWeight(int weight){
        this.weight=weight;
    }

    public int getItemNum(){
        return itemNum;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}


