/**
 * 
 */
package org.troy_temple;

/**
 * @author Maurice
 *
 */
public class Event {
	private String event_name;
	private String event_date;
	private String event_venue;
	private String full_desc;
	//Img url: http://www.troy-temple.org/uploads/event_image/8c8253df-54a5-4c17-9c19-a74ebf5809ea.jpg
	private String event_img;
	private String event_type;
	private String event_recipient_emails;
	
	/**
	 * @return the event_name
	 */
	public String getEvent_name() {
		return event_name;
	}
	/**
	 * @param event_name the event_name to set
	 */
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	/**
	 * @return the event_date
	 */
	public String getEvent_date() {
		return event_date;
	}
	/**
	 * @param event_date the event_date to set
	 */
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	/**
	 * @return the event_venue
	 */
	public String getEvent_venue() {
		return event_venue;
	}
	/**
	 * @param event_venue the event_venue to set
	 */
	public void setEvent_venue(String event_venue) {
		this.event_venue = event_venue;
	}
	/**
	 * @return the full_desc
	 */
	public String getFull_desc() {
		return full_desc;
	}
	/**
	 * @param full_desc the full_desc to set
	 */
	public void setFull_desc(String full_desc) {
		this.full_desc = full_desc;
	}
	/**
	 * @return the event_img
	 */
	public String getEvent_img() {
		return event_img;
	}
	/**
	 * @param event_img the event_img to set
	 */
	public void setEvent_img(String event_img) {
		this.event_img = event_img;
	}
	/**
	 * @return the event_type
	 */
	public String getEvent_type() {
		return event_type;
	}
	/**
	 * @param event_type the event_type to set
	 */
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	/**
	 * @return the event_recipient_emails
	 */
	public String getEvent_recipient_emails() {
		return event_recipient_emails;
	}
	/**
	 * @param event_recipient_emails the event_recipient_emails to set
	 */
	public void setEvent_recipient_emails(String event_recipient_emails) {
		this.event_recipient_emails = event_recipient_emails;
	}
	
}