import axios, { AxiosResponse } from "axios";

const API_URL = "http://localhost:5050/api/appointments";

interface Appointment {
  AppointmentID: number;
  PatientID: number;
  DoctorID: number;
  AppointmentDate: string;
  AppointmentTime: string;
  appointmentType: string;
  Reason: string;
  registration_time: string;
}

class AppointmentService {
  getAllAppointments(): Promise<AxiosResponse<Appointment[]>> {
    return axios.get(`${API_URL}/all`);
  }

  getAppointmentById(id: number): Promise<AxiosResponse<Appointment>> {
    return axios.get(`${API_URL}/${id}`);
  }

  registerAppointment(appointment: Appointment): Promise<AxiosResponse<Appointment>> {
    return axios.post(`${API_URL}/register`, appointment);
  }

  deleteAppointment(id: number): Promise<AxiosResponse<void>> {
    return axios.delete(`${API_URL}/delete/${id}`);
  }

  updateAppointment(appointment: Appointment, id: number): Promise<AxiosResponse<Appointment>> {
    return axios.put(`${API_URL}/update/${id}`, appointment);
  }

  searchAppointment(keyword: string): Promise<AxiosResponse<Appointment[]>> {
    return axios.get(`${API_URL}/search?keyword=${keyword}`);
  }
}

export default new AppointmentService();
