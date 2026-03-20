import { useEffect, useRef, useState } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import Alert from './components/layout/Alert';
import Footer from './components/layout/Footer';
import Header from './components/layout/Header';
import Navbar from './components/layout/Navbar';
import About from './pages/About';
import CalculatorPage from './pages/CalculatorPage';
import Contact from './pages/Contact';
import Home from './pages/Home';
import Login from './pages/Login';
import NotFound from './pages/NotFound';
import Services from './pages/Services';
import Signup from './pages/Signup';

function App() {
  const [alert, setAlert] = useState(null);
  const location = useLocation();
  const mainRef = useRef(null);

  const showAlert = (message, type = 'primary') => {
    setAlert({ message, type });
  };

  useEffect(() => {
    if (!alert) return;
    const timer = setTimeout(() => setAlert(null), 3000);
    return () => clearTimeout(timer);
  }, [alert]);

  useEffect(() => {
    if (mainRef.current) {
      mainRef.current.focus();
    }
  }, [location.pathname]);

  return (
    <>
      <Header />
      <div className="container-fluid">
        <div className="row min-vh-100">
          <Navbar />
          <Alert alert={alert} onClose={() => setAlert(null)} />

          <main id="main-content" ref={mainRef} className="container py-4" tabIndex="-1" aria-live="polite">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/services" element={<Services />} />
              <Route path="/services/calculator/:type" element={<CalculatorPage showAlert={showAlert} />} />
              <Route path="/netbanking" element={<Login showAlert={showAlert} />} />
              <Route path="/signup" element={<Signup showAlert={showAlert} />} />
              <Route path="/contact" element={<Contact />} />
              <Route path="*" element={<NotFound />} />
            </Routes>
          </main>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default App;
