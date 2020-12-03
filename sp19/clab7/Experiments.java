/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        public static void experiment1() {
            BST<Integer> bst = new BST<>();
            Random r = new Random();
            List<Double> avgDepthMy = new ArrayList<>();
            List<Integer> xValues = new ArrayList<>();
            List<Double> avgDepthOpt = new ArrayList<>();

            for (int i = 0; i <= 5000; i++) {
                int x = r.nextInt(5000);
                if (bst.contains(x)) {
                    continue;
                }

                bst.add(x);
                double thisY = bst.averageDepth();
                xValues.add(i);
                avgDepthMy.add(thisY);
                avgDepthOpt.add(ExperimentHelper.optimalAverageDepth(x));

            }
            XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
            chart.addSeries("myBST", xValues, avgDepthMy);
            chart.addSeries("optBST", xValues, avgDepthOpt);

            new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
    }
}
