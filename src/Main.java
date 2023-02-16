public class Main {
    public static void main(String[] args) {
        String[] bacteriaNamn = {"Bacillus cereus", "Staphylococcus aureus", "Escherichia coli", "Salmonella enterica", "Listeria monocytogenes", "Bakus smällningus"};
        double[] growthRates = {0.3, 0.4, 0.5, 0.6, 0.7, 1.1};
        double[] minGrowthRates = {0.25, 0.35, 0.45, 0.55, 0.65, 1.05};
        double[] maxGrowthRates = {0.35, 0.45, 0.55, 0.65, 0.75, 1.15};
        int[] daysTo50Percentages = {10, 8, 6, 5, 4, 2};
        String dominantBacteria = null;

        for(int i = 0; i < bacteriaNamn.length; i++) {
            String bacteria = bacteriaNamn[i];
            double growthRate = growthRates[i];
            double minGrowthrate = minGrowthRates[i];
            double maxGrowthrate = maxGrowthRates[i];
            int daysTo50 = daysTo50Percentages[i];
            int initialPopulation = 1;
            int currentPopulation = initialPopulation;
            int maxPopulation = 1_000_000_000;
            int day = 0;
            double highestGrowthRate = 0.0;
            double currentPercent = 0.0;

            while(true) {
                double growthFactor = 1 + growthRate;
                double dailyGrowth = currentPopulation * growthFactor;
                currentPercent = currentPercent + (currentPopulation / (Math.floor(dailyGrowth) + currentPopulation));
                currentPopulation = (int) Math.floor(dailyGrowth) + currentPopulation;
                if (growthRate < minGrowthrate || growthRate > maxGrowthrate) {
                    System.out.println("Tillväxttakten är utanför det tillåtna intervallet för " + bacteria + ".");
                    break;
                }
                if(currentPopulation > maxPopulation) {
                    System.out.println(bacteria + " har taig över labbet!");
                    break;
                }
                else if (currentPopulation < ((double) initialPopulation / 2)) {
                    System.out.println(bacteria + " har dött en långsam död.");
                    break;
                }
                else if (day > daysTo50) {
                    System.out.println(bacteria + " har inte nått tillväxttakten på " + daysTo50 + " dagar.");
                    break;
                }else if (currentPercent > 0.5) {
                    System.out.println(bacteria + " har en tillväxttakt högre än 50% efter " + day + " dagar.");
                    break;
                }else if(currentPercent == 0.5) {
                    System.out.println("Experimentet med " + bacteria + " lyckades efter " + day + " dagar.");
                    break;
                }
                if (growthRate > highestGrowthRate) {
                    highestGrowthRate = growthRate;
                    dominantBacteria = bacteria;
                }
                System.out.println(bacteria + " tillväxttakt: " + currentPercent + " nuvarande population: " + currentPopulation + " dagar: " + day);
                day++;
            }
        }
        System.out.println("Experimentet avslutat");
        System.out.println("Dominant bakterie: " + dominantBacteria);
    }
}