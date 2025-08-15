package gpacalculator;

public class Main {
    public static void main(String[] args) {
        Student st = new Student("Soumik Chakraborty","25500121116",8);

        double[] sgpaValues = {9.54, 9.85, 8.86, 8.0, 8.46, 8.55, 9.0, 9.0};
        for(int i = 0;i<sgpaValues.length;i++){
            st.sgpa[i] = sgpaValues[i];
        }
        System.out.println("----- YGPA Results -----");
        String[] ygpa = st.calculateYGPAs();
        for(String y : ygpa){
            System.out.println(y);
        }
        System.out.println("\nFinal DGPA : " + String.format("%.2f",st.calculateDGPA()));
    }
}
