package gpacalculator;

public class Student {
    public String name;
    public String roll;
    public double[] sgpa;

    public Student(String name,String roll, int sems){
        this.name = name;
        this.roll = roll;
        this.sgpa = new double[sems];

    }
    private double[] getYGPA(){
            double[] ygpa = new double[4];

            for(int i = 0;i<4;i++){
                int sem1 = 2*i;
                int sem2 = sem1 + 1;

                double sum = sgpa[sem1]+sgpa[sem2];
                ygpa[i] = sum/2;
            }
        return ygpa;

    }

    public String[] calculateYGPAs(){
        double[] values = getYGPA();
        String[] ygpas = new String[4];

        for(int i = 0;i< 4;i++){
            ygpas[i] = String.format("Year %d YGPA : %.2f",i+1,values[i]);
        }
        return ygpas;
    }

    public double calculateDGPA(){
        double[] ygpa = getYGPA();
        return (ygpa[0]+ygpa[1]+1.5*ygpa[2]+1.5*ygpa[3])/5.0;
    }
}

