import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AdminPage from "./pages/AdminPage";
import AdminLoginPage from "./pages/AdminLoginPage"
import ReservationPage from "./pages/ReservationPage";
import Home from "./pages/Home";
import ProtectedRoute from "./components/ProtectedRoutes/ProtectedRoute";
import "./App.css"
import FinalizedPage from "./pages/FinalizedPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/reservation" element={<ReservationPage />} />
        <Route path="/login" element={<AdminLoginPage />} />
        <Route path="/finalized" element={<FinalizedPage/>}/>
        <Route element={<ProtectedRoute />}>
          <Route path="/admin" element={<AdminPage />} />
          {/* alte rute protejate */}
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
