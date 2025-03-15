import React, { useState, useEffect } from "react";
import AppointmentService from "../../services/AppointmentService";
import { Grid, Table, Edit, Trash2, Plus } from "lucide-react";

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

const ViewAllAppointments: React.FC = () => {
  const [appointments, setAppointments] = useState<Appointment[]>([]);
  const [viewMode, setViewMode] = useState<"table" | "card">("table");
  const [selectedAppointment, setSelectedAppointment] = useState<Appointment | null>(null);
  const [isAddModalOpen, setIsAddModalOpen] = useState<boolean>(false);
  const [errors, setErrors] = useState<string[]>([]);

  useEffect(() => {
    AppointmentService.getAllAppointments()
      .then(response => {
        setAppointments(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  const [appointmentData, setAppointmentData] = useState<Appointment>({
    AppointmentID: 0,
    PatientID: 0,
    DoctorID: 0,
    AppointmentDate: "",
    AppointmentTime: "",
    appointmentType: "",
    Reason: "",
    registration_time: "",
  });

  const handleEdit = (appointment: Appointment) => {
    setSelectedAppointment(appointment);
    setAppointmentData(appointment);
  };

  const handleDelete = (AppointmentID: number) => {
    console.log(`Delete appointment with ID: ${AppointmentID}`);
    AppointmentService.deleteAppointment(AppointmentID);
    window.location.reload();
  };

  const closeModal = () => {
    setSelectedAppointment(null);
    setIsAddModalOpen(false);
    setErrors([]);
  };

  const handleRegisterChange = (e: { target: { name: any; value: any; }; }) => {
    setAppointmentData({ ...appointmentData, [e.target.name]: e.target.value });
  };

  const handleUpdateChange = (e: { target: { name: any; value: any; }; }) => {
    setAppointmentData({ ...appointmentData, [e.target.name]: e.target.value });
  };
  const validateForm = (): string[] => {
    const errorsList: string[] = [];
    if (!appointmentData.PatientID) errorsList.push("Patient ID is required.");
    if (!appointmentData.DoctorID) errorsList.push("Doctor ID is required.");
    if (!appointmentData.AppointmentDate.trim()) errorsList.push("Appointment Date is required.");
    if (!appointmentData.AppointmentTime.trim()) errorsList.push("Appointment Time is required.");
    if (!appointmentData.appointmentType.trim()) errorsList.push("Appointment Type is required.");
    if (!appointmentData.Reason.trim()) errorsList.push("Reason is required.");
    return errorsList;
  };
  const handleUpdateSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    const validationErrors = validateForm();
    if (validationErrors.length > 0) {
      setErrors(validationErrors);
      return;
    }
    AppointmentService.updateAppointment(appointmentData, appointmentData.AppointmentID);
    console.log("Update Form submitted");
    closeModal();
    window.location.reload();
  };

  const handleRegisterSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    const validationErrors = validateForm();
    if (validationErrors.length > 0) {
      setErrors(validationErrors);
      return;
    }
    AppointmentService.registerAppointment(appointmentData);
    console.log("Register Form submitted");
    closeModal();
    window.location.reload();
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">All Appointments</h1>
      <div className="flex justify-end mb-4">
        <button
          className={"mr-2 text-green-700"}
          onClick={() => setIsAddModalOpen(true)}
        >
          <Plus size={24} />
        </button>
        <button
          className={`mr-2 ${viewMode === "table" ? "text-blue-600" : "text-gray-600"}`}
          onClick={() => setViewMode("table")}
        >
          <Table size={24} />
        </button>
        <button
          className={`${viewMode === "card" ? "text-blue-600" : "text-gray-600"}`}
          onClick={() => setViewMode("card")}
        >
          <Grid size={24} className="mr-2" />
        </button>
      </div>
      {viewMode === "table" ? (
        <div className="overflow-x-auto rounded-lg">
          <table className="min-w-full bg-white rounded-lg">
            <thead className="bg-secondary-200 rounded-t-lg">
              <tr>
                <th className="py-2 px-4 border-b">Appointment ID</th>
                <th className="py-2 px-4 border-b">Patient ID</th>
                <th className="py-2 px-4 border-b">Doctor ID</th>
                <th className="py-2 px-4 border-b">Appointment Date</th>
                <th className="py-2 px-4 border-b">Appointment Time</th>
                <th className="py-2 px-4 border-b">Type</th>
                <th className="py-2 px-4 border-b">Reason</th>
                <th className="py-2 px-4 border-b">Registration Time</th>
                <th className="py-2 px-4 border-b rounded-tr-lg">Actions</th>
              </tr>
            </thead>
            <tbody>
              {appointments.map(appointment => (
                <tr key={appointment.AppointmentID} className="hover:bg-gray-50">
                  <td className="py-2 px-4 border-b">{appointment.AppointmentID}</td>
                  <td className="py-2 px-4 border-b">{appointment.PatientID}</td>
                  <td className="py-2 px-4 border-b">{appointment.DoctorID}</td>
                  <td className="py-2 px-4 border-b">{new Date(appointment.AppointmentDate).toLocaleDateString()}</td>
                  <td className="py-2 px-4 border-b">{appointment.AppointmentTime}</td>
                  <td className="py-2 px-4 border-b">{appointment.appointmentType}</td>
                  <td className="py-2 px-4 border-b">{appointment.Reason}</td>
                  <td className="py-2 px-4 border-b">{new Date(appointment.registration_time).toLocaleDateString()}</td>
                  <td className="py-2 px-4 border-b flex space-x-2">
                    <button
                      className="text-blue-600 hover:text-blue-900"
                      onClick={() => handleEdit(appointment)}
                    >
                      <Edit size={24} />
                    </button>
                    <button
                      className="text-red-600 hover:text-red-900"
                      onClick={() => handleDelete(appointment.AppointmentID)}
                    >
                      <Trash2 size={24} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {appointments.map(appointment => (
            <div key={appointment.AppointmentID} className="bg-white p-4 rounded-lg shadow-md">
              <div className="flex justify-between items-center mb-2">
                <h2 className="text-xl font-bold">Appointment ID: {appointment.AppointmentID}</h2>
                <div className="flex space-x-2">
                  <button
                    className="text-blue-600 hover:text-blue-900"
                    onClick={() => handleEdit(appointment)}
                  >
                    <Edit size={24} />
                  </button>
                  <button
                    className="text-red-600 hover:text-red-900"
                    onClick={() => handleDelete(appointment.AppointmentID)}
                  >
                    <Trash2 size={24} />
                  </button>
                </div>
              </div>
              <p><strong>Patient ID:</strong> {appointment.PatientID}</p>
              <p><strong>Doctor ID:</strong> {appointment.DoctorID}</p>
              <p><strong>Appointment Date:</strong> {new Date(appointment.AppointmentDate).toLocaleDateString()}</p>
              <p><strong>Appointment Time:</strong> {appointment.AppointmentTime}</p>
              <p><strong>Type:</strong> {appointment.appointmentType}</p>
              <p><strong>Reason:</strong> {appointment.Reason}</p>
              <p><strong>Registration Time:</strong> {new Date(appointment.registration_time).toLocaleDateString()}</p>
            </div>
          ))}
        </div>
      )}
      {selectedAppointment && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-3/4 max-w-4xl relative">
            <button
              className="absolute top-2 right-2 text-gray-600 hover:text-gray-900"
              onClick={closeModal}
            >
              &times;
            </button>
            <h2 className="text-2xl font-bold mb-4">Edit Appointment</h2>
            {errors.length > 0 && (
              <div className="bg-red-100 text-red-700 p-4 rounded-lg mt-4">
                {errors.map((error, index) => (
                  <p key={index}>{error}</p>
                ))}
              </div>
            )}
            <form onSubmit={handleUpdateSubmit}>
              <div className="grid grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">Patient ID</label>
                  <input
                    type="number"
                    name="PatientID"
                    onChange={handleUpdateChange}
                    defaultValue={selectedAppointment.PatientID}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Doctor ID</label>
                  <input
                    type="number"
                    name="DoctorID"
                    onChange={handleUpdateChange}
                    defaultValue={selectedAppointment.DoctorID}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Appointment Date</label>
                  <input
                    type="date"
                    name="AppointmentDate"
                    onChange={handleUpdateChange}
                    defaultValue={selectedAppointment.AppointmentDate}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Appointment Time</label>
                  <input
                    type="text"
                    name="AppointmentTime"
                    onChange={handleUpdateChange}
                    defaultValue={selectedAppointment.AppointmentTime}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Appointment Type</label>
                  <input
                    type="text"
                    name="appointmentType"
                    onChange={handleUpdateChange}
                    defaultValue={selectedAppointment.appointmentType}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Reason</label>
                  <input
                    type="text"
                    name="Reason"
                    onChange={handleUpdateChange}
                    defaultValue={selectedAppointment.Reason}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>  
              </div>
              <div className="mt-4 flex justify-end space-x-2">
                <button
                  type="button"
                  className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md hover:bg-gray-400"
                  onClick={closeModal}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      {isAddModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-3/4 max-w-4xl relative">
            <button
              className="absolute top-2 right-2 text-gray-600 hover:text-gray-900"
              onClick={closeModal}
            >
              &times;
            </button>
            <h2 className="text-2xl font-bold mb-4">Add Appointment</h2>
            {errors.length > 0 && (
              <div className="bg-red-100 text-red-700 p-4 rounded-lg mt-4">
                {errors.map((error, index) => (
                  <p key={index}>{error}</p>
                ))}
              </div>
            )}
            <form onSubmit={handleRegisterSubmit}>
              <div className="grid grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">Patient ID</label>
                  <input
                    type="number"
                    name="PatientID"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Doctor ID</label>
                  <input
                    type="number"
                    name="DoctorID"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Appointment Date</label>
                  <input
                    type="date"
                    name="AppointmentDate"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Appointment Time</label>
                  <input
                    type="text"
                    name="AppointmentTime"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Appointment Type</label>
                  <input
                    type="text"
                    name="appointmentType"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Reason</label>
                  <input
                    type="text"
                    name="Reason"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
              </div>
              <div className="mt-4 flex justify-end space-x-2">
                <button
                  type="button"
                  className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md hover:bg-gray-400"
                  onClick={closeModal}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
</div>
  );
};

export default ViewAllAppointments;
