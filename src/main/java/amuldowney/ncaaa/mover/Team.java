package amuldowney.ncaaa.mover;

import javax.json.JsonObject;

/**
 * Created by Clues on 1/15/15.
 */
public class Team implements Comparable<Team>{

    private String _teamName;
    private Conference _conferenceIn;
    private int _conferenceId;
    private Record _record;
    private Standing _standing;
    private OVRRating _ovr;
    private OVRRating _offensiveOvr;
    private OVRRating _defensiveOVr;
    private OVRRating _specialTeamsOvr;

    public Team(JsonObject result) {
        _teamName = result.getString(MoverUtils.AllTeamsHeaders.name.toString());
        _conferenceId = result.getInt(MoverUtils.AllTeamsHeaders.conferenceId.toString());
        _record = new Record(result.getString(MoverUtils.AllTeamsHeaders.overallRec.toString(),"0-12"));
        _standing = new Standing(result.getString(MoverUtils.AllTeamsHeaders.coachPollRank.toString(), "UNR"));

        _ovr = new OVRRating(result.getString(MoverUtils.AllTeamsHeaders.overallRating.toString()));
        _offensiveOvr = new OVRRating(result.getString(MoverUtils.AllTeamsHeaders.offenseRating.toString()));
        _defensiveOVr = new OVRRating(result.getString(MoverUtils.AllTeamsHeaders.defenseRating.toString()));
        _specialTeamsOvr = new OVRRating(result.getString(MoverUtils.AllTeamsHeaders.specialTeamRating.toString()));

    }

    public void set_record(Record _record) {
        this._record = _record;
    }

    public Team(String _teamName, String _record, String _standing) {
        this._teamName = _teamName;
        this._record = new Record(_record);
        this._standing = new Standing(_standing);
    }

    public Standing get_standing() {
        return _standing;
    }

    public String get_teamName() {
        return _teamName;
    }

    public Conference get_conferenceIn() {
        return _conferenceIn;
    }

    public void set_conferenceIn(Conference _conferenceIn) {
        this._conferenceIn = _conferenceIn;
    }

    public OVRRating get_ovr() {
        return _ovr;
    }

    public OVRRating get_offensiveOvr() {
        return _offensiveOvr;
    }

    public OVRRating get_defensiveOVr() {
        return _defensiveOVr;
    }

    public OVRRating get_specialTeamsOvr() {
        return _specialTeamsOvr;
    }

    @Override
    public String toString(){
        return String.format("%s %s (%s)",_standing,_teamName,_record).trim();
    }


    public int get_conferenceId() {
        return _conferenceId;
    }

    @Override
    public int compareTo(Team that) {
        if(this._record.compareTo(that._record) == 0){
            return this.get_ovr().compareTo(that.get_ovr());
        } else {
            return this._record.compareTo(that._record);
        }
    }

    public class Record implements Comparable<Record>{
        public int wins;
        public int losses;

        public Record(String record){
            String[] splitRec = record.split("-");

            wins = Integer.parseInt(splitRec[0]);
            losses = Integer.parseInt(splitRec[1]);
        }

        public Record(int wins, int losses) {
            this.wins = wins;
            this.losses = losses;
        }

        @Override
        public String toString(){
            return String.format("%d-%d",wins,losses);
        }

        @Override
        public int compareTo(Record that) {
           return this.wins - that.wins;
        }
    }

    public class Standing implements Comparable<Standing>{
        public boolean isRanked = false;
        public Integer ranking;

        public Standing(String standing){
            if(!standing.equals("UNR")){
                isRanked = true;
                ranking = Integer.parseInt(standing.substring(1));
            }
        }

        @Override
        public String toString(){
            return isRanked ? "#" + ranking.toString() : "";
        }

        @Override
        public int compareTo(Standing that) {
            return this.ranking - that.ranking;
        }
    }
}

