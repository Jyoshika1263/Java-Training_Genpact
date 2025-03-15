import axios, { AxiosResponse } from "axios";

const API_URL = "http://localhost:5050/api/billings";

interface Billing {
  bill_id: number;
  PatientID: number;
  amount: number;
  payment_status: string;
  billing_time: string;
  payment_method: string;
  billing_status: string;
  created_by: number;
}

class BillingService {
  getAllBillings(): Promise<AxiosResponse<Billing[]>> {
    return axios.get(`${API_URL}/all`);
  }

  getBillingById(id: number): Promise<AxiosResponse<Billing>> {
    return axios.get(`${API_URL}/${id}`);
  }

  registerBilling(billing: Billing): Promise<AxiosResponse<Billing>> {
    return axios.post(`${API_URL}/register`, billing);
  }

  deleteBilling(id: number): Promise<AxiosResponse<void>> {
    return axios.delete(`${API_URL}/delete/${id}`);
  }

  updateBilling(billing: Billing, id: number): Promise<AxiosResponse<Billing>> {
    return axios.put(`${API_URL}/update/${id}`, billing);
  }

  searchBilling(keyword: string): Promise<AxiosResponse<Billing[]>> {
    return axios.get(`${API_URL}/search?keyword=${keyword}`);
  }
}

export default new BillingService();
