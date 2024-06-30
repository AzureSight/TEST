package backend;

import backend.Event.EventSchedule;

public class Customer {

    private int id = 0;
    private String fname, lname, email, contactnum;
    private User eventOrganizer;
    private EventSchedule[] schedules;

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String fname, String lname, String email, String contactnum, User eventOrganizer) {

            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.contactnum = contactnum;
            this.eventOrganizer = eventOrganizer;            
    }

    public Customer(int id, String fname, String lname, String email, String contactnum) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contactnum = contactnum;
    }

    public Customer(int id, String name, String email, String contactnum,User eventOrganizer) {
        this.id = id;
        String[] splitname = name.split(" ");
        this.fname = splitname[0];
        this.lname = splitname[1];
        this.email = email;
        this.contactnum = contactnum;
        this.eventOrganizer = eventOrganizer;
    }

    public Customer(int id, String fname, String lname, String email, String contactnum, User eventOrganizer) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contactnum = contactnum;
        this.eventOrganizer = eventOrganizer;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }
    
    public String getContactnum() {
        return contactnum;
    }

    public User getEventOrganizer() {
        return eventOrganizer;
    }

    public EventSchedule[] getSchedules() {
        return schedules;
    }
}
