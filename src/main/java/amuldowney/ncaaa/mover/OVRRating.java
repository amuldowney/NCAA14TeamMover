package amuldowney.ncaaa.mover;

public class OVRRating implements Comparable<OVRRating>{
    private String ovr;

    public OVRRating(String string) {
        ovr = string;
    }

    public String getOvr() {
        return ovr;
    }

    public char getOVRLetter(){
        return ovr.charAt(0);
    }

    public char getOVRSign(){
        if (ovr.length() > 1){
            return ovr.charAt(1);
        } else{
            return '/';
        }
    }
    private String signPrecedence = "+/-";

    public int getSignPrecedence(){
        return signPrecedence.indexOf(this.getOVRSign());
    }

    @Override
    public int compareTo(OVRRating that) {
        if(this.getOVRLetter() == that.getOVRLetter()){
            return this.getSignPrecedence() - that.getSignPrecedence();
        } else {
            return this.getOvr().compareTo(that.getOvr());
        }
    }

    @Override
    public String toString() {
        return getOvr();
    }
}
