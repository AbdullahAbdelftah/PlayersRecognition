import java.awt.Point;
import java.util.Scanner;
interface IPlayersFinder {

    /**
     * Search for players locations at the given photo
     * @param photo
     *     Two dimension array of photo contents
     *     Will contain between 1 and 50 elements, inclusive
     * @param team
     *     Identifier of the team
     * @param threshold
     *     Minimum area for an element
     *     Will be between 1 and 10000, inclusive
     * @return
     *     Array of players locations of the given team
     */
    java.awt.Point[] findPlayerss(String[] photo, int team, int threshold,char f[][]);
}
class PlayersFinder implements IPlayersFinder {
    static int co[][]=new int[55][2];
    static int coRindex=0;
    static char team;
    static int thresh=0;

    static int minR=100,maxR=0,minC=100,maxC=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine();
        String[] s = sin.split(", ");
        int r=Integer.parseInt(s[0]),c=Integer.parseInt(s[1]);
        String inp[]=new String[r];
        for(int i=0;i<r;i++){
            inp[i]= sc.nextLine();
        }
        String q=sc.nextLine();
        team=q.charAt(0);
        int threshold=sc.nextInt();
        char[][] a =new char[inp.length][inp[0].length()];
        PlayersFinder p=new PlayersFinder();
        Point[] ll = p.findPlayerss(inp, 0, 0,a);

        //here we scan the array to find the required team color
        //when we find the color we use recursive fn to scan more team corlor so we can group them
        //mid-point is calculated using this equation: 2*(index)+1,since every index is 2 blocks of length
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a[0].length;j++){
                if(a[i][j]==team){
                    findPlayers(a,i,j);
                }
                if(thresh>=threshold){
                    co[coRindex][0]=((minC*2+1)+(maxC*2+1))/2;
                    co[coRindex][1]=((minR*2+1)+(maxR*2+1))/2;
                    coRindex++;
                }
                minR=100;
                maxR=0;
                minC=100;
                maxC=0;
                thresh=0;
            }
        }
        //here we take out the array elements in new one since the first one was having a large rows number above the constraint

        int z[][]=new int[coRindex][2];

        for(int i=0;i<coRindex;i++){
            for (int j=0;j<2;j++){
                z[i][j]=co[i][j];
            }
        }
        //then here we use sorting function made down below and in it we call function that swaps rows which is down below also
        sort(z);
        //printing results
        System.out.print("[");
        for(int i=0;i<coRindex;i++){
            System.out.print("(");
            for (int j=0;j<2;j++){
                if(j==0) {
                    System.out.print(z[i][j] + ", ");
                }
                else {
                    System.out.print(z[i][j]);
                }
            }
            if(i!=coRindex-1) {
                System.out.print("), ");
            }
            else{
                System.out.print(")");
            }
        }
        System.out.print("]");
    }
    static void findPlayers(char a[][],int r,int c){
        if(c<0 || c>=a[0].length || r<0 || r>=a.length || a[r][c]!=team){
            return;
        }

        thresh+=4;
        a[r][c]='0';
        if (r>=maxR){
            maxR=r;
        }
        if (r<=minR){
            minR=r;
        }
        if (c>=maxC){
            maxC=c;
        }
        if (c<=minC){
            minC=c;
        }
        //up
        findPlayers(a,r-1,c);
        //down
        findPlayers(a,r+1,c);
        //right
        findPlayers(a,r,c+1);
        //left
        findPlayers(a,r,c-1);
    }
    public static void swap(int a[][], int rowA, int rowB) {
        int tmpRow[] = a[rowA];
        a[rowA] = a[rowB];
        a[rowB] = tmpRow;
    }
    public static void sort(int a[][]){
        for(int j=0;j<coRindex-1;j++){
            for (int i=0;i<coRindex-1;i++){
                if(a[i][0]>a[i+1][0]){
                    swap(a,i,i+1);
                }
                else if(a[i][0]==a[i+1][0]){
                    if(a[i][1]>a[i+1][1]){
                        swap(a,i,i+1);
                    }
                }
            }
        }
    }

    @Override
    public Point[] findPlayerss(String[] photo, int team, int threshold,char f[][]) {
        for(int i=0;i<photo.length;i++){
            f[i]=photo[i].toCharArray();
        }
        return new Point[0];
    }
}



