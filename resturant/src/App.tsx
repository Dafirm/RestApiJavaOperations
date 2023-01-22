import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import "./App.css";
import { RootState } from "./app/store";
import CustomerCard from "./components/CustomerCard";
import ReservationCards from "./components/ReservationCards";
import { addReservation } from "./features/reservationSlice";

function App() {

  const [reservationNameInput, setReservationNameInput] = useState('');

  const reservations = useSelector((state:RootState) => state.reservations.value)

    const customers = useSelector(
      (state: RootState) => state.customer.value
    );
   const dispatch = useDispatch();

  const handleReservations = () => {
    if(!reservationNameInput) return;
    dispatch(addReservation(reservationNameInput));
    setReservationNameInput("");
  }
  return (
    <div className="App">
      <div className="container">
        <div className="reservation-container">
          <div>
            <h5 className="reservation-header">Reservations List</h5>
            <div className="reservation-cards-container">
              {reservations.map((name, index) => {
                return <ReservationCards name={name} index={index} />;
              })}
            </div>
          </div>
          <div className="reservation-input-container">
            <input
              value={reservationNameInput}
              onChange={(e) => setReservationNameInput(e.target.value)}
            />
            <button onClick={handleReservations}>Add Name</button>
          </div>
        </div>
        <div className="customer-food-container">
              {customers.map(customer => {
                return <CustomerCard 
                id = {customer.id}
                name={customer.name}
                food={customer.food}/>
              })}
        </div>
      </div>
    </div>
  );
}

export default App;