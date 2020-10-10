/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
// import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author nadtaalzahrani
 */


public class os1 {
    public int n; // processes 
    public int m; // resources
    public int Need[][], Allocate[][], Max[][], Avail[], Request[][];
    int NeedCheck = 0, AvailCheck = 0;

    public os1() throws Exception {
     
    }
 
    public void read()  {
       try{
        Scanner input = new Scanner(new BufferedReader(new FileReader(new File("input.txt"))));
        n = Integer.parseInt(input.nextLine());
        m = Integer.parseInt(input.nextLine());
        Need = new int[n][m];  
        Max = new int[n][m];
        Allocate = new int[n][m];
        Avail = new int[m];
      
        for (int i = 0; i < Allocate.length; i++) {
            String[] l = input.nextLine().trim().split(" ");
            for (int j = 0; j < l.length; j++) {
                Allocate[i][j] = Integer.parseInt(l[j]);
            }
        }
       
       input.nextLine();
        for (int i = 0; i < Max.length; i++) {
            String[] l = input.nextLine().trim().split(" ");
            for (int j = 0; j < l.length; j++) {
                Max[i][j] = Integer.parseInt(l[j]);

            }
        }
       input.nextLine();
        String[] l = input.nextLine().trim().split(" ");
        for (int j = 0; j < l.length; j++) {
            Avail[j] = Integer.parseInt(l[j]);
        }
     
        System.out.println("There are " + n + " processes in the system."+"\n");
        System.out.println("There are " + m + " resource types."+"\n");
  // -------------------------------------------------
        System.out.println("The Allocation Matrix is...");
         printletter(m);
        printMatrix(n , m ,Allocate );
// ---------------------------------------------------
        System.out.println("\n"+"The Max Matrix is...");
        printletter(m);
        printMatrix(n , m ,Max );   }
    
       catch(Exception ex){
        System.out.println("Error:the input file dose not exist"); } }

   public void computNeed(){
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Need[i][j] = Max[i][j] - Allocate[i][j];
            } }
        System.out.println("\n"+"The Need Matrix is...");
        printletter(m);
        printMatrix(n , m ,Need ); }
        
    
// all prints
public static void printletter(int m)
{
    char c = 'A';
    System.out.print("   ");
    for (int i = 0; i < m; i++) 
    {
         System.out.print(c + " ");
         c++;
    }
    System.out.println();    
}

public static void printMatrix( int n, int m , int [][] A)
{
    for (int i = 0 ; i < n ; i++ ) {
            System.out.print( i + ": ");
                for (int j = 0 ; j < m ; j++ )  {
                     System.out.print( A[i][j] + " ");
                }   
                System.out.println();
        }
    System.out.println();
}
public void printavailable ()
{ 
    char c = 'A';
    System.out.print("");
    for (int i = 0; i < m; i++) 
        {
            System.out.print(c + " ");
            c++;
        }
    System.out.println();
    for (int i = 0; i < Avail.length ; i++) 
        {
            System.out.print(Avail[i] + " ");  
        }
    System.out.println("\n");
}

public  boolean checkIsSafe () {   
    int [] work = new int [m];
    boolean [] finish = new boolean [n];
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < Avail.length ; j++) 
        {
            for (int k = 0; k < m; k++) 
            {
                work [i] = Avail[i] ;
                finish [j] = false;
                if ( work[i] >= Need[i][j] && finish[i] == false) {
                    work [i] += Allocate[i][j];
                    finish [i] = true;
                }
            }
        }
    }
    for (int i = 0; i < finish.length ; i++) 
    {
        if (finish [i] == true )
            return true;
    }
    System.out.println();
    return false;
}
public void Req(){
        System.out.println();
          Scanner sc = new Scanner(System.in);
             System.out.println("Is there any Request ?");
             String processNum = sc.nextLine();
            String[] NewReq = processNum.trim().split(" ");
       
        String Start=NewReq[0].substring(0, 1);
        int num = Integer.parseInt(Start); // convert to int
       
       int Req [] = new int[m];
        for (int j = 0, d = 1; j < m; j++, d++) {
            Req[j] = Integer.parseInt(NewReq[d]);  //request 
        }
        for (int j = 0; j < m; j++) {
            if (Req[j] <= Need[num][j]) {
                NeedCheck++;
            }
            if (Req[j] <= Avail[j]) {
                AvailCheck++;
            }   }
        if (NeedCheck == m && AvailCheck == m) {
            for (int i = 0; i < m; i++) {
                Allocate[num][i] += Req[i];
                Need[num][i] -= Req[i];
                Avail[i] = Avail[i] - Req[i];
            }
            System.out.println("\nTHE REQUEST CAN BE GRANTED!\n");
            System.out.println("The Available Vector is...");
            for (int i = 0; i < m; i++) {
                System.out.print("" + Avail[i] + " ");

            }
            System.out.println("");
        } else {
            if (NeedCheck != m  || AvailCheck != m) {
            System.out.println("THE REQUEST CANNOT BE GRANTED!"); }
         
        }  }   }
            



     
     
      
