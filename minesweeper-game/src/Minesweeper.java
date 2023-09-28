import java.util.*;
//Değerlendirme Formu 5 :Proje Minesweeper sınıfı içinde tasarlandı
public class Minesweeper{
    int rowNum;
    int colNum;
    String [][] gameMap;
    String [][] minesMap;
    int minesNum;
    boolean isTrue;
    int row;
    int col;
    int count;
    Scanner input=new Scanner(System.in);
    //Değerlendirme Formu 7 : Matris boyutu kullanıcı tarafından belirleniyor.
    public Minesweeper(int rowNum,int colNum){
        this.rowNum=rowNum;
        this.colNum=colNum;
        this.minesNum=(rowNum*colNum)/4;
        this.gameMap=new String[rowNum][colNum];
        this.minesMap=new String[rowNum][colNum];
    }
    //Değerlendirme Formu 6 : Print metoduyla maplarin ekrana bastırılması sağlanmıştır.
    public void print(String[][] map){
        for (int i=0;i<this.rowNum;i++){
            for (int j=0;j<this.colNum;j++){
                System.out.print(" "+map[i][j]+" ");
            }
            System.out.println();
        }
    }
    //Değerlendirme Formu 6 : Map methoduyla map oluşturulmuştur.
    public void Maps(){
        for (int i=0;i<this.rowNum;i++){
            for (int j=0;j<this.colNum;j++) {
                this.gameMap[i][j]="-";
                this.minesMap[i][j]="-";
            }
        }
    }
    //Değerlendirme Formu 8 : Matrise uygun sayıda rastgele mayın yerleştirildi.
    public void randomMines(){
        int [] ranRow=new int[this.minesNum];
        int [] ranCol= new int[this.minesNum];
        for (int i=0;i<minesNum;i++){
            ranRow[i]=(int)(Math.random()*this.rowNum);
            ranCol[i]=(int) (Math.random()*this.colNum);
            for (int j=0;j<i;j++) {
                if (ranRow[j] == ranRow[i] && ranCol[j] == ranCol[i]) {
                    i--;
                }
            }
            this.minesMap[ranRow[i]][ranCol[i]]="*";
        }
    }
    //Değerlendirme Formu 11: Kullanıcı her hamle yaptığında oyun alanı güncelleniyor.
    public void run(){
        Maps();
        randomMines();
        print(this.minesMap);
        System.out.println("===================");
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");
        print(this.gameMap);
        isTrue=true;
        while (isTrue){
            //Değerlendirme Formu 9 :Kullanıcıdan işaretlemek istediği satır ve sütün bilgisi alınıyor.
            System.out.print("Satır giriniz: ");
            this.row=input.nextInt();
            System.out.print("Sütun giriniz: ");
            this.col=input.nextInt();
            System.out.println("=====================");
            //Değerlendirme Formu 15:Kullanıcının kazanma kaybetme durumunda uygun mesajlar kullanıcıya gösteriliyor.
            //Değerlendirme Formu 10 :Kullanıcı map uzunluğu dışında değer verdiğinin kontrolu yapılıp tekrar giriş yapması isteniyor.
            if (this.row<0 || this.row>=this.rowNum|| this.col<0 || this.col>=this.colNum){
                System.out.println("Map sınırları dışı değer girdiniz!!");
                continue;
            }
            //Değerlendirme Formu 13: Kullanıcı mayına bastığında oyunu kaybedeceği şekilde kontrol sağlanıyor.
            if (this.minesMap[row][col].equals("*")){
                System.out.println("Game Over");
                isTrue=false;
            }
            //Değerlendirme Formu 14: Seçilen tüm noktalar mayınsızsa oyunu kazanmanın kontrolü yapılıyor.
            else {
                check();
                print(this.gameMap);
                if (finish()){
                    System.out.println("Tebrikler oyunu kazandınız.");
                    isTrue=false;
                }
            }
        }
    }
    //Değerlendirme Formu 6 : Control metodu tanımlanmıştır.
    //Değerlendirme Formu 12:Girilen noktada mayın yoksa etrafında mayın sayısı veya 0 yazdırılıyor.
    public void check(){
        this.count=0;
        for (int i=(this.row-1);i<=this.row+1;i++){
            for (int j=this.col-1;j<=this.col+1;j++){
                if (i<0 || i>=this.rowNum|| j<0 || j>=this.colNum){
                    continue;
                }
                if (this.minesMap[i][j].equals("*")){
                    this.count++;
                }
            }
        }
        this.gameMap[row][col]=String.valueOf(this.count);
        this.minesMap[row][col]=String.valueOf(this.count);
    }
    //Değerlendirme Formu 6 :Oyun bitiş metodu tanımlanmıştır.
    //Değerlendirme Formu 14: Seçilen tüm noktalar mayınsızsa oyunu kazanmanın kontrolü yapılıyor.
    public boolean finish(){
        for (int i=0;i<this.rowNum;i++){
            for (int j=0;j<this.colNum;j++){
                if (this.minesMap[i][j].equals("-")){
                    return false;
                }
            }
        }
        return true;
    }
}
