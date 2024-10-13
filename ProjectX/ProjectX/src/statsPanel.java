import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class statsPanel extends JPanel implements ActionListener {
    static ArrayList<Integer> population;
    final int PANEL_WIDTH = 1000;
    final int PANEL_HEIGTH = 800;
    Image peter;
    Timer timer;
    int xVelocity = 1;
    int yVelocity = 1;
    int x=0;
    int y=0;
    static int xAxis;
    static float yAxis;
    static Main m;
    ArrayList<Integer> indexes=new ArrayList<>();
    ArrayList<Integer> popcopy=new ArrayList<>();
    
    

    statsPanel(ArrayList<Integer> pop, Main m1) {
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGTH));
        //this.setBackground(new Color(0xFFEDEBDE));
        //this.setBounds(100,10,100,100);
        population= pop;
        m=m1;

        //population.sort(Comparator.reverseOrder());
        yAxis = (float)350/(float)(population.get(0));
        peter = new ImageIcon("gif.png").getImage();
        timer = new Timer(5, this);
        timer.start();


    }
    public void normalizevals(ArrayList<Integer> populations){
        popcopy.addAll(populations);


        int max = 0;
        int min = 0;

        for (int i:populations ){
            if (i>max){
                max = i;
            }
            if (i<min){
                min = i;
            }
        }
        int dif = max-min;
        //System.out.println(dif);
        for (int i=0; i<populations.size();i++){
            float x = ((float)populations.get(i)-min)/dif;
            populations.set(i, (int) ( 300*x));
        }

    }
    public ArrayList<int[]> fixlist(ArrayList<Integer> k){
        int b = 0;
        ArrayList<int[]> myarr = new ArrayList<>();
        for(int i=0;i<k.size();i++){
            if ((k.get(i)!= b)|| (i == k.size()-1)){
                indexes.add(i);
                myarr.add(new int[]{i, k.get(i)});
                b = k.get(i);
            }

        }
        return myarr;
    }
    public void paint(Graphics var1) {
        
        
        Graphics2D var2 = (Graphics2D) var1;
        var2.setPaint(new Color(2499620));
        var2.setFont(new Font("Courier New", 1, 15));
        var2.drawString("population", 15, 35);
        var2.setPaint(new Color(2499620));
        var2.setFont(new Font("Courier New", 1, 15));
        var2.drawString("time", 321, 375);
        var2.setPaint(new Color(2499620));
        var2.setFont(new Font("Courier New", 1, 32));
        boolean var3 = false;
        this.normalizevals(population);
        ArrayList var4 = this.fixlist(population);
        xAxis = 800 / (population.size() + 1);
        int var5 = 0;
    
        for (int var6 = 0; var6 < var4.size() - 1; ++var6) {
            int var7 = ((int[]) var4.get(var6))[0];
            int var8 = ((int[]) var4.get(var6))[1];
            int var9 = ((int[]) var4.get(var6 + 1))[0];
            int var10 = ((int[]) var4.get(var6 + 1))[1];
            var2.setStroke(new BasicStroke(3.0F));
            var2.setColor(new Color(-62756231, true));
            int[] var11 = new int[]{xAxis * var7 + 40, xAxis * var7 + 40, xAxis * var9 + 40, xAxis * var9 + 40};
            int[] var12 = new int[]{350, 350 - var8, 350 - var10, 350};
            var2.fillPolygon(var11, var12, 4);
            var2.setColor(new Color(0));
            Line2D.Double var13 = new Line2D.Double((double) (xAxis * var7 + 40), (double) (350 - var8), (double) (xAxis * var9 + 40), (double) (350 - var10));
            var2.draw(var13);
            int var14 = xAxis * var7 + 40 - 3;
            int var15 = 350 - var8 - 3;
            var2.fillOval(var14, var15, 6, 6);
            var2.setPaint(new Color(0));
            var2.setFont(new Font("Courier New", 1, 15));
            var2.drawString("Population: ", 800, 25);
            var2.drawString("Time: ", 920, 25);
            var2.setFont(new Font("Courier New", 1, 15));
            var2.drawString(String.valueOf(this.popcopy.get((Integer) this.indexes.get(var6))), 804, var5 + 45);
            var2.setFont(new Font("Courier New", 1, 15));
            if (var6 == 0) {
                var2.drawString("0", 924, var5 + 45);
            } else {
                var2.drawString(String.valueOf(var7*20), 924, var5 + 45);
            }
    
            var5 += 20;
        }
    
        // Stampa percentuali finali
        float percentFastMen = (float) Main.fastmen / Main.gesamt * 100;
        float percentFastWomen = (float) Main.fastwomen / Main.gesamt * 100;
        float percentSlowMen = (float) Main.slowmen / Main.gesamt * 100;
        float percentSlowWomen = (float) Main.slowwomen / Main.gesamt * 100;
    
        var2.setPaint(new Color(0));
        var2.setFont(new Font("Courier New", 1, 17));
        var2.drawString("Final: ", 180, 450);
        var2.drawString(String.format("Fast Men: %.2f%%", percentFastMen), 180, 470);
        var2.drawString(String.format("Fast Women: %.2f%%", percentFastWomen), 180, 490);
        var2.drawString(String.format("Slow Men: %.2f%%", percentSlowMen), 180, 510);
        var2.drawString(String.format("Slow Women: %.2f%%", percentSlowWomen), 180, 530);
        
        // Valori iniziali
        var2.drawString("Initial: ", 460, 450);
        var2.drawString(String.format("Fast Men: %.2f%%", Main.initialFastMen), 460, 470);
        var2.drawString(String.format("Fast Women: %.2f%%",Main.initialFastWomen), 460, 490);
        var2.drawString(String.format("Slow Men: %.2f%%",Main.initialSlowMen), 460, 510);
        var2.drawString(String.format("Slow Women: %.2f%%", Main.initialSlowWomen), 460, 530);
    
        var2.drawString("A value of: " + Main.a, 730, 470);
        var2.drawString("B value of: " + Main.b, 730, 520);
        var2.drawString("C value of : " + Main.c, 730, 570);
    
        if (Main.none) {
            var2.drawString("Dominant gene: None", 730, 620);
        } else if (Main.dominantgene) {
            var2.drawString("Dominant gene: Fast", 730, 620);
        } else {
            var2.drawString("Dominant gene: Slow", 730, 620);
        }
    
        var2.setPaint(new Color(2499620));
        var2.setStroke(new BasicStroke(3.0F));
        var2.drawLine(40, 45, 40, 350);
        var2.setPaint(new Color(2499620));
        var2.setStroke(new BasicStroke(3.0F));
        var2.drawLine(40, 350, 690, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x>PANEL_WIDTH-peter.getWidth(null) || x<0){
            xVelocity = xVelocity * -1;
        }
        if(y>PANEL_HEIGTH-500-peter.getHeight(null) || y<0){
            yVelocity = yVelocity * -1;
        }
        x = x + xVelocity;
        y = y + yVelocity;
        repaint();
    }
}