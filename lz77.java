/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz77;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author EHAB &Omar
 */

public class LZ77 {
static char[] data={'a','b','a','a','b','a','b','a','a','b','b','b','b','b','b','b','b','b','b','b','b','a'};//18 char
static int index =0;
static ArrayList<Character> array = new ArrayList<Character>();
static void decompress(int u ,int l ,char c  ) {
	
		  
		if (l==0) {
				  array.add(c);
				  index++;		        		      
			}
			else if (l==1){
				
				array.add( array.get(index-u) );
			    index++;
			    array.add(c);
			    index++;
		
			}
			else if (l==2){
				array.add( array.get(index-u) );
			    index++;
				array.add( array.get(index-u) );
			    index++;
			    array.add(c);
			    index++;
			
				
			}
			else if (l==3){
				array.add( array.get(index-u) );
			    index++;
				array.add( array.get(index-u) );
			    index++;
				array.add( array.get(index-u) );
			    index++;
			    array.add(c);
			    index++;
			}
			       								
		
		}
static void print_decompress() {

	System.out.println(" "+ array);	
	
}

		
    /**
     * @param args the command line arguments
     */


    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input=new Scanner(System.in);
        int p=0, l=0, token=4, maxP=0, maxL=0, tagCount=1;
        char s='a';
        char[] result=new char[1];
       System.out.println("The Compression :");
       System.out.println("<"+0+","+0+","+data[0]+">");
        decompress(0,0,data[0]);
        for(int indix=1; indix<data.length;)
        {
            for(int i=0; i<indix;i++)
            {
                if(indix+3<=data.length-1 &&data[indix]==data[i] && data[indix+1]==data[i+1] &&data[indix+2]==data[i+2] &&i<=indix-3 )
                {
                    p=indix-i;
                    l=3;
                    s=data[indix+3];
                    token=1;
                }
                else if(indix+2<=data.length-1 &&data[indix]==data[i] && data[indix+1]==data[i+1] &&i<=indix-2 &&token!=1 )
                {
                    p=indix-i;
                    l=2;
                    s=data[indix+2];
                    token=2;
                }
                else if(indix+1<=data.length-1 &&data[indix]==data[i] &&i<=indix-1 &&token!=1 &&token!=2 )
                {
                    p=indix-i;
                    l=1;
                    s=data[indix+1];
                    token=3;
                }
                else if(indix<=data.length &&i<=indix &&token!=1 &&token!=2 &&token!=3)
                {
                    p=indix-i-1;
                    l=0;
                    s=data[indix];
                }
            }   
            System.out.println("<"+p+","+l+","+s+">");
            
            
            decompress(p,l,s);
    
     
            
            tagCount++;
            if(p/2>=maxP)
            {
                if(p%2!=0)
                {
                    maxP=(p/2)+1;
                }
            }
            if(l/2>=maxL)
            {
                if(l%2!=0)
                {
                    maxL=(l/2)+1;
                }
            }
            indix+=l+1;
            token=4;
        }
        print_decompress();
        System.out.println("data size = "+(8*data.length));
        System.out.println("compressed data size = "+(maxL+maxP+tagCount)*8);
        
        //decompress();
        
        
    } 
}