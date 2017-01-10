package waifu;


import java.io.Serializable;

public class Week implements Serializable{
	private int[] week;
	public static final int MONDAY=0;
	public static final int TUESDAY=1;
	public static final int WEDNESDAY=2;
	public static final int THURSDAY=3;
	public static final int FRIDAY=4;
	public static final int SATURDAY=5;
	public static final int SUNDAY=6;

	public static final int[] DAYS={MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY ,SATURDAY ,SUNDAY};

	public Week(){
		week=new int[7];
	}
	public Week(int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday){
		week= new int[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
	}
	
	public int[] getWeek(){return week;}
	
	public void setDay(int day,int value){week[day]=value;}
	
	public int getDay(int day){return week[day];}
}
