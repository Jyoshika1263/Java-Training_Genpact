import React, { useState, useEffect } from "react";
import BillingService from "../../services/BillingService";
import { Grid, Table, Edit, Trash2, Plus } from "lucide-react";

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

const ViewAllBilling: React.FC = () => {
  const [billings, setBillings] = useState<Billing[]>([]);
  const [viewMode, setViewMode] = useState<"table" | "card">("table");
  const [selectedBilling, setSelectedBilling] = useState<Billing | null>(null);
  const [isAddModalOpen, setIsAddModalOpen] = useState<boolean>(false);
  const [errors, setErrors] = useState<string[]>([]);

  useEffect(() => {
    BillingService.getAllBillings()
      .then(response => {
        setBillings(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  const [billingData, setBillingData] = useState<Billing>({
    bill_id: 0,
    PatientID: 0,
    amount: 0,
    payment_status: "",
    billing_time: "",
    payment_method: "",
    billing_status: "",
    created_by: 0,
  });

  const handleEdit = (billing: Billing) => {
    setSelectedBilling(billing);
    setBillingData(billing);
  };

  const handleDelete = (bill_id: number) => {
    console.log(`Delete billing with ID: ${bill_id}`);
    BillingService.deleteBilling(bill_id);
    window.location.reload();
  };

  const closeModal = () => {
    setSelectedBilling(null);
    setIsAddModalOpen(false);
    setErrors([]);
  };

  const handleRegisterChange = (e: { target: { name: any; value: any; }; }) => {
    setBillingData({ ...billingData, [e.target.name]: e.target.value });
  };

  const handleUpdateChange = (e: { target: { name: any; value: any; }; }) => {
    setBillingData({ ...billingData, [e.target.name]: e.target.value });
  };
  const validateForm = (): string[] => {
    const errorsList: string[] = [];
    if (!billingData.PatientID) errorsList.push("Patient ID is required.");
    if (!billingData.amount || billingData.amount <= 0) errorsList.push("Amount must be a positive number.");
    if (!billingData.payment_status.trim()) errorsList.push("Payment status is required.");
    if (!billingData.billing_time.trim()) errorsList.push("Billing time is required.");
    if (!billingData.payment_method.trim()) errorsList.push("Payment method is required.");
    if (!billingData.billing_status.trim()) errorsList.push("Billing status is required.");
    return errorsList;
  };

  const handleUpdateSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    const validationErrors = validateForm();
    if (validationErrors.length > 0) {
      setErrors(validationErrors);
      return;
    }
    BillingService.updateBilling(billingData, billingData.bill_id);
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
    BillingService.registerBilling(billingData);
    console.log("Register Form submitted");
    closeModal();
    window.location.reload();
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">All Billings</h1>
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
                <th className="py-2 px-4 border-b">Bill ID</th>
                <th className="py-2 px-4 border-b">Patient ID</th>
                <th className="py-2 px-4 border-b">Amount</th>
                <th className="py-2 px-4 border-b">Payment Status</th>
                <th className="py-2 px-4 border-b">Billing Time</th>
                <th className="py-2 px-4 border-b">Payment Method</th>
                <th className="py-2 px-4 border-b">Billing Status</th>
                <th className="py-2 px-4 border-b">Created By</th>
                <th className="py-2 px-4 border-b rounded-tr-lg">Actions</th>
              </tr>
            </thead>
            <tbody>
              {billings.map(billing => (
                <tr key={billing.bill_id} className="hover:bg-gray-50">
                  <td className="py-2 px-4 border-b">{billing.bill_id}</td>
                  <td className="py-2 px-4 border-b">{billing.PatientID}</td>
                  <td className="py-2 px-4 border-b">{billing.amount}</td>
                  <td className="py-2 px-4 border-b">{billing.payment_status}</td>
                  <td className="py-2 px-4 border-b">{new Date(billing.billing_time).toLocaleDateString()}</td>
                  <td className="py-2 px-4 border-b">{billing.payment_method}</td>
                  <td className="py-2 px-4 border-b">{billing.billing_status}</td>
                  <td className="py-2 px-4 border-b">{billing.created_by}</td>
                  <td className="py-2 px-4 border-b flex space-x-2">
                    <button
                      className="text-blue-600 hover:text-blue-900"
                      onClick={() => handleEdit(billing)}
                    >
                      <Edit size={24} />
                    </button>
                    <button
                      className="text-red-600 hover:text-red-900"
                      onClick={() => handleDelete(billing.bill_id)}
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
          {billings.map(billing => (
            <div key={billing.bill_id} className="bg-white p-4 rounded-lg shadow-md">
              <div className="flex justify-between items-center mb-2">
                <h2 className="text-xl font-bold">Bill ID: {billing.bill_id}</h2>
                <div className="flex space-x-2">
                  <button
                    className="text-blue-600 hover:text-blue-900"
                    onClick={() => handleEdit(billing)}
                  >
                    <Edit size={24} />
                  </button>
                  <button
                    className="text-red-600 hover:text-red-900"
                    onClick={() => handleDelete(billing.bill_id)}
                  >
                    <Trash2 size={24} />
                  </button>
                </div>
              </div>
              <p><strong>Patient ID:</strong> {billing.PatientID}</p>
              <p><strong>Amount:</strong> {billing.amount}</p>
              <p><strong>Payment Status:</strong> {billing.payment_status}</p>
              <p><strong>Billing Time:</strong> {new Date(billing.billing_time).toLocaleDateString()}</p>
              <p><strong>Payment Method:</strong> {billing.payment_method}</p>
              <p><strong>Billing Status:</strong> {billing.billing_status}</p>
              <p><strong>Created By:</strong> {billing.created_by}</p>
            </div>
          ))}
        </div>
      )}
      {selectedBilling && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-3/4 max-w-4xl relative">
            <button
              className="absolute top-2 right-2 text-gray-600 hover:text-gray-900"
              onClick={closeModal}
            >
              &times;
            </button>
            <h2 className="text-2xl font-bold mb-4">Edit Billing</h2>
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
                    defaultValue={selectedBilling.PatientID}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Amount</label>
                  <input
                    type="number"
                    name="amount"
                    onChange={handleUpdateChange}
                    defaultValue={selectedBilling.amount}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Payment status</label>
                  <input
                    type="text"
                    name="payment_status"
                    onChange={handleUpdateChange}
                    defaultValue={selectedBilling.payment_status}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Billing time</label>
                  <input
                    type="text"
                    name="billing_time"
                    onChange={handleUpdateChange}
                    defaultValue={selectedBilling.billing_time}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Payment method</label>
                  <input
                    type="text"
                    name="payment_method"
                    onChange={handleUpdateChange}
                    defaultValue={selectedBilling.payment_method}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Billing Status</label>
                  <input
                    type="text"
                    name="billing_status"
                    onChange={handleUpdateChange}
                    defaultValue={selectedBilling.billing_status}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Created by</label>
                  <input
                    type="number"
                    name="created_by"
                    onChange={handleUpdateChange}
                    defaultValue={selectedBilling.created_by}
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
            <h2 className="text-2xl font-bold mb-4">Add Billing</h2>
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
                  <label className="block text-sm font-medium text-gray-700">Amount</label>
                  <input
                    type="number"
                    name="amount"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">payment_status</label>
                  <input
                    type="text"
                    name="payment_status"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Billing time</label>
                  <input
                    type="text"
                    name="billing_time"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Payment method</label>
                  <input
                    type="text"
                    name="payment_method"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Billing status</label>
                  <input
                    type="text"
                    name="billing_status"
                    onChange={handleRegisterChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700">Created by</label>
                  <input
                    type="number"
                    name="created_by"
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

export default ViewAllBilling;
