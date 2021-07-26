//Edison Enerio
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.time.Duration;
import java.text.DecimalFormat;

public class EnerioEdison3{
  
 //Movie class
  public static class Movie{
    private String title;
    private int duration;
    private double popularity;

    public Movie(){
        title = "Title here";
        duration = 1;
        popularity = 1;
    }
    
    public Movie(String title, int duration, double popularity){
	this.title = title;
	this.duration = duration;
	this.popularity = popularity;
    }
    
    private int getDuration(){
	return duration;
    }
    
    private double getPopularity(){
    	return popularity;
    }

    public String toString(){
	    return title + " (" + duration + " minutes)";
    }
  }
  
  //Auditorium class
  public static class Auditorium{
    int id, capacity;

    Auditorium(){
        id = 10;
        capacity = 100;
    }
    
    Auditorium(int id, int capacity){
	this.id = id;
	this.capacity = capacity;
    }

    int getCapacity(){
	return capacity;
    }
   
    public String toString(){
	    return "Auditorium " + id;
    }
  }

 //Showing class
  public static class Showing{
    Movie movie;
    Auditorium auditorium;
    LocalTime showtime;

    Showing(){
        Movie movie = new Movie();
        Auditorium auditorium = new Auditorium();
        showtime = LocalTime.of(1,1);
    }
    
    Showing(Movie mov, Auditorium aud, LocalTime time){
	movie = mov;
	auditorium = aud;
	showtime = time;
    }
    
    Movie getMovie(){
        return movie;
    }
    
    Auditorium getAuditorium(){
	    return auditorium;
    }

    LocalTime getTime(){
	    return showtime;
    }
    
    public String toString() {
        return movie + " in " + auditorium + " at " + showtime;
    }
  }
 
 //Theater class
 public static class Theater {
	 Random random = new Random();
	 int timeBetweenShowings = 15;
	 LocalTime firstShowtime;
	 LocalTime lastShowtime;
	 ArrayList<Showing> showings = new ArrayList<Showing>();
	 ArrayList<Auditorium> auditoriums = new ArrayList<Auditorium>();
	 ArrayList<Movie> movie = new ArrayList<Movie>();
	 double ticketPrice = 16.99;
	 
	 Theater(){
		 firstShowtime = LocalTime.of(10, 00);
		 lastShowtime = LocalTime.of(21, 00);
		 
		 movie.add(new Movie("Joker", 122, random.nextDouble() * 10));
		 movie.add(new Movie("Zombie Land", 93, random.nextDouble() * 10));
		 movie.add(new Movie("Countdown", 90, random.nextDouble() * 10));
		 movie.add(new Movie("Hustlers", 109, random.nextDouble() * 10));
		 movie.add(new Movie("IT Chapter Two", 169, random.nextDouble() * 10));
		 
		 showings.clear();
		 
		 auditoriums.add(new Auditorium(1, 50));
		 auditoriums.add(new Auditorium(2, 75));
		 auditoriums.add(new Auditorium(3, 100));
	 }	 
		 
		 void schedule(){
			 for(int a = 0; a < 3; a++) {
			    LocalTime currTime = firstShowtime;
			    boolean isOk = true;
			    int indexOfShowings = 0;
				while(isOk){
				    Movie randomlySelectedMov = randomMovie();
					showings.add(new Showing(randomlySelectedMov, auditoriums.get(a), currTime));
    				int movDuration = randomlySelectedMov.getDuration();
    				int nextMov = movDuration + timeBetweenShowings;
    			    currTime = currTime.plus(Duration.ofMinutes(nextMov));
    			    indexOfShowings++;
    			    boolean timeCheck = currTime.isAfter(lastShowtime);
					if(timeCheck){
					    isOk=false;
					    break;
					}
				}
			 }
		 }
		 
		 void sellTickets() {
		     int TOTALticketsSold=0;
		     double TOTALrevenue = 0;
		     double popularityGetter = 0;
		     DecimalFormat formatOne = new DecimalFormat("#, ###, ###.##");
			 for(int a = 0; a < showings.size(); a++) {
				     for(int p = 0; p < 5; p++){
				         if((showings.get(a)).getMovie() == movie.get(p)){
				             popularityGetter = (movie.get(p)).getPopularity();
				         }
				     }
				     double percOfTicketSold = popularityGetter * 0.100;
				     
				     double capGetter = 0;
				     for(int o = 0; o < 3; o++){
				         if((showings.get(a)).getAuditorium() == auditoriums.get(o)){
				             capGetter = (auditoriums.get(o)).getCapacity();
				         }
				     }
				     double ticketsSold = percOfTicketSold * capGetter;
				     int realTicketsSold = (int) ticketsSold;
    				 double revenue = realTicketsSold * ticketPrice;
    				 System.out.println(showings.get(a) + "     | tickets sold: " + realTicketsSold + "     | revenue: " + formatOne.format(revenue));
				 
				    TOTALticketsSold += realTicketsSold;
				    TOTALrevenue += revenue;
				 
			 }
			 System.out.println("Tickets sold for today: " + formatOne.format(TOTALticketsSold));
		        System.out.println("Today's total revenue: " + formatOne.format(TOTALrevenue) + "\n\n");
		        TOTALticketsSold=0;
		     TOTALrevenue = 0;
		 }
		 
		 private Movie randomMovie() {
			 return movie.get(random.nextInt(4));
		 }
    }
    
 public static void main(String[] args){
	for(int a = 1; a <= 3; a++){
	    Theater theater = new Theater();
	    theater.schedule();
	    theater.sellTickets();
    }
 }
}
