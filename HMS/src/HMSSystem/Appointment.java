package HMSSystem;

import java.sql.Timestamp;

public class Appointment {
 
    private int id;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
 
    public Appointment(int patientId, int doctorId, String appointmentDate) {
        super();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
    }
 
    public Appointment(int id, int patientId, int doctorId, String appointmentDate) {
        super();
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public int getPatientId() {
        return patientId;
    }
 
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
 
    public int getDoctorId() {
        return doctorId;
    }
 
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
 
    public String getAppointmentDate() {
        return appointmentDate;
    }
 
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
 
    @Override
    public String toString() {
        return "Appointment [id=" + id + ", patientId=" + patientId + ", doctorId=" + doctorId + ", appointmentDate=" + appointmentDate + "]";
    }
}



























//public class Appointment {
//    private int patientId;
//    private int doctorId;
//    private String appointmentDate;
//    private String reason;
//	public Appointment(int patientId, int doctorId, String appointmentDate, String reason) {
//		super();
//		
//		this.patientId = patientId;
//		this.doctorId = doctorId;
//		this.appointmentDate = appointmentDate;
//		this.reason = reason;
//	}	
//	public int getPatientId() {
//		return patientId;
//	}
//	public void setPatientId(int patientId) {
//		this.patientId = patientId;
//	}
//	public int getDoctorId() {
//		return doctorId;
//	}
//	public void setDoctorId(int doctorId) {
//		this.doctorId = doctorId;
//	}
//	public String getAppointmentDate() {
//		return appointmentDate;
//	}
//	public void setAppointmentDate(String appointmentDate) {
//		this.appointmentDate = appointmentDate;
//	}
//	public String getReason() {
//		return reason;
//	}
//	public void setReason(String reason) {
//		this.reason = reason;
//	}
//    
//
//}
