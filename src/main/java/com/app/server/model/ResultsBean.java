public class ResultsBean implements Serializable {

    public boolean isInside(int x, float y, float r) {
        return (x >= 0 && y >= 0 && x <= r && y <= r/2 ) || (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) || (x >= 0 && y <= 0 && y >= x - r/2);
    }
}