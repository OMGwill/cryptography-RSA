import java.math.BigInteger;

public class RSA
{
    static int[] ptxt = new int[100];
    static int[] ctxt = new int[100];
    static int[] decrypted = new int[100];
    
    public static int gcd(int a, int b){
        if(a == 0)
            return b;
            
        return gcd(b%a, a);
    }
    
    public static int mul_inverse(int a, int m ){
        a %= m;
        
        for(int i = 1; i < m; i ++){
            if((a*i) % m == 1){
                return i;
            }
        }
        
        return 0;
    }
    
    public static void stringToint(String s){
        String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 .,;?";
        int[] a = new int[100];
        int count = 0;
        
        for(int i = 0; i < s.length(); i++){
            if(i%2 == 0){
                
                a[i] = alpha.indexOf(s.charAt(i)) * 100;
            } else{
                a[i-1] =a[i-1] + alpha.indexOf(s.charAt(i));
            }   
        }
       
        for(int i = 0; i < s.length(); i+=2){
            ptxt[count] = a[i];
            count++;
        }
        
   }
   
   public static void encrypt(int e, int n){
       for(int i = 0; i < ptxt.length; i++){
           
           BigInteger num = new BigInteger(Integer.toString(ptxt[i]));
           BigInteger exp = new BigInteger(Integer.toString(e));
           BigInteger m = new BigInteger(Integer.toString(n));
           
           num = num.modPow(exp,m);
           
           ctxt[i] = num.intValue();
        }
    }
    
    public static void decrypt(int d, int n){
       for(int i = 0; i < ptxt.length; i++){
           BigInteger num = new BigInteger(Integer.toString(ctxt[i]));
           BigInteger exp = new BigInteger(Integer.toString(d));
           BigInteger m = new BigInteger(Integer.toString(n));
           
           num = num.modPow(exp,m);
           
           decrypted[i] = num.intValue();
        }
    }
    
    public static void main(){
        
        
        String plaintext= "How are you?";
        String plaintext2= "Public key cryptography.";
        int p = 73;
        int q = 151;
        int count = 0;
        int[] e = new int[5];
        int[] d = new int[5];
        
        //System.out.println(alpha.indexOf(plaintext.charAt(0)));
        
        //calculate n and phi(n)
        int n = p * q;
        int phi = (p-1) * (q-1);
        
        System.out.println("RSA Key information:");
        System.out.println("Phi: " + phi);
        System.out.println("n: " + n);
        
        
        //calculate e's
        for(int i = 2; i< phi; i++){
            if(gcd(i,phi) == 1){
                e[count] = i;
                
                count++;
                
                if(count > 4){
                    break;
                }
            }
       
        }
        
        System.out.print("A list of five e's: ");
        for(int i = 0; i < 5; i++){
            System.out.print(e[i] + " ");
        }
        System.out.println();
        
        
        //calculate d's
        for(int i = 0; i < 5; i++){
            d[i] = mul_inverse(e[i],phi);
        }
        
        System.out.print("A list of five d's: ");
        for(int i = 0; i < 5; i++){
            System.out.print(d[i] + " ");
        }
        System.out.println();
        
        //convert string to ints
        stringToint(plaintext);
        
        System.out.println();
        System.out.println();
        
        System.out.println("Original plaintext: " + plaintext);
        System.out.println();
        /////////////////////////////////////////////////////////////
        //key1
        System.out.println("Public key: (" + e[0] + "," + n + ")");
        
        
        //encryption with first key
        for(int i = 0; i < plaintext.length()/2; i++){
            encrypt(e[0],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[0] + "," + n + ")");
        //decryption with first key
        for(int i = 0; i < plaintext.length()/2; i++){
            decrypt(d[0],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        ////////////////////////////////////////////////////
        //key2
        System.out.println("Public key: (" + e[1] + "," + n + ")");
        
        
        //encryption with second key
        for(int i = 0; i < plaintext.length()/2; i++){
            encrypt(e[1],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[1] + "," + n + ")");
        //decryption with second key
        for(int i = 0; i < plaintext.length()/2; i++){
            decrypt(d[1],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        ///////////////////////////////////////////////////////////
        //key3
        System.out.println("Public key: (" + e[2] + "," + n + ")");
        
        
        //encryption with third key
        for(int i = 0; i < plaintext.length()/2; i++){
            encrypt(e[2],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[2] + "," + n + ")");
        //decryption with third key
        for(int i = 0; i < plaintext.length()/2; i++){
            decrypt(d[2],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        ////////////////////////////////////////////////////////
        //key4
        System.out.println("Public key: (" + e[3] + "," + n + ")");
        
        
        //encryption with fourth key
        for(int i = 0; i < plaintext.length()/2; i++){
            encrypt(e[3],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[3] + "," + n + ")");
        //decryption with fourth key
        for(int i = 0; i < plaintext.length()/2; i++){
            decrypt(d[3],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        //////////////////////////////////////////////////////////
        //key5
        System.out.println("Public key: (" + e[4] + "," + n + ")");
        
        
        //encryption with fifth key
        for(int i = 0; i < plaintext.length()/2; i++){
            encrypt(e[4],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[4] + "," + n + ")");
        //decryption with fifth key
        for(int i = 0; i < plaintext.length()/2; i++){
            decrypt(d[4],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //test2
        //convert string to ints
        stringToint(plaintext2);
        
        System.out.println();
        System.out.println();
        
        System.out.println("Original plaintext2: " + plaintext2);
        System.out.println();
        /////////////////////////////////////////////////////////////
        //key1
        System.out.println("Public key: (" + e[0] + "," + n + ")");
        
        
        //encryption with first key
        for(int i = 0; i < plaintext2.length()/2; i++){
            encrypt(e[0],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[0] + "," + n + ")");
        //decryption with first key
        for(int i = 0; i < plaintext2.length()/2; i++){
            decrypt(d[0],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        ////////////////////////////////////////////////////
        //key2
        System.out.println("Public key: (" + e[1] + "," + n + ")");
        
        
        //encryption with second key
        for(int i = 0; i < plaintext2.length()/2; i++){
            encrypt(e[1],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[1] + "," + n + ")");
        //decryption with second key
        for(int i = 0; i < plaintext2.length()/2; i++){
            decrypt(d[1],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        ///////////////////////////////////////////////////////////
        //key3
        System.out.println("Public key: (" + e[2] + "," + n + ")");
        
        
        //encryption with third key
        for(int i = 0; i < plaintext2.length()/2; i++){
            encrypt(e[2],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[2] + "," + n + ")");
        //decryption with third key
        for(int i = 0; i < plaintext2.length()/2; i++){
            decrypt(d[2],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        ////////////////////////////////////////////////////////
        //key4
        System.out.println("Public key: (" + e[3] + "," + n + ")");
        
        
        //encryption with fourth key
        for(int i = 0; i < plaintext2.length()/2; i++){
            encrypt(e[3],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[3] + "," + n + ")");
        //decryption with fourth key
        for(int i = 0; i < plaintext2.length()/2; i++){
            decrypt(d[3],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        //////////////////////////////////////////////////////////
        //key5
        System.out.println("Public key: (" + e[4] + "," + n + ")");
        
        
        //encryption with fifth key
        for(int i = 0; i < plaintext2.length()/2; i++){
            encrypt(e[4],n);
        }
        
        System.out.print("Ciphertext: ");        
        //print values
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(ctxt[i] + " ");
        }
        System.out.println();
        
        
        System.out.println("Private key: (" + d[4] + "," + n + ")");
        //decryption with fifth key
        for(int i = 0; i < plaintext2.length()/2; i++){
            decrypt(d[4],n);
        }
        
        System.out.print("Plaintext: ");
        for(int i = 0; i < plaintext2.length()/2; i++){
            System.out.print(decrypted[i] + " ");
        }
        System.out.println();
        System.out.println();
    }
}
